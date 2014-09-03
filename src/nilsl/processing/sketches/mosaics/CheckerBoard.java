package nilsl.processing.sketches.mosaics;

import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.MosaicInfo;
import nilsl.processing.lib.twodim.imageproviders.CheckerBoardImageProvider;
import processing.core.*;

public class CheckerBoard extends PApplet {

	private static final long serialVersionUID = 1L;
	private MosaicInfo mosInfo = new MosaicInfo();

	public void setup() {
		size(800, 600);
		noLoop();

	}

	public void draw() {
		mosInfo.imgSizeX = 100;
		mosInfo.imgSizeY = 100;
		mosInfo.xdim = 3;
		mosInfo.ydim = 3;

		DefaultMosaicDrawer md = new DefaultMosaicDrawer(mosInfo);

		md.imageProvider = new CheckerBoardImageProvider();
		md.parentApplet = this;

		md.draw();

	}

}
