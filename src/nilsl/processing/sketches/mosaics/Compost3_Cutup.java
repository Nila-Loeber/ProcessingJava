 package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import processing.opengl.PShader;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.img.enhancers.CutupEnhancer;
import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.img.enhancers.ImageOverlayEnhancer;
import nilsl.processing.lib.img.enhancers.ProcessingFilterEnhancer;
import nilsl.processing.lib.img.enhancers.TypoEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.CutupDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.TintDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.TintInfo;
import nilsl.processing.lib.txt.textproviders.CounterProvider;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

@SuppressWarnings("unused")
public class Compost3_Cutup extends MosaicEditorApplet {


	private static final long serialVersionUID = 1L;

	
	public void setup() {
		mosInfo.xdim = 1;
		mosInfo.ydim = 17;
				
		//mosInfo.imgSizeX = 245;
		mosInfo.imgSizeX = (int) (1224/1.5);
		// mosInfo.imgSizeY = 326;
		mosInfo.imgSizeY = (int) (1632/1.5/mosInfo.ydim);
		
		mosDrawer = new CutupDrawer(mosInfo);
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.POSTERIZE,2));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.GRAY,0));
//		mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.THRESHOLD,0.5f));
//		mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.INVERT,0));
		
		mosDrawer.imageEnhancers.add(new CutupEnhancer(mosInfo.ydim));		
		
		try {
			imageProvider = new FilterableMultiImageProvider("c:\\data\\compost\\small\\images.dat",new ArrayList<FilterCommand>());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageProvider.ApplyFilters();
		
		mosDrawer.imageProvider = imageProvider;
		mosDrawer.parentApplet = this;

		super.setup();
	}


	

	

	
	

}

