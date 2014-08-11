package nilsl.processing.lib.twodim.drawers.mosaic;

import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import processing.core.PGraphics;

public class CutupDrawer extends DefaultMosaicDrawer {

	public CutupDrawer(MosaicInfo mosInfo) {
		super(mosInfo);
	}

	@Override
	public void draw() {
		ResetCounter();
		do {
			PGraphics pg = parentApplet.createGraphics(imgXSize, imgYSize*maxY);
			imageProvider.getNextImage(pg);
			if (imageEnhancers.size() > 0) {
				for (ImageEnhancer enhancer : imageEnhancers) {
					enhancer.Enhance(pg);
				}
			}
			canvas.image(pg, counter.getCurX() * imgXSize,
					counter.getCurY() * imgYSize);
			counter.inc();
		} while (!counter.eof() && imageProvider.hasNext());
	}

}
