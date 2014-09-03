package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import processing.opengl.PShader;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.enhancers.CutupEnhancer;
import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.img.enhancers.ImageOverlayEnhancer;
import nilsl.processing.lib.img.enhancers.ProcessingFilterEnhancer;
import nilsl.processing.lib.img.enhancers.TypoEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.twodim.drawers.mosaic.CutupDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintInfo;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.txt.textproviders.CounterProvider;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class Compost3_Cutup extends MosaicEditorApplet {

	private static final long serialVersionUID = 1L;

	public void setup() {
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings();
		settings.mosInfo.xdim = 1;
		settings.mosInfo.ydim = 7;

		// settings.mosInfo.imgSizeX = 245;
		settings.mosInfo.imgSizeX = (int) (1224 / 1.5);
		// settings.mosInfo.imgSizeY = 326;
		settings.mosInfo.imgSizeY = (int) (1632 / 1.5 / settings.mosInfo.ydim);

		mosDrawer = new CutupDrawer(settings.mosInfo);
		mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(
				PShader.POSTERIZE, 4));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.GRAY,3));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.THRESHOLD,0.5f));
		mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.BLUR,
				5));

		mosDrawer.imageEnhancers.add(new CutupEnhancer(settings.mosInfo.ydim));

		try {
			imageProvider = new FilterableMultiImageProvider(
					"c:\\data\\compost\\small\\images.dat");
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
