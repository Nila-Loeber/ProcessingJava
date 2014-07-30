package nilsl.processing.lib.img;

import processing.core.PGraphics;
import processing.core.PImage;

public class ImageOverlayEnhancer implements ImageEnhancer {

	private PImage overlayImage;

	public ImageOverlayEnhancer(PImage overlayImage) {
		this.overlayImage = overlayImage;
	}

	@Override
	public void Enhance(PGraphics pgraphic) {
		overlayImage.resize(pgraphic.width, pgraphic.height);
		pgraphic.beginDraw();
		pgraphic.image(overlayImage, 0, 0);
		pgraphic.endDraw();
	}

}
