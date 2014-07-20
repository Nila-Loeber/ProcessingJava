package nilsl.processing.sketches.mosaics;

import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.applet.FilesaveApplet;
import nilsl.processing.lib.img.filters.Filter;
import nilsl.processing.lib.img.filters.RandomizeFilter;
import nilsl.processing.lib.io.FileRepo;
import nilsl.processing.lib.twodim.imageproviders.CheckerBoardImageProvider;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.MonoImageProvider;
import nilsl.processing.lib.twodim.imageproviders.MultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicDrawer2d;
import processing.core.*;
import nilsl.processing.lib.twodim.mosaicdrawers.Zoomable;

public class MultiImage extends FilesaveApplet {

	private static final long serialVersionUID = 1L;
	private static final int imgSize = 200;
	private static final int xdim = 3;
	private static final int ydim = 3;

	private FilterableMultiImageProvider imgProvider;
	
	public void setup() {
		size(xdim * imgSize, ydim * imgSize);

		md = new DefaultMosaicDrawer(xdim, ydim, imgSize, imgSize);

		List<String> files = FileRepo.listFiles("c:\\data\\labels");

		System.out.println("Files found: " + files.size());

		Filter randomizeFilter = new RandomizeFilter();
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(randomizeFilter);
		imgProvider = new FilterableMultiImageProvider(files.subList(0, xdim * ydim),filters);
		
		md.imageProvider = imgProvider;
		md.parentApplet = this;

	}

	MosaicDrawer2d md;

	public void draw() {

		md.draw();
	}

	@Override
	public void keyPressed() {
		if (key == '+') {
			background(0);
			((Zoomable) md).zoom();
		}
		if (key == '-') {
			background(0);
			((Zoomable) md).unzoom();
		}
		if (key == 'r') {
			background(0);
			imgProvider.ApplyFilters();
		}
		super.keyPressed();
	}

}
