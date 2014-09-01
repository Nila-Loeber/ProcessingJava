package nilsl.processing.sketches.mosaics;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import processing.core.PGraphics;
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
import nilsl.processing.lib.twodim.imageproviders.random.GeneratorImageProvider;
import nilsl.processing.lib.twodim.imageproviders.random.GeneratorProviderInfo;
import nilsl.processing.lib.twodim.imageproviders.random.ImageGenerator;
import nilsl.processing.lib.twodim.imageproviders.random.ImageGeneratorState;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class RandomSquares1 extends MosaicEditorApplet {

	private static final long serialVersionUID = 1L;

	public void setup() {

		MosaicInfo mosInfo = new MosaicInfo();



		mosInfo.imgSizeX = 150;
		mosInfo.imgSizeY = 150;
		mosInfo.xdim = 12;
		mosInfo.ydim = 12;
		mosDrawer = new DefaultMosaicDrawer(mosInfo);
		
		GeneratorProviderInfo rspInfo = new GeneratorProviderInfo();
		rspInfo.seed = 1;
		rspInfo.width = mosInfo.imgSizeX;
		rspInfo.height = mosInfo.imgSizeY;
		rspInfo.numPictures = mosInfo.Size();		
		rspInfo.imageGenerator= new ImageGenerator()
		{

			@Override
			public void Draw(PGraphics buffer, ImageGeneratorState state) {
				
					int minLines=20;
					int maxLines=20;
					int numLines = Math.max(state.rnd.nextInt(maxLines),minLines);
					int lineSize=state.gpInfo.width/numLines;
					
					buffer.background(0);
					
					//buffer.strokeJoin(4);
					int maxStrokeWeight=1;
					int minStrokeWeight=1;
					//buffer.strokeWeight(Math.max(state.rnd.nextInt(maxStrokeWeight),minStrokeWeight));
					buffer.strokeWeight(Math.max(maxStrokeWeight,state.counter.getCurPos()*2));
					float maxRotate=1;
					//buffer.rotate(state.counter.getCurPos()/buffer.RAD_TO_DEG * (1+(-1) * state.rnd.nextInt(3)));
					//buffer.rotate(state.counter.getCurPos()/buffer.RAD_TO_DEG);
//					float maxScale=0.5f;
//					buffer.scale(state.rnd.nextFloat()*maxScale);

					int interval = 255 / state.gpInfo.numPictures;
					
					buffer.scale(0.5f+state.counter.getCurPos()*interval/255);
					
					for (int i = 0; i <= numLines; i++) {
			            
			            buffer.noFill();
			              
			            //buffer.stroke(i % 2 * 127, rnd.nextInt(255)%2*127, 127);
			            
			            
			            
			            System.out.println(state.gpInfo.height*state.gpInfo.width);
			            
			            buffer.stroke(state.counter.getCurPos()*interval+50,(i%2)*state.counter.getCurPos()*interval+50,0);
			            
			            //buffer.rectMode(buffer.CENTER);
			                                   
			            //buffer.rect(buffer.width / 2, buffer.height / 2, i * lineSize * 2, i * lineSize * 2);

			            
			            buffer.ellipseMode(buffer.CENTER);
			            buffer.ellipse(buffer.width / 2, buffer.height / 2, i * lineSize * 2, i * lineSize * 2);
			        
					
				}
				
			}
			
		};
		
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings(
				mosInfo, "/Users/Nils/Documents/Kunst/Random/Compositions/",
				mosDrawer);

		super.setup(settings);

		// mosDrawer.imageEnhancers.add(new ZoomEnhancer(120,120,40));

		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.POSTERIZE,3));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.BLUR,5));
		 //mosDrawer.imageEnhancers.add(new
		 //ProcessingFilterEnhancer(PShader.INVERT,10));
//		mosDrawer.imageEnhancers.add(new
//		ProcessingFilterEnhancer(PShader.GRAY,0));
		// mosDrawer.imageEnhancers.add(blackSquare);

		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.THRESHOLD,0.65f));
//		mosDrawer.imageEnhancers.add(new
//		TypoEnhancer("/Users/Nils/Documents/Kunst/Fonts/ACIDLABEL-120.vlw",120,0,130,color(255,255,255,100),new
//		 SentenceProvider("Erhaben heit der Stile")));

		
		imageProvider = new GeneratorImageProvider(rspInfo);
		imageProvider.setFilterProcessor(new FilterProcessor(
				new ArrayList<FilterCommand>()));
		imageProvider.applyFilters();

		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;
	}

}
