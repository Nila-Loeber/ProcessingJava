package nilsl.processing.lib.twodim.imageproviders.generators;

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
import nilsl.processing.lib.twodim.imageproviders.ReInitable;
import nilsl.processing.lib.twodim.imageproviders.Resetable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gstreamer.BufferCopyFlags;

import processing.core.PGraphics;

public class GeneratorImageProvider extends ImageProvider implements
		Filterable, Resetable, ReInitable {

	static final Logger logger = LogManager
			.getLogger(GeneratorImageProvider.class.getPackage().getName());
	private Random rnd;
	private FilterProcessor filterProcessor;
	private List<? super NImage> images;
	private GeneratorProviderInfo gpInfo;
	private Iterator<? super NImage> it;
	private Counter1d counter;

	public GeneratorImageProvider(GeneratorProviderInfo info) {
		init(info);
	}

	private void init(GeneratorProviderInfo info) {
		this.rnd = new Random(info.seed);
		this.counter = new DefaultCounter1d();
		this.gpInfo = info;
		images = generateImages();
		it = images.iterator();
	}

	private List<? super NImage> generateImages() {
		synchronized(this)
		{
		images = new ArrayList<NImage>();
		for (int i = 0; i < gpInfo.numPictures; i++) {
			images.add(generateImage());
			counter.inc();
		}
		return images;
		}
	}

	private NImage generateImage() {
		PGraphics buffer = PAppletShim.getApplet().createGraphics(gpInfo.width,
				gpInfo.height);
		buffer.beginDraw();

		ImageGeneratorState state = new ImageGeneratorState();
		state.counter = counter;
		state.rnd = rnd;
		state.gpInfo = gpInfo;

		gpInfo.imageGenerator.Draw(buffer, state);

		buffer.endDraw();
		return new NImage(buffer);
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
		synchronized(this)
		{	
		buffer.image(((NImage) it.next()).getImage(), 0, 0);
		}
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

	@Override
	public void reInit() {
		synchronized(this)
		{
		init(gpInfo);
		}

	}

}
