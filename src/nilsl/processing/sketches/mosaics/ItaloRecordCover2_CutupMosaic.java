package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;

import processing.opengl.PShader;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.img.enhancers.CutupEnhancer;
import nilsl.processing.lib.img.enhancers.ProcessingFilterEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;

@SuppressWarnings("serial")
public class ItaloRecordCover2_CutupMosaic extends MosaicEditorApplet {
	public void setup() {
		mosInfo.xdim = 6;
		mosInfo.ydim = 4;

		mosInfo.imgSizeX = 600;
		mosInfo.imgSizeY = 600;

		mosDrawer = new DefaultMosaicDrawer(mosInfo);
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.POSTERIZE, 4));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.GRAY,3));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.THRESHOLD,0.5f));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.BLUR, 5));

		try {
			imageProvider = new FilterableMultiImageProvider(
					"c:\\data\\compositions\\cutups\\images.dat");
			imageProvider.setFilterProcessor(new FilterProcessor(
					new ArrayList<FilterCommand>()));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageProvider.ApplyFilters();

		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;

		super.setup();
	}
}
