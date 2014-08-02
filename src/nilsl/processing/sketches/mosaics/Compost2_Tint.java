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
import nilsl.processing.lib.img.enhancers.TypoEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.TintDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.TintInfo;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

@SuppressWarnings("unused")
public class Compost2_Tint extends MosaicEditorApplet {


	private static final long serialVersionUID = 1L;

	
	public void setup() {
		mosInfo.imgSizeX = 245/4;
		//mosInfo.imgSizeX = (int) (1224/1.5);
		mosInfo.imgSizeY = 326/4;
		//mosInfo.imgSizeY = (int) (1632/1.5);
		mosInfo.xdim = 18;
		mosInfo.ydim = 9;
		
		colorMode(HSB);
		
		List<TintInfo> tintInfos = new ArrayList<TintInfo>();
		
		for (int i=0; i<100; i++)
		{
			tintInfos.add(new TintInfo(i*2, 255, sin(i/5)*255, 255f));
		}
		
		mosDrawer = new TintDrawer(mosInfo,tintInfos,mosInfo.xdim*mosInfo.ydim,1);
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.POSTERIZE,2));
		mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.THRESHOLD,0.65f));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.INVERT,0));
		
		mosDrawer.imageEnhancers.add(new TypoEnhancer("c:\\data\\fonts\\Aharoni-Bold-120.vlw",60,30,30,color(255,255,255,255),new SentenceProvider("A knife, a fork, a bottle and a cork")));
		
		
		try {
			imageProvider = new FilterableMultiImageProvider("c:\\data\\compost\\verysmall\\images.dat",new ArrayList<FilterCommand>());
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

