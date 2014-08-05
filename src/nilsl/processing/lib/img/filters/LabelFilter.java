package nilsl.processing.lib.img.filters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.moment.Mean;

import processing.core.PImage;
import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.img.RecordImage;

/**
 * @author Nils
 *
 */
/**
 * @author Nils
 *
 */
/**
 * @author Nils
 *
 */
public class LabelFilter implements FilterCommand {

	

	private int takeRectangularSample(PImage img) {
		img.loadPixels();
		int[] pixels = img.pixels;
		double[] pixelsDouble = new double[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			pixelsDouble[i] = pixels[i];
		}

		Mean mean = new Mean();

		mean.setData(pixelsDouble);
		return (int) mean.evaluate() / 100000;
	}


	
	
	/**
	 * The general idea here is to take one sample each from the NW, NE, SE and SW corners.
	 * Those are supposed to have the same value. Then we take one sample from the E side.
	 * This one should be different than the corners. This eliminates a couple more false
	 * positives.
	 * 
	 * @param nimage
	 * @return
	 */
	public boolean isLabel(NImage nimage) {  // HACK: Should refactor to its own class in .lib.cv
		
		if (nimage instanceof RecordImage)	// isLabel is precomputed in RecordImage
		{
			return ((RecordImage)nimage).isLabel;
		}
		
		// isLabel not precomputed; compute now		
		boolean isLabel = true;

		final int samplingDistance = nimage.width/12; // Based on experiments with w: 600. 50 is a good val here.   
		final int samplingRectSize = samplingDistance/2;
		
		List<Integer> samples = new ArrayList<Integer>();
		PImage pimg = nimage.GetImage();
		int imgWidth = pimg.width;
		int imgHeight = pimg.height;
		samples.add(takeRectangularSample(pimg.get(samplingDistance,
				samplingDistance, samplingRectSize, samplingRectSize)));

		samples.add(takeRectangularSample(pimg.get(imgWidth - samplingDistance
				- samplingRectSize, samplingDistance, samplingRectSize,
				samplingRectSize)));

		samples.add(takeRectangularSample(pimg.get(samplingDistance, imgHeight-samplingDistance-samplingRectSize, samplingRectSize,
				samplingRectSize)));

		samples.add(takeRectangularSample(pimg.get(imgWidth - samplingDistance
				- samplingRectSize, imgHeight-samplingDistance-samplingRectSize, samplingRectSize,
				samplingRectSize)));
		

		int refValue = samples.get(0);
		System.out.println("Processing " + nimage.imageFilename
				+ ". Reference Value: " + refValue);
		for (int val : samples) {
			if (val != refValue)
				isLabel = false;
			System.out.println(val);
		}
		
		samples.add(takeRectangularSample(pimg.get(imgWidth - samplingDistance
				- samplingRectSize, imgHeight/2, samplingRectSize,
				samplingRectSize)));
		
		if (samples.get(4)==refValue) isLabel=false;
		
		System.out.println("IsLabel: " + isLabel);
		return isLabel;
	}

	@Override
	public void apply(List<? super NImage> images) {
		for (Iterator<NImage> i = (Iterator<NImage>) images.iterator(); i.hasNext();) {
			NImage image = i.next();
			if (!isLabel(image))
				i.remove();
		}
	}

	@Override
	public boolean removeAfterApply() {
		// TODO Auto-generated method stub
		return false;
	}

}
