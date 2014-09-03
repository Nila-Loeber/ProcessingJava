package nilsl.processing.sketches.mosaics;

import java.util.ArrayList;

import nilsl.processing.lib.applet.mosaic.MidifiedMosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.processingshims.PAppletShim;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.MosaicInfo;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.imageproviders.generators.GeneratorImageProvider;
import nilsl.processing.lib.twodim.imageproviders.generators.GeneratorProviderInfo;
import nilsl.processing.lib.twodim.imageproviders.generators.ImageGenerator;
import nilsl.processing.lib.twodim.imageproviders.generators.ImageGeneratorState;
import processing.core.PGraphics;

public class Generated2 extends MidifiedMosaicEditorApplet {

	private static final long serialVersionUID = 1L;
	private GeneratorProviderInfo rspInfo;

	public void setup() {

		MosaicInfo mosInfo = new MosaicInfo(this);

		mosInfo.imgSizeX = 125;
		mosInfo.imgSizeY = 125;
		mosInfo.xdim = 8;
		mosInfo.ydim = 8;
		mosDrawer = new DefaultMosaicDrawer(mosInfo);

		rspInfo = new GeneratorProviderInfo();
		rspInfo.seed = 1;
		rspInfo.width = mosInfo.imgSizeX;
		rspInfo.height = mosInfo.imgSizeY;
		rspInfo.numPictures = mosInfo.Size();
		rspInfo.imageGenerator = new ImageGenerator() {

			@Override
			public void Draw(PGraphics buffer, ImageGeneratorState state) {
				DrawRandomSquares(buffer, state);
			}

			public void DrawHsbCircles(PGraphics buffer,
					ImageGeneratorState state) {

				noStroke();

				int background= (int)map(sliders[0],0,127,0,255);
				int strokeCol = sliders[1];

				int size = rotaryKnobs[0];
				int color = rotaryKnobs[1];
				int opacity = rotaryKnobs[2];
				int divisor = Math.max(1,rotaryKnobs[3]);
				int xoffset = rotaryKnobs[4];
				int yoffset = rotaryKnobs[5];
				int numCircles = rotaryKnobs[6];
				int distance = rotaryKnobs[7];
				
				

				buffer.background(background);
				buffer.colorMode(HSB);
				buffer.stroke(strokeCol,255,255,opacity);
				if (divisor==1 || state.counter.getCurPos() % divisor != 0) {
					


					for (int i = 0; i < numCircles; i++) {
						buffer.ellipseMode(CORNER);
						buffer.fill(color, 255, 255, opacity);
						buffer.ellipse(0, 0, i
								* size + distance+xoffset, i * size
								+ distance+yoffset);
					}
				}
			}

			public void DrawHsbSquares(PGraphics buffer,
					ImageGeneratorState state) {

				int bgcolor = ((int) PAppletShim.getApplet().map(
						state.counter.getCurPos(), 0, mosInfo.Size(), 0, 255) + 127) % 255;
				System.out.println(bgcolor);
				buffer.noStroke();
				buffer.colorMode(HSB);
				buffer.fill(bgcolor, 255, 255);
				buffer.rect(0, 0, rspInfo.width, rspInfo.height);
				buffer.fill(255 - bgcolor, 255, 255);
				buffer.rect(10, 10, 20, 20);
				buffer.fill(255 - bgcolor / 2, 255, 255);
				buffer.rect(30, 30, 20, 20);
				buffer.fill(255 - bgcolor / 4, 255, 255);
				buffer.rect(50, 50, 20, 20);
				buffer.fill(255 - bgcolor / 8, 255, 255);
				buffer.rect(70, 70, 20, 20);
			}

			public void DrawRandomSquares(PGraphics buffer, ImageGeneratorState state)
			{
				
					int minLines = Math.max(1,rotaryKnobs[0]);
					int maxLines = Math.max(1,rotaryKnobs[1]);
					int numLines = (int)map(state.counter.getCurPos(),0,rspInfo.numPictures,minLines,maxLines);
					int minStrokeWeight = Math.max(1,rotaryKnobs[2]);
					int maxStrokeWeight = Math.max(1,rotaryKnobs[3]);
					int lineSize = state.gpInfo.width / numLines;
					int background = (int)map(sliders[0],0,127,0,255);
					float scale = map(sliders[3],0,127,0,5);
					int strokeColStart = (int)map(sliders[1],0,127,0,255);
					int strokeColEnd = (int)map(sliders[2],0,127,0,255);
					
					
					buffer.background(background);

					int strokeWeight= (int)map(state.counter.getCurPos(),0,rspInfo.numPictures,minStrokeWeight,maxStrokeWeight);
					
					
					buffer.strokeWeight(strokeWeight);
					
					
					 buffer.scale(scale);
					 

					int startCol = 0;
					int interval = (255 - startCol) / state.gpInfo.numPictures;

					// buffer.scale(0.5f + state.counter.getCurPos() * interval /
					// 255);

					int strokeRange = strokeColEnd-strokeColStart;
					int strokeCol= (int)map(state.counter.getCurPos(),0,rspInfo.numPictures,strokeColStart,strokeColEnd);
					
					for (int i = 0; i <= numLines; i++) {

						
						
						// buffer.strokeWeight(Math.max(30-i*3,5));

						buffer.noFill();

						// buffer.stroke(i % 2 * 127, rnd.nextInt(255)%2*127, 127);

						// buffer.stroke(state.counter.getCurPos() * interval +
						// startCol,
						// (i % 2) * state.counter.getCurPos() * interval
						// + startCol, 0);

						buffer.colorMode(HSB);
						buffer.stroke(strokeCol, 255, 255);

						buffer.rectMode(buffer.CENTER);

						// buffer.rect(buffer.width / 2, buffer.height / 2, i *
						// lineSize * 2, i * lineSize * 2);

						// buffer.ellipseMode(buffer.CENTER);
						// buffer.ellipse(buffer.width / 2, buffer.height / 2, i
						// * lineSize, i * lineSize);

						buffer.rect(buffer.width / 2, buffer.height / 2, i
								* lineSize, i * lineSize);

					}

				}
			
			
		};

		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings(
				mosInfo, "/Users/Nils/Documents/Kunst/Random/Compositions/",
				mosDrawer);

		super.setup(settings);

		imageProvider = new GeneratorImageProvider(rspInfo);
		imageProvider.setFilterProcessor(new FilterProcessor(
				new ArrayList<FilterCommand>()));

		mosDrawer.imageProvider = (ImageProvider) imageProvider;
	}
}
