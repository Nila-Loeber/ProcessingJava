package nilsl.processing.lib.twodim.imageproviders.generators;

import processing.core.PGraphics;

public abstract class ImageGenerator {
	public abstract void Draw(PGraphics buffer, ImageGeneratorState state);
}
