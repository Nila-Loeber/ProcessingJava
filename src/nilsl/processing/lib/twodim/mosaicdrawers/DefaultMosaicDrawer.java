package nilsl.processing.lib.twodim.mosaicdrawers;

import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.twodim.imageproviders.Resetable;
import processing.core.PGraphics;
import nilsl.processing.lib.twodim.counters.Counter2d;
import nilsl.processing.lib.twodim.counters.DefaultCounter2d;

public class DefaultMosaicDrawer extends EnhanceableMosaicDrawer2d implements Zoomable {

	protected Counter2d counter;
	protected int imgXSize;
	protected int imgYSize;
	protected int maxX;
	protected int maxY;
	
	

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
			counter.inc();
		}
		while (!counter.eof() && imageProvider.hasNext());
	}

	@Override
	public void zoom() {
		imgXSize*=2;
	    imgYSize*=2;  
	}

	@Override
	public void unzoom() {
		imgXSize/=2;
	    imgYSize/=2;
	}


}
