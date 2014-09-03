package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import processing.opengl.PShader;
import nilsl.processing.lib.applet.ViewerApplet;
import nilsl.processing.lib.applet.ViewerAppletSettings;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.enhancers.ProcessingFilterEnhancer;
import nilsl.processing.lib.img.enhancers.TypoEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.LabelFilter;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.img.filters.RandomizeFilter;
import nilsl.processing.lib.twodim.drawers.Drawer;
import nilsl.processing.lib.twodim.drawers.OutsideInDrawer;
import nilsl.processing.lib.twodim.drawers.OutsideInDrawerInfo;
import nilsl.processing.lib.twodim.drawers.RandomDrawerInfo;
import nilsl.processing.lib.twodim.drawers.RandomPosDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class OutsideIn1 extends ViewerApplet {

	private static final long serialVersionUID = 1L;
	private OutsideInDrawer drawer;

	public void setup() {

		OutsideInDrawerInfo info = new OutsideInDrawerInfo();
		info.imgSizeX = 600;
		info.imgSizeY = 600;
		info.numPics = 5;
		info.imgSizeXdest = 200;
		info.imgSizeYdest = 200;

		drawer = new OutsideInDrawer(info);

		ViewerAppletSettings settings = new ViewerAppletSettings(
				"/Users/Nils/Documents/Kunst/Italo-Disco/Compositions/OutsideIn/",
				drawer);
		settings.width = info.imgSizeX;
		settings.height = info.imgSizeY;
		super.setup(settings);

		// drawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.POSTERIZE,4));
		// drawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.GRAY,4));

		List<FilterCommand> filters = new ArrayList<FilterCommand>();

		filters.add(new LabelFilter());
		filters.add(new RandomizeFilter(false));

		FilterableMultiImageProvider imageProvider = null;

		try {
			imageProvider = new FilterableMultiImageProvider(
					"/Users/Nils/Documents/Kunst/Italo-Disco/Material/Images/ItaloRecords/italorecords.dat");
			imageProvider.setFilterProcessor(new FilterProcessor(filters));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		imageProvider.applyFilters();

		drawer.imageProvider = (ImageProvider) imageProvider;
		drawer.parentApplet = this;

		background(255);
		frameRate(0.2f);
	}

	@Override
	public void draw() {
		drawer.draw();
		super.draw();
		// noLoop();
	}

}
