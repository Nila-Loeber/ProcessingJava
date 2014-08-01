package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import processing.opengl.PShader;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.img.enhancers.ImageOverlayEnhancer;
import nilsl.processing.lib.img.enhancers.ProcessingFilterEnhancer;
import nilsl.processing.lib.img.enhancers.ZoomEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.img.filters.RandomizeFilter;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicInfo;

public class Compost1 extends MosaicEditorApplet {


	private static final long serialVersionUID = 1L;

	
	public void setup() {
		mosInfo.imgSizeX = 245;
		mosInfo.imgSizeY = 326;
		mosInfo.xdim = 3;
		mosInfo.ydim = 3;
			
		PImage overlayImage = loadImage("c:\\data\\overlays\\blacksquare.png");
		ImageEnhancer blackSquare = new ImageOverlayEnhancer(overlayImage);
		
		mosDrawer = new DefaultMosaicDrawer(mosInfo);
		
		//mosDrawer.imageEnhancers.add(new ZoomEnhancer(120,120,40));
		
		
		mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.POSTERIZE,3));
		mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.GRAY,0));
		
		//mosDrawer.imageEnhancers.add(blackSquare);
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.INVERT,10));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.BLUR,5));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.THRESHOLD,0.65f));
		
		FilterCommand briFilter = new OrderByBriFilter(false);
		List<FilterCommand> filters = new ArrayList<FilterCommand>();
		filters.add(briFilter);
		
		try {
			imageProvider = new FilterableMultiImageProvider("c:\\data\\compost\\verysmall\\images.dat",filters);
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

