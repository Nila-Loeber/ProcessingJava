package nilsl.processing.lib.twodim.drawers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.onedim.counters.Counter1d;
import nilsl.processing.lib.onedim.counters.DefaultBoundedCounter1d;
import nilsl.processing.lib.onedim.counters.DefaultCounter1d;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import processing.core.PGraphics;

public class RandomPosDrawer extends Drawer {

	static final Logger logger = LogManager.getLogger(RandomPosDrawer.class.getPackage().getName());
	Counter1d counter;
	private RandomDrawerInfo drawerInfo;
	
	public RandomPosDrawer(RandomDrawerInfo info)
	{
		this.drawerInfo = info;
		counter = new DefaultBoundedCounter1d(0,1,drawerInfo.numPics);
	}
	
	@Override
	public void draw() {
		logger.info("Draw called.");
		counter.reset();
		do{
			PGraphics pg = parentApplet.createGraphics(drawerInfo.imgSizeX, drawerInfo.imgSizeY);
			imageProvider.getNextImage(pg);
//			if (imageEnhancers.size()>0)
//			{
//				for(ImageEnhancer enhancer : imageEnhancers)
//				{
//					enhancer.Enhance(pg);
//				}
//			}
			
			int drawXPos = (int)(Math.random()*(drawerInfo.width-drawerInfo.imgSizeX));
			int drawYPos = (int)(Math.random()*(drawerInfo.height-drawerInfo.imgSizeY));
				
			canvas.pushMatrix();			
			canvas.rotate((float)(Math.random()*drawerInfo.slant));
			canvas.image(pg, drawXPos, drawYPos);
			canvas.popMatrix();
			logger.trace(String.format("Random drawing image to %d:%d.",drawXPos,drawYPos));
			counter.inc();
		}
		while (!counter.eof() && imageProvider.hasNext());
	}	

}
