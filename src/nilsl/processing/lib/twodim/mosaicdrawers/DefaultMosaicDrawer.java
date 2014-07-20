package nilsl.processing.lib.twodim.mosaicdrawers;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import nilsl.processing.lib.twodim.*;
import nilsl.processing.lib.twodim.counters.Counter2d;
import nilsl.processing.lib.twodim.counters.DefaultCounter2d;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;

public class DefaultMosaicDrawer implements MosaicDrawer2d {

	private Counter2d counter;
	private int imgXSize;
	private int imgYSize;
	
	public ImageProvider imageProvider;
	public PApplet parentApplet;
	
	public DefaultMosaicDrawer(int maxX, int maxY, int imgXSize, int imgYSize) {
		this.imgXSize=imgXSize;
		this.imgYSize=imgYSize;
		counter = new DefaultCounter2d(maxX, maxY);
	}

	public DefaultMosaicDrawer(int maxX, int maxY, int imgXSize, int imgYSize, Counter2d counter) {
		this.imgXSize=imgXSize;
		this.imgYSize=imgYSize;
		this.counter = counter;
	}
	
	@Override
	public void draw() {
		do{
			PGraphics pg = parentApplet.createGraphics(imgXSize, imgYSize);
			imageProvider.getNextImage(pg);
			parentApplet.image(pg,counter.getCurX()*imgXSize, counter.getCurY()*imgYSize);
			counter.inc();
		}
		while (!counter.eof());
		
		
		
	}

}
