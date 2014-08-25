package nilsl.processing.lib.cv;

import nilsl.processing.lib.processingshims.PAppletShim;
import processing.core.PGraphics;
import processing.core.PImage;

public class CompostImageProcessing {
	public static int calcFillHeight(PImage pimage, int top, int bottom,
			float thresholdProportionOfFilledPixels) {
		
		PGraphics image = PAppletShim.getApplet().createGraphics(pimage.width, pimage.height);
		
		prepareImage(image,pimage);
		
		int counter = top;
		while (counter != bottom) {
			int blackPixels = calcBlackPixels(image, counter);
			float proportionOfFilledPixels = calcProportionOfFilledPixels(
					image.width, blackPixels);

			if (proportionOfFilledPixels >= thresholdProportionOfFilledPixels)
				return counter;
			counter++;
		}
		return bottom;
	}

	static private void prepareImage(PGraphics graphics, PImage pimage) {
		graphics.beginDraw();
		  graphics.background(0);
		  graphics.image(pimage,0,0);
		  graphics.filter(PAppletShim.getApplet().THRESHOLD, 0.5f);		 
		  graphics.endDraw();
		  graphics.loadPixels();
	}

	static int calcBlackPixels(PImage image, int y) {
		int numBlackPixels = 0;

		for (int x = 0; x < image.width; x++) {
			int curPixel = image.pixels[y * image.width + x];
			if (curPixel != PAppletShim.getApplet().color(255))
				numBlackPixels++;
		}

		return numBlackPixels;
	}

	static float calcProportionOfFilledPixels(int width, int blackPixels) {
		return (float) blackPixels / width;
	}
}
