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
import nilsl.processing.lib.img.filters.LabelFilter;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.img.filters.SizeFilter;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.TintDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.TintInfo;
import nilsl.processing.lib.txt.textproviders.CounterProvider;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

@SuppressWarnings("unused")
public class ItaloRecord3_Tint extends MosaicEditorApplet {


	private static final long serialVersionUID = 1L;

	
	public void setup() {
		mosInfo.imgSizeX = 600;
		//mosInfo.imgSizeX = (int) (1224/1.5);
		mosInfo.imgSizeY = 600;
		//mosInfo.imgSizeY = (int) (1632/1.5);
		mosInfo.xdim = 8;
		mosInfo.ydim = 6;
		
		colorMode(HSB);
		
		List<TintInfo> tintInfos = new ArrayList<TintInfo>();
		
		for (int i=0; i<8; i++)
		{
			tintInfos.add(new TintInfo(i*20, 255, 255, 255f));
		}
		
		//mosDrawer = new TintDrawer(mosInfo,tintInfos,mosInfo.xdim*mosInfo.ydim,1);
		mosDrawer = new DefaultMosaicDrawer(mosInfo);
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.POSTERIZE,2));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.THRESHOLD,0.6f));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.INVERT,0));
		//mosDrawer.imageEnhancers.add(new TypoEnhancer("c:\\data\\fonts\\Aharoni-Bold-120.vlw",60,100,100,color(255,0,255,127),new CounterProvider(9)));
		
		FilterCommand sizeFilter = new SizeFilter(600,600);
		LabelFilter labelFilter = new LabelFilter();
		List<FilterCommand> filterCommands = new ArrayList<FilterCommand>();
		filterCommands.add(sizeFilter);
		filterCommands.add(labelFilter);
		
		try {
			imageProvider = new FilterableMultiImageProvider("c:\\data\\unsorted\\images.dat");
			imageProvider.setFilterProcessor(new FilterProcessor(filterCommands));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageProvider.ApplyFilters();
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;

		super.setup();
	}


	

	

	
	

}

