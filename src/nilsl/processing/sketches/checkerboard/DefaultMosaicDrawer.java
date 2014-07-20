package nilsl.processing.sketches.checkerboard;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import nilsl.processing.lib.twodim.*;

public class DefaultMosaicDrawer implements MosaicDrawer2d {

	private Counter2d counter;
	private int imgXSize;
	private int imgYSize;
	
	public ImageProvider imageProvider;
	public PApplet parentApplet;
	
	public DefaultMosaicDrawer(int maxX, int maxY, int imgXSize, int imgYSize) {
		this.imgXSize=imgXSize;
		this.imgYSize=imgYSize;
		//counter = new DirectionChangerCounter2d(maxX, maxY);
		counter = new DefaultCounter2d(maxX, maxY);
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
