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
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.twodim.drawers.Drawer;
import nilsl.processing.lib.twodim.drawers.RandomDrawerInfo;
import nilsl.processing.lib.twodim.drawers.RandomPosDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class RandomPos1 extends ViewerApplet {


	private static final long serialVersionUID = 1L;
	private RandomPosDrawer drawer;

	
	public void setup() {	
		
		RandomDrawerInfo info = new RandomDrawerInfo();
		info.imgSizeX=150;
		info.imgSizeY=150;
		info.width=2000;
		info.height=1500;
		info.numPics=150;
		info.repeatImages=true;
		info.slant=radians(360);
		

		drawer = new RandomPosDrawer(info);
		
		ViewerAppletSettings settings = new ViewerAppletSettings("/Users/Nils/Documents/Kunst/Italo-Disco/Compositions/",drawer);
		settings.width=info.width;
		settings.height=info.height;
		super.setup(settings);
		
		
		
		//drawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.POSTERIZE,2));
		
		List<FilterCommand> filters = new ArrayList<FilterCommand>();
		
		FilterableMultiImageProvider imageProvider=null;
		
		try {
			 imageProvider = new FilterableMultiImageProvider("/Users/Nils/Documents/Kunst/Italo-Disco/Material/Images/ItaloRecords/italorecords.dat");
			imageProvider.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageProvider.ApplyFilters();
		
		drawer.imageProvider = (ImageProvider) imageProvider;
		drawer.parentApplet = this;

		background(255);
		
	}
	
	@Override
	public void draw()
	{
		drawer.draw();
		super.draw();
		//noLoop();
	}


	

	

	
	

}

