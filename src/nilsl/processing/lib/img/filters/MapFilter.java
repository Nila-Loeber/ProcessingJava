package nilsl.processing.lib.img.filters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nilsl.processing.lib.img.NImage;

// Picks a proportional sample from the image list.
public class MapFilter implements FilterCommand {

	private int numItems;

	static final Logger logger = LogManager.getLogger(MapFilter.class
			.getPackage().getName());

	public MapFilter(int numItems) {
		this.numItems = numItems;

	}

	@Override
	public void apply(List<? super NImage> images) {
		int arraySize = images.size();
		int period = (int) (1 / ((float) numItems / arraySize));

		logger.info(String.format(
				"Applying filter. numItems: %d. images.size: %d. Period: %d",
				numItems, arraySize, period));

		Iterator<? super NImage> it = images.iterator();
		int counter = 0;

		while (it.hasNext()) {
			it.next();
			if (counter % period != 0) {
				it.remove();
				logger.trace("Removing image at " + counter);
			}
			counter++;
		}

		// for (int counter = 0; counter <= arraySize; counter++) {
		// logger.trace("Processing item " + counter);
		// it.next();
		// if (counter % period != 0) {
		// it.remove();
		// logger.trace("Removing image at " + counter);
		// }
		// }
	}

	@Override
	public boolean removeAfterApply() {
		return true;
	}

}
