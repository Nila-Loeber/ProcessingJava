package nilsl.processing.lib.twodim.drawers.mosaic;

import processing.core.PApplet;

public class MosaicInfo {

	public PApplet applet;

	public MosaicInfo(PApplet applet) {
		this.applet = applet;

	}

	public int imgSizeX;
	public int imgSizeY;
	public int xdim;
	public int ydim;

	public int Size() {
		return xdim * ydim;
	}
}
