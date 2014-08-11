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
import nilsl.processing.lib.twodim.drawers.mosaic.MosaicInfo;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class Sinus1 extends MosaicEditorApplet {


	private static final long serialVersionUID = 1L;

	
	public void setup() {
		
		MosaicInfo mosInfo = new MosaicInfo();
		
		mosInfo.imgSizeX = 1000/4;
		mosInfo.imgSizeY = 720/4;
		mosInfo.xdim = 6;
		mosInfo.ydim = 6;
		mosDrawer = new DefaultMosaicDrawer(mosInfo);
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings(mosInfo,"/Users/Nils/Documents/Kunst/Italo-Disco/Compositions/Cutup/",mosDrawer);
		
	
		super.setup(settings);
		
		
		//mosDrawer.imageEnhancers.add(new ZoomEnhancer(120,120,40));
		
		
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.POSTERIZE,3));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.BLUR,5));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.INVERT,10));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.GRAY,0));
		//mosDrawer.imageEnhancers.add(blackSquare);
		
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.THRESHOLD,0.65f));
		//mosDrawer.imageEnhancers.add(new TypoEnhancer("c:\\data\\fonts\\Aharoni-Bold-120.vlw",120,30,105,color(0,0,0,100),new SentenceProvider("A knife, a fork, a bottle and a cork")));
		
		FilterCommand briFilter = new OrderByBriFilter(false);
		List<FilterCommand> filters = new ArrayList<FilterCommand>();
		filters.add(briFilter);
		
		try {
			imageProvider = new FilterableMultiImageProvider("/Users/Nils/Documents/Kunst/Sinus/src/sinusimages.dat");
			imageProvider.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageProvider.ApplyFilters();
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;		
	}


	

	

	
	

}

