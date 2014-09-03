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
import nilsl.processing.lib.twodim.imageproviders.generators.ColorProvider;
import nilsl.processing.lib.twodim.imageproviders.generators.GeneratorImageProvider;
import nilsl.processing.lib.twodim.imageproviders.generators.GeneratorProviderInfo;
import nilsl.processing.lib.twodim.imageproviders.generators.ImageGenerator;
import nilsl.processing.lib.twodim.imageproviders.generators.ImageGeneratorState;
import nilsl.processing.lib.txt.textproviders.SentenceProvider;

public class Generated1 extends MosaicEditorApplet {

	private static final long serialVersionUID = 1L;

	public void setup() {

		MosaicInfo mosInfo = new MosaicInfo();

		mosInfo.imgSizeX = 300;
		mosInfo.imgSizeY = 300;
		mosInfo.xdim = 5;
		mosInfo.ydim = 5;
		mosDrawer = new DefaultMosaicDrawer(mosInfo);

		GeneratorProviderInfo rspInfo = new GeneratorProviderInfo();
		rspInfo.seed = 1;
		rspInfo.width = mosInfo.imgSizeX;
		rspInfo.height = mosInfo.imgSizeY;
		rspInfo.numPictures = mosInfo.Size();
		rspInfo.imageGenerator = new ImageGenerator() {

			@Override
			public void Draw(PGraphics buffer, ImageGeneratorState state) {

				int minLines = 8;
				int maxLines = 8;
				int numLines = Math.max(state.rnd.nextInt(maxLines), minLines);
				int lineSize = state.gpInfo.width / numLines;
				// int lineSize = 1;

				// buffer.background(state.counter.getCurPos()%2 * 255,
				// 0-(state.counter.getCurPos()%2 * 255),0);
				// buffer.background(state.counter.getCurPos()%2 * 255,127,0);;
				buffer.background(0);

				// buffer.strokeJoin(4);
				int maxStrokeWeight = 5;
				int minStrokeWeight = 1;
				buffer.strokeWeight(Math.max(
						state.rnd.nextInt(maxStrokeWeight), minStrokeWeight));
				// buffer.strokeWeight(Math.max(maxStrokeWeight,
				// maxStrokeWeight));
				float maxRotate = 1;
				// buffer.rotate(state.counter.getCurPos()/buffer.RAD_TO_DEG *
				// (1+(-1) * state.rnd.nextInt(3)));
				// buffer.rotate(state.counter.getCurPos()/buffer.RAD_TO_DEG);
				// float minScale=2f;
				// float maxScale=5f;
				// buffer.scale(Math.max(state.rnd.nextFloat()*maxScale,minScale));

				int startCol = 0;
				int interval = (255 - startCol) / state.gpInfo.numPictures;

				// buffer.scale(0.5f + state.counter.getCurPos() * interval /
				// 255);

				for (int i = 0; i <= numLines; i++) {

					// buffer.strokeWeight(Math.max(30-i*3,5));

					buffer.noFill();

					// buffer.stroke(i % 2 * 127, rnd.nextInt(255)%2*127, 127);

					// buffer.stroke(state.counter.getCurPos() * interval +
					// startCol,
					// (i % 2) * state.counter.getCurPos() * interval
					// + startCol, 0);

					buffer.stroke(0, 255, 0);

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

		// mosDrawer.imageEnhancers.add(new ZoomEnhancer(120,120,40));

		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.POSTERIZE,3));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.BLUR,5));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.INVERT,10));
		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.GRAY,0));
		// mosDrawer.imageEnhancers.add(blackSquare);

		// mosDrawer.imageEnhancers.add(new
		// ProcessingFilterEnhancer(PShader.THRESHOLD,0.65f));
		// mosDrawer.imageEnhancers.add(new
		// TypoEnhancer("/Users/Nils/Documents/Kunst/Fonts/ACIDLABEL-120.vlw",120,0,130,color(255,255,255,100),new
		// SentenceProvider("Erhaben heit der Stile")));

		imageProvider = new GeneratorImageProvider(rspInfo);
		imageProvider.setFilterProcessor(new FilterProcessor(
				new ArrayList<FilterCommand>()));
		imageProvider.applyFilters();

		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;
	}

}
