package nilsl.processing.lib.twodim.imageproviders.random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.onedim.counters.Counter1d;
import nilsl.processing.lib.onedim.counters.DefaultCounter1d;
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

public class GeneratorImageProvider extends ImageProvider implements Filterable,
		Resetable {

	static final Logger logger = LogManager
			.getLogger(GeneratorImageProvider.class.getPackage().getName());
	private Random rnd;
	private FilterProcessor filterProcessor;
	private List<? super NImage> images;
	private GeneratorProviderInfo gpInfo;
	private Iterator<? super NImage> it;
	private Counter1d counter;

	public GeneratorImageProvider(GeneratorProviderInfo info) {
		this.rnd = new Random(info.seed);
		this.counter = new DefaultCounter1d();
		this.gpInfo = info;
		images = generateImages();
		it = images.iterator();
	}

	private List<? super NImage> generateImages() {
		images = new ArrayList<NImage>();
		for (int i = 0; i < gpInfo.numPictures; i++) {
			images.add(generateImage());
			counter.inc();
		}
		return images;
	}

	private NImage generateImage() {
		PGraphics buffer = PAppletShim.getApplet().createGraphics(
				gpInfo.width, gpInfo.height);
		buffer.beginDraw();
		
		ImageGeneratorState state = new ImageGeneratorState();
		state.counter=counter;
		state.rnd=rnd;		
		state.gpInfo=gpInfo;
		
		gpInfo.imageGenerator.Draw(buffer, state);
				
		buffer.endDraw();
		return new NImage(buffer);
	}

//	private void generateImageContent(PGraphics buffer) {
////		buffer.fill(rnd.nextInt() % 255, rnd.nextInt() % 255,
////				rnd.nextInt() % 255);
////		buffer.rect(0, 0, buffer.height, buffer.width);
//		
//		int numLines = Math.max(rnd.nextInt(gipInfo.maxLines),gipInfo.minLines);
//		int lineSize=gipInfo.width/numLines;
//		//buffer.background(0,0,rnd.nextInt(255));
//		
//		buffer.background(0);
//		
//		buffer.strokeJoin(rnd.nextInt(3));
//		buffer.strokeWeight(Math.max(rnd.nextInt(gipInfo.maxStrokeWeight),gipInfo.minStrokeWeight));
//		buffer.rotate((float)(rnd.nextFloat()*gipInfo.maxRotate));
//		buffer.scale(rnd.nextFloat()*gipInfo.maxScale);
//		
//		for (int i = 0; i <= numLines; i++) {
//            
//            buffer.noFill();
//              
//            //buffer.stroke(i % 2 * 127, rnd.nextInt(255)%2*127, 127);
//            
//            buffer.stroke(gipInfo.colorProvider.GetColor(rnd));
//            
//            buffer.rectMode(buffer.CENTER);
//                                   
//            buffer.rect(buffer.width / 2, buffer.height / 2, i * lineSize * 2, i * lineSize * 2);
//
//        }
//		
//	}

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
