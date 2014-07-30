package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PGraphics;
import processing.core.PImage;
import nilsl.processing.lib.applet.mosaic.SwapperApplet;
import nilsl.processing.lib.img.ImageEnhancer;
import nilsl.processing.lib.img.ImageOverlayEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.img.filters.OrderByHueFilter;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicDrawer2d;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicInfo;

public class Compost1 extends SwapperApplet {

	private MosaicInfo mosInfo = new MosaicInfo();
	
	private static final long serialVersionUID = 1L;

	private FilterableMultiImageProvider imgProvider;

	
	public void setup() {
		mosInfo.imgSizeX = 1224/16;
		mosInfo.imgSizeY = 1632/16;
		mosInfo.xdim = 12;
		mosInfo.ydim = 6;
		
		size(mosInfo.xdim * mosInfo.imgSizeX, mosInfo.ydim * mosInfo.imgSizeY);

		
		PImage overlayImage = loadImage("c:\\data\\compost\\blackborder.png");
		ImageEnhancer blackSquare = new ImageOverlayEnhancer(overlayImage);
		
		
		md = new DefaultMosaicDrawer(mosInfo);
		
		md.imageEnhancers.add(blackSquare);

		//FilterCommand labelFilter = new RandomizeFilter(true);
		FilterCommand hueFilter = new OrderByBriFilter();
		List<FilterCommand> filters = new ArrayList<FilterCommand>();
		filters.add(hueFilter);
		
		
		
		try {
			imgProvider = new FilterableMultiImageProvider("c:\\data\\compost\\verysmall\\images.dat",filters);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imgProvider.ApplyFilters();
		
		md.imageProvider = imgProvider;
		md.parentApplet = this;

	}

	DefaultMosaicDrawer md;

	public void draw() {

		md.draw();
	}

	@Override
	protected FilterableMultiImageProvider getimageProvider() {
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
