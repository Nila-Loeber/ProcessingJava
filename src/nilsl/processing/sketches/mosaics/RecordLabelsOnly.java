package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.LabelFilter;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;

public class RecordLabelsOnly extends MosaicEditorApplet {

	private static final long serialVersionUID = 1L;

	Integer oldPos = null;
	Integer newPos = null;

	public void setup() {
		mosInfo.imgSizeX = 200;
		mosInfo.imgSizeY = 200;
		mosInfo.xdim = 5;
		mosInfo.ydim = 5;

		size(mosInfo.xdim * mosInfo.imgSizeX, mosInfo.ydim * mosInfo.imgSizeY);

		mosDrawer = new DefaultMosaicDrawer(mosInfo);

		// List<String> files = FileRepo.listFiles("c:\\data\\unsorted");
		//
		// System.out.println("Files found: " + files.size());

		FilterCommand labelFilter = new LabelFilter();
		List<FilterCommand> filters = new ArrayList<FilterCommand>();
		filters.add(labelFilter);
		// imgProvider = new
		// FilterableMultiImageProvider(files.subList(0,500),filters);
		try {
			imageProvider = new FilterableMultiImageProvider(
					"c:\\data\\unsorted\\images.dat");
			imageProvider.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// imgProvider = new FilterableMultiImageProvider(files,filters);
		imageProvider.ApplyFilters();

		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;

	}


	public void draw() {

		mosDrawer.draw();
	}



	
	
}
