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

		mosInfo.imgSizeX = 200;
		mosInfo.imgSizeY = 200;
		mosInfo.xdim = 5;
		mosInfo.ydim = 5;
		mosDrawer = new DefaultMosaicDrawer(mosInfo);

		rspInfo = new GeneratorProviderInfo();
		rspInfo.seed = 1;
		rspInfo.width = mosInfo.imgSizeX;
		rspInfo.height = mosInfo.imgSizeY;
		rspInfo.numPictures = mosInfo.Size();
		rspInfo.imageGenerator = new ImageGenerator() {

			@Override
			public void Draw(PGraphics buffer, ImageGeneratorState state) {
				DrawHsbCircles(buffer, state);
			}

			public void DrawHsbCircles(PGraphics buffer,
					ImageGeneratorState state) {

				noStroke();

				int numCircles = 5;
				int distance = 10;
				buffer.colorMode(RGB);
				buffer.background(64 + 127 * (state.counter.getCurPos() % 2));

				buffer.colorMode(HSB);

				//int color = (int) map(mouseY, 0, height, 0, 255);
				// int color=(int)map(midiS1,0,127,0,255);
				//int size = (int) map(mouseX, 0, width, 1, 50);

				for (int i = 0; i < numCircles; i++) {
					// buffer.fill(state.rnd.nextInt(255),255,255);
					buffer.fill(sliders[0], 255, 255, sliders[2]);
					buffer.ellipse(buffer.width / 2, buffer.height / 2, i
							* sliders[1], i * sliders[1]);
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
