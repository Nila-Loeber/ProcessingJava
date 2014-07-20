package nilsl.processing.lib.twodim.mosaicdrawers;

import processing.core.PApplet;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;

public abstract class MosaicDrawer2d {
	public abstract void draw();
	public ImageProvider imageProvider=null;
	public PApplet parentApplet=null;
}
