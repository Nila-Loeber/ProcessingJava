package nilsl.processing.lib.twodim.imageproviders;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nilsl.processing.lib.applet.NApplet;
import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.processingshims.PAppletShim;
import processing.core.PApplet;
import processing.core.PImage;
import processing.video.Movie;

public class VideoImageProvider extends MultiImageProvider implements
		Filterable {

	static final Logger logger = LogManager.getLogger(VideoImageProvider.class.getName()); 
	
	private FilterProcessor processor;
	private String filename;

	public VideoImageProvider(String filename, List<VideoClipInfo> clips) {
		super();
		this.filename = filename;
		GenerateImages(clips);
		
	}

	private void GenerateImages(List<VideoClipInfo> clips) {	
		clips.forEach(clipInfo -> images.addAll(GetImages(clipInfo)));   // TODO: test .parallelStream or .parallel for graet win
	}

	private List<NImage> GetImages(VideoClipInfo clip) {
		// Load Video
		Movie video = new Movie(PAppletShim.getApplet(), filename);

		List<NImage> results = new ArrayList<NImage>();
		
		video.play();
		video.read();
		video.volume(0);
		
		float clipStart = ((float)clip.interval.getStartMillis())/1000;
		float clipEnd = ((float)clip.interval.getEndMillis())/1000;
		float period = (clipEnd-clipStart)/clip.numPictures;
		
		for (int i=0; i<clip.numPictures; i++)
		{
			logger.trace(String.format("Generating Frame %d of %d. Jumping to: %f",i+1,clip.numPictures,(clipStart+i*period)));
			video.jump(clipStart+i*period);
			if (video.available()) video.read();
			PImage pImage = PAppletShim.getApplet().createImage(video.width,video.height,PApplet.RGB); // TODO		
			pImage.set(0, 0, video.get());
			results.add(new NImage(pImage));
			
		}
		video.stop();
		return results;
	}

	@Override
	public void ApplyFilters() {
		processor.ApplyFilters(images);

	}

	@Override
	public void setFilterProcessor(FilterProcessor processor) {
		this.processor = processor;

	}

	@Override
	public FilterProcessor getFilterProcessor() {
		return processor;
	}

}
