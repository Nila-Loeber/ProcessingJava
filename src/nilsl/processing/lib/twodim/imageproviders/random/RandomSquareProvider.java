package nilsl.processing.lib.twodim.imageproviders.random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.processingshims.PAppletShim;
import nilsl.processing.lib.twodim.drawers.OutsideInDrawer;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.Filterable;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.imageproviders.Resetable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gstreamer.BufferCopyFlags;

import processing.core.PGraphics;

public class RandomSquareProvider extends ImageProvider implements Filterable,
		Resetable {

	static final Logger logger = LogManager
			.getLogger(RandomSquareProvider.class.getPackage().getName());
	private Random rnd;
	private FilterProcessor filterProcessor;
	private List<? super NImage> images;
	private RandomSquareProviderInfo rspInfo;
	private Iterator<? super NImage> it;

	public RandomSquareProvider(RandomSquareProviderInfo info) {
		this.rnd = new Random(info.seed);
		this.rspInfo = info;
		images = generateImages();
		it = images.iterator();
	}

	private List<? super NImage> generateImages() {
		images = new ArrayList<NImage>();
		for (int i = 0; i < rspInfo.numPictures; i++) {
			images.add(generateImage());
		}
		return images;
	}

	private NImage generateImage() {
		PGraphics buffer = PAppletShim.getApplet().createGraphics(
				rspInfo.width, rspInfo.height);
		buffer.beginDraw();
		
		generateImageContent(buffer);
				
		buffer.endDraw();
		return new NImage(buffer);
	}

	private void generateImageContent(PGraphics buffer) {
//		buffer.fill(rnd.nextInt() % 255, rnd.nextInt() % 255,
//				rnd.nextInt() % 255);
//		buffer.rect(0, 0, buffer.height, buffer.width);
		
		int numLines = Math.max(rnd.nextInt(rspInfo.maxLines),rspInfo.minLines);
		int lineSize=rspInfo.width/numLines;
		//buffer.background(0,0,rnd.nextInt(255));
		
		buffer.background(0);
		
		buffer.strokeJoin(rnd.nextInt(3));
		buffer.strokeWeight(Math.max(rnd.nextInt(rspInfo.maxStrokeWeight),rspInfo.minStrokeWeight));
		buffer.rotate((float)(rnd.nextFloat()*rspInfo.maxRotate));
		buffer.scale(rnd.nextFloat()*rspInfo.maxScale);
		
		for (int i = 0; i <= numLines; i++) {
            
            buffer.noFill();
              
            //buffer.stroke(i % 2 * 127, rnd.nextInt(255)%2*127, 127);
            
            buffer.stroke(rspInfo.colorProvider.GetColor(rnd));
            
            buffer.rectMode(buffer.CENTER);
                                   
            buffer.rect(buffer.width / 2, buffer.height / 2, i * lineSize * 2, i * lineSize * 2);

        }
		
	}

	@Override
	public void applyFilters() {

		filterProcessor.applyFilters(images);
	}

	@Override
	public void setFilterProcessor(FilterProcessor processor) {
		this.filterProcessor = processor;
	}

	@Override
	public FilterProcessor getFilterProcessor() {
		return filterProcessor;
	}

	@Override
	public void getNextImage(PGraphics buffer) {
		buffer.beginDraw();
		buffer.image(((NImage)it.next()).getImage(),0,0);
		buffer.endDraw();
		
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public void reset() {
		it = images.iterator();

	}

}
