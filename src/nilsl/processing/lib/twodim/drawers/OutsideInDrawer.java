package nilsl.processing.lib.twodim.drawers;

import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.img.enhancers.ImageEnhancer;
import nilsl.processing.lib.onedim.counters.Counter1d;
import nilsl.processing.lib.onedim.counters.DefaultBoundedCounter1d;
import nilsl.processing.lib.onedim.counters.DefaultCounter1d;
import nilsl.processing.lib.twodim.imageproviders.Resetable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import processing.core.PGraphics;

public class OutsideInDrawer extends Drawer {

	static final Logger logger = LogManager.getLogger(OutsideInDrawer.class
			.getPackage().getName());
	Counter1d counter;
	private OutsideInDrawerInfo drawerInfo;
	public final List<ImageEnhancer> imageEnhancers = new ArrayList<ImageEnhancer>();

	public OutsideInDrawer(OutsideInDrawerInfo info) {
		this.drawerInfo = info;
		counter = new DefaultCounter1d();
	}

	@Override
	public void draw() {
		logger.info("Draw called.");
		counter.reset();
		int increment = (Math.min(drawerInfo.imgSizeX, drawerInfo.imgSizeY) - Math
				.min(drawerInfo.imgSizeXdest, drawerInfo.imgSizeYdest))
				/ drawerInfo.numPics;
		do {

			int drawXPos = counter.getCurPos() * (increment / 2);
			int drawYPos = counter.getCurPos() * (increment / 2);
			int width = drawerInfo.imgSizeX - counter.getCurPos() * (increment);
			int height = drawerInfo.imgSizeY - counter.getCurPos()
					* (increment);
			PGraphics pg = parentApplet.createGraphics(drawerInfo.imgSizeX,
					drawerInfo.imgSizeY);
			imageProvider.getNextImage(pg);
			if (imageEnhancers.size() > 0) {
				for (ImageEnhancer enhancer : imageEnhancers) {
					enhancer.Enhance(pg);
				}
			}

			canvas.image(pg, drawXPos, drawYPos, width, height);
			logger.trace(String
					.format("Drawing image to %d:%d. Width, height: %d, %d. Increment: %d. Enhancers found: ",
							drawXPos, drawYPos, width, height, increment,
							imageEnhancers.size()));
			counter.inc();
		} while (counter.getCurPos() <= drawerInfo.numPics);
	}

}
