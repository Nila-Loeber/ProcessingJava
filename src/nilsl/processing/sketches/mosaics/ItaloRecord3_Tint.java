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
import nilsl.processing.lib.img.filters.HueBPF;
import nilsl.processing.lib.img.filters.LabelFilter;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.img.filters.OrderByHueFilter;
import nilsl.processing.lib.img.filters.SizeFilter;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintInfo;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.txt.textproviders.CounterProvider;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class ItaloRecord3_Tint extends MosaicEditorApplet {


	private static final long serialVersionUID = 1L;

	
	public void setup() {
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings();
		settings.mosInfo.imgSizeX = 600;
		//settings.mosInfo.imgSizeX = (int) (1224/1.5);
		settings.mosInfo.imgSizeY = 600;
		//settings.mosInfo.imgSizeY = (int) (1632/1.5);
		settings.mosInfo.xdim = 9;
		settings.mosInfo.ydim = 6;
		
		colorMode(HSB);
		
		List<TintInfo> tintInfos = new ArrayList<TintInfo>();
		
		for (int i=0; i<9; i++)
		{
			tintInfos.add(new TintInfo(i*20+50, 255, 255, 255f));
		}
		
		//mosDrawer = new TintDrawer(mosInfo,tintInfos,settings.mosInfo.xdim*settings.mosInfo.ydim,1);
		mosDrawer = new DefaultMosaicDrawer(settings.mosInfo);
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.POSTERIZE,4));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.THRESHOLD,0.5f));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.INVERT,0));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.BLUR ,5));
		//mosDrawer.imageEnhancers.add(new TypoEnhancer("c:\\data\\fonts\\Aharoni-Bold-120.vlw",60,100,100,color(255,0,255,127),new CounterProvider(settings.mosInfo.xdim*settings.mosInfo.ydim)));
		
		FilterCommand sizeFilter = new SizeFilter(600,600);
		//FilterCommand briFilter = new OrderByBriFilter(false);
		//FilterCommand hueFilter = new OrderByHueFilter();
		FilterCommand hueFilter = new HueBPF(10, 30);
		
		//LabelFilter labelFilter = new LabelFilter();
		List<FilterCommand> filterCommands = new ArrayList<FilterCommand>();
		filterCommands.add(sizeFilter);
		filterCommands.add(hueFilter);
		//filterCommands.add(labelFilter);
		
		try {
			imageProvider = new FilterableMultiImageProvider("c:\\data\\unsorted\\recordimages.dat");
			imageProvider.setFilterProcessor(new FilterProcessor(filterCommands));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageProvider.applyFilters();
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;

		super.setup(settings);
	}


	

	

	
	

}

