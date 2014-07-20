package nilsl.processing.sketches.checkerboard;

import processing.core.*;

public class CheckerBoard extends PApplet {

	private static final long serialVersionUID = 1L;

	public void setup() {
		size(800, 600);
		noLoop();

	}

	public void draw()
	{
	DefaultMosaicDrawer md = new DefaultMosaicDrawer(2,2,100,100);

	md.imageProvider = new CheckerBoardImageProvider();
	md.parentApplet=this;

	md.draw();

	}

}
