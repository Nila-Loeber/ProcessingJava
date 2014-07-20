package nilsl.processing.sketches.mosaics;

import nilsl.processing.lib.twodim.imageproviders.CheckerBoardImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import processing.core.*;

public class CheckerBoard extends PApplet {

	private static final long serialVersionUID = 1L;

	public void setup() {
		size(800, 600);
		noLoop();

	}

	public void draw()
	{
	DefaultMosaicDrawer md = new DefaultMosaicDrawer(3,3,100,100);

	md.imageProvider = new CheckerBoardImageProvider();
	md.parentApplet=this;

	md.draw();

	}

}
