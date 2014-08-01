package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PGraphics;
import processing.core.PImage;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.img.enhancers.ImageOverlayEnhancer;
import nilsl.processing.lib.img.enhancers.ZoomEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.img.filters.OrderByHueFilter;
import nilsl.processing.lib.img.filters.RandomizeFilter;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicDrawer2d;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicInfo;

public class Compost1 extends MosaicEditorApplet {

	private MosaicInfo mosInfo = new MosaicInfo();
	
	private static final long serialVersionUID = 1L;

	private FilterableMultiImageProvider imgProvider;

	
	public void setup() {
		mosInfo.imgSizeX = 245/2;
		mosInfo.imgSizeY = 326/2;
		mosInfo.xdim = 13;
		mosInfo.ydim = 7;
		
		size(mosInfo.xdim * mosInfo.imgSizeX, mosInfo.ydim * mosInfo.imgSizeY);

		
		//PImage overlayImage = loadImage("c:\\data\\compost\\blackborder.png");
		//ImageEnhancer blackSquare = new ImageOverlayEnhancer(overlayImage);
		ImageEnhancer zoom = new ZoomEnhancer(30,40,60);
		
		md = new DefaultMosaicDrawer(mosInfo);
		
		md.imageEnhancers.add(zoom);

		FilterCommand randFilter = new RandomizeFilter(true);
		FilterCommand briFilter = new OrderByBriFilter();
		List<FilterCommand> filters = new ArrayList<FilterCommand>();
		filters.add(briFilter);
		filters.add(randFilter);
		
		
		
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
