package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.applet.FilesaveApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.LabelFilter;
import nilsl.processing.lib.img.filters.SwapFilter;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicDrawer2d;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicInfo;
import nilsl.processing.lib.twodim.mosaicdrawers.Zoomable;

public class RecordLabelsOnly extends MosaicEditorApplet {

	private static final long serialVersionUID = 1L;

	private MosaicInfo mosInfo = new MosaicInfo();

	private FilterableMultiImageProvider imgProvider;
	Integer oldPos = null;
	Integer newPos = null;

	public void setup() {
		mosInfo.imgSizeX = 200;
		mosInfo.imgSizeY = 200;
		mosInfo.xdim = 5;
		mosInfo.ydim = 5;

		size(mosInfo.xdim * mosInfo.imgSizeX, mosInfo.ydim * mosInfo.imgSizeY);

		md = new DefaultMosaicDrawer(mosInfo);

		// List<String> files = FileRepo.listFiles("c:\\data\\unsorted");
		//
		// System.out.println("Files found: " + files.size());

		FilterCommand labelFilter = new LabelFilter();
		List<FilterCommand> filters = new ArrayList<FilterCommand>();
		filters.add(labelFilter);
		// imgProvider = new
		// FilterableMultiImageProvider(files.subList(0,500),filters);
		try {
			imgProvider = new FilterableMultiImageProvider(
					"c:\\data\\unsorted\\images.dat", filters);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// imgProvider = new FilterableMultiImageProvider(files,filters);
		imgProvider.ApplyFilters();

		md.imageProvider = imgProvider;
		md.parentApplet = this;

	}

	MosaicDrawer2d md;

	public void draw() {

		md.draw();
	}

	@Override
	protected FilterableMultiImageProvider getImageProvider() {
		return imgProvider;
	}

	@Override
	protected MosaicInfo getMosInfo() {
		return mosInfo;
	}

	@Override
	protected MosaicDrawer2d getMosDrawer() {
		return md;
	}
	
}
