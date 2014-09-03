package nilsl.processing.lib.twodim.drawers.mosaic;

import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.twodim.imageproviders.Resetable;
import processing.core.PGraphics;
import nilsl.processing.lib.twodim.counters.Counter2d;
import nilsl.processing.lib.twodim.counters.DefaultCounter2d;

public class TintDrawer extends EnhanceableMosaicDrawer2d {

	private Counter2d counter;
	private int imgXSize;
	private int imgYSize;
	private int maxX;
	private int maxY;

	private int numPics;

	private List<TintInfo> tintInfos;
	private float repetitionFactor;

	private void ResetCounter() {
		counter = new DefaultCounter2d(maxX, maxY);
		((Resetable) imageProvider).reset();
	}

	private void init(MosaicInfo mosInfo, List<TintInfo> tintInfos, int numPics) {
		this.tintInfos = tintInfos;
		this.numPics = numPics;
		this.imgXSize = mosInfo.imgSizeX;
		this.imgYSize = mosInfo.imgSizeY;
		this.maxX = mosInfo.xdim;
		this.maxY = mosInfo.ydim;
	}

	public TintDrawer(MosaicInfo mosInfo, List<TintInfo> tintInfos,
			int numPics, float repetitionFactor) {
		this.repetitionFactor = repetitionFactor;
		init(mosInfo, tintInfos, numPics);
	}

	public TintDrawer(MosaicInfo mosInfo, TintInfo tintInfo, int numPics) {
		ArrayList<TintInfo> ti = new ArrayList<>();
		ti.add(tintInfo);
		init(mosInfo, ti, numPics);
	}

	PGraphics pg;

	@Override
	public void draw() {
		ResetCounter();

		for (int i = 0; i < numPics; i++) {
			TintInfo currentTintInfo = tintInfos.get((int) (i
					/ repetitionFactor % tintInfos.size()));

			parentApplet.tint(currentTintInfo.v1, currentTintInfo.v2,
					currentTintInfo.v3, currentTintInfo.opacity);
			if (pg == null)
				pg = parentApplet.createGraphics(imgXSize, imgYSize);
			if (imageProvider.hasNext()) {
				imageProvider.getNextImage(pg);
			}
			if (imageEnhancers.size() > 0) {
				for (ImageEnhancer enhancer : imageEnhancers) {
					enhancer.Enhance(pg);
				}
			}
			parentApplet.image(pg, counter.getCurX() * imgXSize,
					counter.getCurY() * imgYSize);
			counter.inc();

		}
	}
}
