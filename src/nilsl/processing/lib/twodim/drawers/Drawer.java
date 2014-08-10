package nilsl.processing.lib.twodim.drawers;

import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import processing.core.PApplet;
import processing.core.PGraphics;

public abstract class Drawer {

	public ImageProvider imageProvider = null;
	public PApplet parentApplet = null;
	public PGraphics canvas;

	public Drawer() {
		super();
	}

	public abstract void draw();

}