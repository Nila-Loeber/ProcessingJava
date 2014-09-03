package nilsl.processing.lib.img.enhancers;

import nilsl.processing.lib.onedim.counters.Counter1d;
import nilsl.processing.lib.onedim.counters.ModularCounter1d;
import processing.core.PGraphics;
import processing.core.PImage;

public class CutupEnhancer implements ImageEnhancer {

	private Counter1d modularCounter;
	private int numSegments;
	private int segmentToUse = -1;

	public CutupEnhancer(int numSegments) {
		this.numSegments = numSegments;
		modularCounter = new ModularCounter1d(1, numSegments);
	}

	public CutupEnhancer(int numSegments, int segmentToUse) {
		this.numSegments = numSegments;
		this.segmentToUse = segmentToUse;
		modularCounter = new ModularCounter1d(1, numSegments);
	}

	@Override
	public void Enhance(PGraphics pgraphic) {
		int height = (int) pgraphic.height / numSegments;

		int curPos;

		if (segmentToUse != -1)
			curPos = segmentToUse * height;
		else
			curPos = modularCounter.getCurPos() * height;

		PImage imagePart = pgraphic.get(0, curPos, pgraphic.width, height);
		pgraphic.setSize(pgraphic.width, height);
		pgraphic.beginDraw();
		pgraphic.image(imagePart, 0, 0, pgraphic.width, pgraphic.height);
		pgraphic.endDraw();

		modularCounter.inc();
	}

}
