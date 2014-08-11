 package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import processing.opengl.PShader;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.enhancers.CutupEnhancer;
import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.img.enhancers.ImageOverlayEnhancer;
import nilsl.processing.lib.img.enhancers.ProcessingFilterEnhancer;
import nilsl.processing.lib.img.enhancers.TypoEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.LabelFilter;
import nilsl.processing.lib.img.filters.OrderByBriFilter;
import nilsl.processing.lib.img.filters.RandomizeFilter;
import nilsl.processing.lib.img.filters.SizeFilter;
import nilsl.processing.lib.twodim.drawers.mosaic.CutupDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.MosaicInfo;
import nilsl.processing.lib.twodim.drawers.mosaic.TintDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintInfo;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.txt.textproviders.CounterProvider;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class ItaloRecord1_Cutup extends MosaicEditorApplet {


	private static final long serialVersionUID = 1L;

	
	public void setup() {
		
		MosaicInfo mosInfo = new MosaicInfo();
		
		mosInfo.xdim = 1;
		mosInfo.ydim = 15;
	
		mosInfo.imgSizeX = 600;
		mosInfo.imgSizeY = 600/mosInfo.ydim;
		mosDrawer = new CutupDrawer(mosInfo);
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings(mosInfo, "/Users/Nils/Documents/Kunst/Italo-Disco/Compositions/Cutup/", mosDrawer);
		
		
		
		super.setup(settings);
		

		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.POSTERIZE,4));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.GRAY,3));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.THRESHOLD,0.5f));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.BLUR,5));
		
		FilterCommand sizeFilter = new SizeFilter(600,600);
		List<FilterCommand> filterCommands = new ArrayList<FilterCommand>();
		filterCommands.add(sizeFilter);
		//filterCommands.add(new LabelFilter());
		filterCommands.add(new RandomizeFilter(false));
		
		
		
		mosDrawer.imageEnhancers.add(new CutupEnhancer(settings.mosInfo.ydim,0));		
		
		try {
			imageProvider = new FilterableMultiImageProvider("/Users/Nils/Documents/Kunst/Italo-Disco/Material/Images/ItaloRecords/italorecords.dat");
			imageProvider.setFilterProcessor(new FilterProcessor(filterCommands));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageProvider.ApplyFilters();
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;
		
		
		
	}


	

	

	
	

}

