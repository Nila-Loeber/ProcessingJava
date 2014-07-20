package nilsl.processing.sketches.mosaics;

import nilsl.processing.lib.twodim.imageproviders.MonoImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import processing.core.*;

public class MonoImage extends PApplet {

	private static final long serialVersionUID = 1L;

	public void setup() {
		size(1000, 1000);
		noLoop();

	}

	public void draw()
	{
	DefaultMosaicDrawer md = new DefaultMosaicDrawer(5,5,200,200);

	md.imageProvider = new MonoImageProvider("c:\\data\\R-356557-1194196996.jpeg");
	md.parentApplet=this;

	md.draw();

	}

}
