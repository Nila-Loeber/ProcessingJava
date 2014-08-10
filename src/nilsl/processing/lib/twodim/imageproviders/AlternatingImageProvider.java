package nilsl.processing.lib.twodim.imageproviders;

import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.onedim.counters.Counter1d;
import nilsl.processing.lib.onedim.counters.ModularCounter1d;
import processing.core.PGraphics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AlternatingImageProvider extends ImageProvider implements Resetable {

	private Counter1d counter;
	private List<ImageProvider> imageProviders;
	static final Logger logger = LogManager.getLogger(AlternatingImageProvider.class.getName()); 
	
	public AlternatingImageProvider(List<ImageProvider> imageProviders)
	{
		this.imageProviders = imageProviders;
		this.counter =   new ModularCounter1d(1,imageProviders.size());
	}

	
	@Override
	public void getNextImage(PGraphics buffer) {
		boolean imageFound=false;
		logger.trace("getNextImage called.");
		while (!imageFound)
		{
			ImageProvider currentProvider = imageProviders.get(counter.getCurPos());
			if (currentProvider!=null && currentProvider.hasNext())
			{
				currentProvider.getNextImage(buffer);
				imageFound=true;
			}
			counter.inc();
		}
			
	}

	@Override
	public boolean hasNext() {
		return imageProviders.stream().anyMatch(i -> i.hasNext());
	}

	@Override
	public void reset() {
		imageProviders.stream().forEach(i -> ((Resetable)i).reset());
		counter.reset();
	}


}

