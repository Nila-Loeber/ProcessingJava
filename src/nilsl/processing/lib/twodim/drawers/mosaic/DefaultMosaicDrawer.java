package nilsl.processing.lib.twodim.drawers.mosaic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nilsl.processing.lib.applet.NApplet;
import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.twodim.imageproviders.Resetable;
import processing.core.PGraphics;
import nilsl.processing.lib.twodim.counters.Counter2d;
import nilsl.processing.lib.twodim.counters.DefaultCounter2d;

public class DefaultMosaicDrawer extends EnhanceableMosaicDrawer2d  {

	protected Counter2d counter;
	protected int imgXSize;
	protected int imgYSize;
	protected int maxX;
	protected int maxY;
	
	static final Logger logger = LogManager.getLogger(DefaultMosaicDrawer.class.getPackage().getName()); 

	protected void ResetCounter()
	{
		counter = new DefaultCounter2d(maxX, maxY);
		((Resetable)imageProvider).reset();
	}
	
	public DefaultMosaicDrawer(MosaicInfo mosInfo) {
		this.imgXSize=mosInfo.imgSizeX;
		this.imgYSize=mosInfo.imgSizeY;
		this.maxX=mosInfo.xdim;
		this.maxY=mosInfo.ydim;
	}

	
	
	@Override
	public void draw() {
		logger.info("Draw called.");
		ResetCounter();
		canvas.background(0);
		do{
			
			PGraphics pg = parentApplet.createGraphics(imgXSize, imgYSize);
			imageProvider.getNextImage(pg);
			if (imageEnhancers.size()>0)
			{
				for(ImageEnhancer enhancer : imageEnhancers)
				{
					enhancer.Enhance(pg);
				}
			}
			canvas.image(pg,counter.getCurX()*imgXSize, counter.getCurY()*imgYSize);
			logger.trace(String.format("Drawing tile %d:%d called.",counter.getCurX(),counter.getCurY() ));
			counter.inc();
		}
		while (!counter.eof() && imageProvider.hasNext());
	}	
}
