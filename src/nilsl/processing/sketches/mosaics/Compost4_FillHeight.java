package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import processing.opengl.PShader;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.comparators.JarFillHeightComparator;
import nilsl.processing.lib.img.enhancers.ProcessingFilterEnhancer;
import nilsl.processing.lib.img.enhancers.TypoEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.JarFillHeightFilter;
import nilsl.processing.lib.img.filters.MapFilter;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.MosaicInfo;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class Compost4_FillHeight extends MosaicEditorApplet {


	private static final long serialVersionUID = 1L;

	
	public void setup() {
	
		MosaicInfo mosInfo=new MosaicInfo();
		
		mosInfo.imgSizeX = 2448/8;
		mosInfo.imgSizeY = 3264/8;
		mosInfo.xdim = 20;
		mosInfo.ydim = 16;
		
		mosDrawer = new DefaultMosaicDrawer(mosInfo);
		
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings(mosInfo,"/Users/Nils/Documents/Kunst/Compost/Compositions/",mosDrawer);
		
		FilterCommand fillHeightFilter = new JarFillHeightFilter(true);
		FilterCommand mapFilter = new MapFilter(mosInfo.xdim*mosInfo.ydim+10);
		List<FilterCommand> filters = new ArrayList<FilterCommand>();
		filters.add(fillHeightFilter);
		filters.add(mapFilter);
		
		try {
			imageProvider = new FilterableMultiImageProvider("/Users/Nils/Documents/Kunst/Compost/Images/eighthsize/compostimages.dat");
			imageProvider.setFilterProcessor(new FilterProcessor(filters));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		imageProvider.applyFilters();
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;

		super.setup(settings);
		
	}


	

	

	
	

}

