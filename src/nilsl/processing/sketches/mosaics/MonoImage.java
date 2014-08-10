package nilsl.processing.sketches.mosaics;

import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.MosaicInfo;
import nilsl.processing.lib.twodim.imageproviders.MonoImageProvider;
import nilsl.processing.lib.twodim.imageproviders.Resetable;
import processing.core.*;

public class MonoImage extends PApplet implements Resetable{

	private static final long serialVersionUID = 1L;
	private MosaicInfo mosInfo = new MosaicInfo();

	public void setup() {
		size(1000, 1000);
		noLoop();

	}

	public void draw() {
		mosInfo.imgSizeX = 200;
		mosInfo.imgSizeY = 200;
		mosInfo.xdim = 5;
		mosInfo.ydim = 5;
		DefaultMosaicDrawer md = new DefaultMosaicDrawer(mosInfo);

		md.imageProvider = new MonoImageProvider(
				"c:\\data\\unsorted\\R-356557-1194196996.jpeg");
		md.parentApplet = this;

		md.draw();

	}

	@Override
	public void reset() {
	
	}

}
