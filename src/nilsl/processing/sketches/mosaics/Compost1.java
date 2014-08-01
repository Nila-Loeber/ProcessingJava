package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.img.enhancers.ImageEnhancer;
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
		mosInfo.imgSizeX = 245/2;
		mosInfo.imgSizeY = 326/2;
		mosInfo.xdim = 13;
		mosInfo.ydim = 7;
			
		//PImage overlayImage = loadImage("c:\\data\\compost\\blackborder.png");
		//ImageEnhancer blackSquare = new ImageOverlayEnhancer(overlayImage);
		ImageEnhancer zoom = new ZoomEnhancer(30,40,60);
		
		mosDrawer = new DefaultMosaicDrawer(mosInfo);
		
		mosDrawer.imageEnhancers.add(zoom);

		FilterCommand briFilter = new OrderByBriFilter();
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


	public void draw() {

		mosDrawer.draw();
	}

	

	
	

}
