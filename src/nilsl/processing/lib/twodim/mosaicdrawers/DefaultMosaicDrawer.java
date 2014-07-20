package nilsl.processing.lib.twodim.mosaicdrawers;

import processing.core.PApplet;
import nilsl.processing.lib.twodim.imageproviders.Resetable;
import processing.core.PGraphics;
import processing.core.PImage;
import nilsl.processing.lib.twodim.*;
import nilsl.processing.lib.twodim.counters.Counter2d;
import nilsl.processing.lib.twodim.counters.DefaultCounter2d;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;

public class DefaultMosaicDrawer extends MosaicDrawer2d implements Zoomable {

	private Counter2d counter;
	private int imgXSize;
	private int imgYSize;
	private int maxX;
	private int maxY;
	

	private void ResetCounter()
	{
		counter = new DefaultCounter2d(maxX, maxY);
		((Resetable)imageProvider).reset();
	}
	
	public DefaultMosaicDrawer(int maxX, int maxY, int imgXSize, int imgYSize) {
		this.imgXSize=imgXSize;
		this.imgYSize=imgYSize;
		this.maxX=maxX;
		this.maxY=maxY;
	}

	@Override
	public void draw() {
		ResetCounter();
		do{
			PGraphics pg = parentApplet.createGraphics(imgXSize, imgYSize);
			imageProvider.getNextImage(pg);
			parentApplet.image(pg,counter.getCurX()*imgXSize, counter.getCurY()*imgYSize);
			counter.inc();
		}
		while (!counter.eof());
		
		
		
	}

	@Override
	public void zoom() {
		imgXSize*=2;
	    imgYSize*=2;
	    draw();
	}

	@Override
	public void unzoom() {
		imgXSize/=2;
	    imgYSize/=2;
	    draw();
	}

}
