package nilsl.processing.lib.twodim.drawers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.onedim.counters.Counter1d;
import nilsl.processing.lib.onedim.counters.DefaultBoundedCounter1d;
import nilsl.processing.lib.onedim.counters.DefaultCounter1d;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.imageproviders.Resetable;
import processing.core.PGraphics;

public class RandomPosDrawer extends Drawer {

	static final Logger logger = LogManager.getLogger(RandomPosDrawer.class.getPackage().getName());
	Counter1d counter;
	private RandomDrawerInfo drawerInfo;
	public final List<ImageEnhancer> imageEnhancers=new ArrayList<ImageEnhancer>();	
	
	public RandomPosDrawer(RandomDrawerInfo info)
	{
		this.drawerInfo = info;
		counter = new DefaultBoundedCounter1d(0,1,drawerInfo.numPics);
	}
	
	@Override
	public void draw() {
		logger.info("Draw called.");		
		counter.reset();
		if (drawerInfo.repeatImages && imageProvider instanceof Resetable) {
			((Resetable)imageProvider).reset();
		}
		do{
			PGraphics pg = parentApplet.createGraphics(drawerInfo.imgSizeX, drawerInfo.imgSizeY);
			imageProvider.getNextImage(pg);
			if (imageEnhancers.size()>0)
			{				
				for(ImageEnhancer enhancer : imageEnhancers)
				{
					enhancer.Enhance(pg);
				}
			}
			
			int drawXPos = (int)(Math.random()*(drawerInfo.width-drawerInfo.imgSizeX));
			int drawYPos = (int)(Math.random()*(drawerInfo.height-drawerInfo.imgSizeY));
				
			canvas.pushMatrix();			
			canvas.rotate((float)(Math.random()*drawerInfo.slant));
			canvas.image(pg, drawXPos, drawYPos);
			canvas.popMatrix();
			logger.trace(String.format("Random drawing image to %d:%d. Enhancers found: ",drawXPos,drawYPos,imageEnhancers.size()));
			counter.inc();
		}
		while (!counter.eof() && imageProvider.hasNext());
	}	

}
