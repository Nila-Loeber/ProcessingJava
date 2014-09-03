package nilsl.processing.lib.img.filters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import processing.core.PImage;
import nilsl.processing.lib.cv.RecordImageProcessing;
import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.img.RecordImage;
import nilsl.processing.lib.twodim.imageproviders.MultiImageFileProvider;

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

	static final Logger logger = LogManager.getLogger(LabelFilter.class
			.getPackage().getName());

	@Override
	public void apply(List<? super NImage> images) {
		logger.info("Applying filter.");
		for (Iterator<NImage> i = (Iterator<NImage>) images.iterator(); i
				.hasNext();) {
			NImage image = i.next();
			if (!RecordImageProcessing.isLabel(image))
				i.remove();
		}
	}

	@Override
	public boolean removeAfterApply() {
		// TODO Auto-generated method stub
		return false;
	}

}
