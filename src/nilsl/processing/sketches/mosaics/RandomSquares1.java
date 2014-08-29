package nilsl.processing.sketches.mosaics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import nilsl.processing.lib.twodim.imageproviders.random.ColorProvider;
import nilsl.processing.lib.twodim.imageproviders.random.RandomSquareProvider;
import nilsl.processing.lib.twodim.imageproviders.random.RandomSquareProviderInfo;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class RandomSquares1 extends MosaicEditorApplet {

	private static final long serialVersionUID = 1L;

	public void setup() {

		MosaicInfo mosInfo = new MosaicInfo();



		mosInfo.imgSizeX = 300;
		mosInfo.imgSizeY = 300;
		mosInfo.xdim = 4;
		mosInfo.ydim = 4;
		mosDrawer = new DefaultMosaicDrawer(mosInfo);
		
		RandomSquareProviderInfo rspInfo = new RandomSquareProviderInfo();
		rspInfo.seed = 1;
		rspInfo.width = mosInfo.imgSizeX;
		rspInfo.height = mosInfo.imgSizeY;
		rspInfo.numPictures = mosInfo.Size();
		rspInfo.maxLines=20;
		rspInfo.minLines=10;
		rspInfo.maxStrokeWeight=20;
		rspInfo.minStrokeWeight=10;
		rspInfo.maxScale=2f;
		rspInfo.maxRotate=0.5f;
		rspInfo.colorProvider = new ColorProvider()
		{
			@Override
			public int GetColor(Random rnd) {
				// TODO Auto-generated method stub
				return color(0, (int)(sin(rnd.nextInt(127))*255), 0);
			}
			
		};
		
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings(
				mosInfo, "/Users/Nils/Documents/Kunst/Random/Compositions/" + rspInfo.seed + "_",
				mosDrawer);

		super.setup(settings);

		// mosDrawer.imageEnhancers.add(new ZoomEnhancer(120,120,40));

		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.POSTERIZE,3));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.BLUR,5));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.INVERT,10));
		//mosDrawer.imageEnhancers.add(new
		//ProcessingFilterEnhancer(PShader.GRAY,0));
		// mosDrawer.imageEnhancers.add(blackSquare);

		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.THRESHOLD,0.65f));
//		mosDrawer.imageEnhancers.add(new
//		TypoEnhancer("/Users/Nils/Documents/Kunst/Fonts/ACIDLABEL-120.vlw",120,0,130,color(255,255,255,100),new
//		 SentenceProvider("Erhaben heit der Stile")));

		
		imageProvider = new RandomSquareProvider(rspInfo);
		imageProvider.setFilterProcessor(new FilterProcessor(
				new ArrayList<FilterCommand>()));
		imageProvider.applyFilters();

		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;
	}

}
