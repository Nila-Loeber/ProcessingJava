package nilsl.processing.lib.img;

import java.io.Serializable;

import nilsl.processing.lib.processingshims.PAppletShim;
import processing.core.*;

public class NImage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	protected transient PImage image;
	public String imageFilename;
	int[] histogramHue = new int[256];
	int[] histogramSat = new int[256];
	int[] histogramBri = new int[256];
	public int dominantHue;
	public int dominantSat;
	public int dominantBri;

	public int width;
	public int height;

	public NImage(PImage image) {
		this.image = image;
		init();
	}

	public PImage getImage() {
		if (image == null) {
			PImage img = PAppletShim.getApplet().loadImage(this.imageFilename);
			image = img;
		}
		return image;
	}

	// Releases the memory held by the Image data.
	public void disposeImage() {
		this.image = null;
	}

	public NImage(String filename) {
		imageFilename = filename;
		init();
	}

	private void init() {
		buildHistograms();
		buildDominantValues();
		width = image.width;
		height = image.height;
	}

	private void buildDominantValues() {
		dominantHue = calcDominantValue(histogramHue);
		dominantSat = calcDominantValue(histogramSat);
		dominantBri = calcDominantValue(histogramBri);
	}

	void buildHistograms() {
		getImage().loadPixels();
		for (int i = 0; i < getImage().pixels.length; i++) {
			int pixel = getImage().pixels[i];
			int hue = (int) (PAppletShim.getApplet().hue(pixel));
			histogramHue[hue]++;
			int sat = (int) (PAppletShim.getApplet().saturation(pixel));
			histogramSat[sat]++;
			int bri = (int) (PAppletShim.getApplet().brightness(pixel));
			histogramBri[bri]++;
		}
	}

	private int calcDominantValue(int[] histogram) {
		int maxCount = PApplet.max(histogram);
		int dominantVal = 0;
		for (int i = 0; i < histogram.length; i++) {
			if (histogram[i] == maxCount)
				dominantVal = i;
		}
		return dominantVal;
	}

}
