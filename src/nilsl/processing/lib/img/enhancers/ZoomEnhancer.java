package nilsl.processing.lib.img.enhancers;

import processing.core.PGraphics;
import processing.core.PImage;

public class ZoomEnhancer implements ImageEnhancer {

	int x, y, width;

	public ZoomEnhancer(int x, int y, int width) {
		this.x = x;
		this.y = y;
		this.width = width;
	}

	@Override
	public void Enhance(PGraphics pgraphic) {
		int height = (int) (width * pgraphic.height) / pgraphic.width;
		if (x + width < pgraphic.width && y + height < pgraphic.height) {
			PImage zoomedImage = pgraphic.get(x, y, width, height);
			pgraphic.beginDraw();
			pgraphic.image(zoomedImage, 0, 0, pgraphic.width, pgraphic.height);

			pgraphic.endDraw();
		}
	}

}
