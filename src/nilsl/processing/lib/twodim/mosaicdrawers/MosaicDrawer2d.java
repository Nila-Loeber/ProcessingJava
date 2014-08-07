package nilsl.processing.lib.twodim.mosaicdrawers;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;

public abstract class MosaicDrawer2d {
	public ImageProvider imageProvider=null;
	public PApplet parentApplet=null;
	public PGraphics canvas;
	
	public abstract void draw();
	
}
