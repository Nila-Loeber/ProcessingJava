package nilsl.processing.sketches.mosaics;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Interval;

import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.imageproviders.VideoClipInfo;
import nilsl.processing.lib.twodim.imageproviders.VideoImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.TintDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.TintInfo;

public class ItaloVideo1_Gazebo extends MosaicEditorApplet {

private static final long serialVersionUID = 1L;

	
	public void setup() {
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings();
		settings.mosInfo.xdim = 5;
		settings.mosInfo.ydim = 5;
				
		settings.mosInfo.imgSizeX = 960;
		settings.mosInfo.imgSizeY = 720;
		
		settings.filePath="/Users/Nils/Documents/Kunst/Italo-Disco/Compositions/";
		
		super.setup(settings);
		
		mosDrawer = new DefaultMosaicDrawer(settings.mosInfo);
		mosDrawer.canvas=this.canvas;
		//mosDrawer = new TintDrawer(mosInfo, new TintInfo(255, 255, 255, 200), 60);

		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.GRAY,3));

		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.BLUR,5));
		//mosDrawer.imageEnhancers.add(new ZoomEnhancer(180,180,120));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.POSTERIZE,5));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.THRESHOLD,0.7f));
		
		//mosDrawer.imageEnhancers.add(new CutupEnhancer(settings.mosInfo.ydim));		
		
		//VideoClipInfo clipInfo1=new VideoClipInfo(new Interval(37*1000,38*1000),20);
		VideoClipInfo clipInfo2=new VideoClipInfo(new Interval(37*1000,53*1000),settings.mosInfo.Size());
		//VideoClipInfo clipInfo2=new VideoClipInfo(new Interval(40*1000,60*1000),settings.mosInfo.Size()/2);
		List<VideoClipInfo> clipInfos = new ArrayList<VideoClipInfo>();
		//clipInfos.add(clipInfo1);
		clipInfos.add(clipInfo2);
		imageProvider = new VideoImageProvider("/Users/Nils/Documents/Processing/Video_Mosaic/Gazebo - I Like Chopin (WWF Club 9).mp4",clipInfos);
		imageProvider.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		imageProvider.ApplyFilters();
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;
		
	} 

	
}
