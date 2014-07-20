package nilsl.processing.sketches.mosaics;

import java.util.List;

import nilsl.processing.lib.io.FileRepo;
import nilsl.processing.lib.twodim.imageproviders.CheckerBoardImageProvider;
import nilsl.processing.lib.twodim.imageproviders.MonoImageProvider;
import nilsl.processing.lib.twodim.imageproviders.MultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import processing.core.*;

public class MultiImage extends PApplet {

	private static final long serialVersionUID = 1L;
	private static final int imgSize = 200;
	private static final int xdim = 7;
	private static final int ydim = 5;
	
	public void setup() {
		size(xdim*imgSize, ydim*imgSize);
		noLoop();

	}

	public void draw() {
		DefaultMosaicDrawer md = new DefaultMosaicDrawer(xdim, ydim, imgSize, imgSize);

		List<String> files = FileRepo.listFiles("c:\\data\\labels");

		System.out.println("Files found: " + files.size());

		md.imageProvider = new MultiImageProvider(files.subList(0, xdim*ydim));
		md.parentApplet = this;

		md.draw();

	}
	
	
	

}
