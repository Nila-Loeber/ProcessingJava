package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import processing.opengl.PShader;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.enhancers.ProcessingFilterEnhancer;
import nilsl.processing.lib.img.enhancers.TypoEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class Compost1 extends MosaicEditorApplet {

	private static final long serialVersionUID = 1L;

	public void setup() {
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings();

		settings.mosInfo.imgSizeX = 245;
		settings.mosInfo.imgSizeY = 326;
		settings.mosInfo.xdim = 1;
		settings.mosInfo.ydim = 1;

		PImage overlayImage = loadImage("c:\\data\\overlays\\blacksquare.png");
		// ImageEnhancer blackSquare = new ImageOverlayEnhancer(overlayImage);

		mosDrawer = new DefaultMosaicDrawer(settings.mosInfo);

		// mosDrawer.imageEnhancers.add(new ZoomEnhancer(120,120,40));

		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.POSTERIZE,3));
		mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.BLUR,
				5));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.INVERT,10));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.GRAY,0));
		// mosDrawer.imageEnhancers.add(blackSquare);

		mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(
				PShader.THRESHOLD, 0.65f));
		mosDrawer.imageEnhancers.add(new TypoEnhancer(
				"c:\\data\\fonts\\Aharoni-Bold-120.vlw", 120, 30, 105, color(0,
						0, 0, 100), new SentenceProvider(
						"A knife, a fork, a bottle and a cork")));

		FilterCommand briFilter = new OrderByBriFilter(false);
		List<FilterCommand> filters = new ArrayList<FilterCommand>();
		filters.add(briFilter);

		try {
			imageProvider = new FilterableMultiImageProvider(
					"c:\\data\\compost\\verysmall\\images.dat");
			imageProvider.setFilterProcessor(new FilterProcessor(
					new ArrayList<FilterCommand>()));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageProvider.applyFilters();

		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;

		super.setup(settings);

	}

}
