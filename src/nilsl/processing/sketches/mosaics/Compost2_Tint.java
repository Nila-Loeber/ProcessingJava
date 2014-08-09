 package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import processing.opengl.PShader;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.img.enhancers.ImageOverlayEnhancer;
import nilsl.processing.lib.img.enhancers.ProcessingFilterEnhancer;
import nilsl.processing.lib.img.enhancers.TypoEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.TintDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.TintInfo;
import nilsl.processing.lib.txt.textproviders.CounterProvider;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class Compost2_Tint extends MosaicEditorApplet {


	private static final long serialVersionUID = 1L;

	
	public void setup() {
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings();
		settings.mosInfo.imgSizeX = 245;
		//settings.mosInfo.imgSizeX = (int) (1224/1.5);
		settings.mosInfo.imgSizeY = 326;
		//settings.mosInfo.imgSizeY = (int) (1632/1.5);
		settings.mosInfo.xdim = 3;
		settings.mosInfo.ydim = 3;
		
		colorMode(HSB);
		
		List<TintInfo> tintInfos = new ArrayList<TintInfo>();
		
		for (int i=0; i<10; i++)
		{
			tintInfos.add(new TintInfo(i*20, 255, 255, 255f));
		}
		
		mosDrawer = new TintDrawer(settings.mosInfo,tintInfos,settings.mosInfo.xdim*settings.mosInfo.ydim,1);
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.POSTERIZE,2));
		mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.THRESHOLD,0.5f));
		mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.INVERT,0));
		
		//mosDrawer.imageEnhancers.add(new TypoEnhancer("c:\\data\\fonts\\Aharoni-Bold-120.vlw",60,100,100,color(255,0,255,127),new CounterProvider(9)));
		
		
		try {
			imageProvider = new FilterableMultiImageProvider("c:\\data\\compost\\verysmall\\images.dat");
			imageProvider.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageProvider.ApplyFilters();
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;

		super.setup(settings);
	}


	

	

	
	

}

