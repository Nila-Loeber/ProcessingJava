package nilsl.processing.lib.img.filters;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nilsl.processing.lib.img.NImage;

public class RandomizeFilter implements FilterCommand {

	boolean removeAfterApply;
	
	static final Logger logger = LogManager.getLogger(RandomizeFilter.class
			.getPackage().getName());
	
	public RandomizeFilter(boolean removeAfterApply)
	{
		this.removeAfterApply=removeAfterApply;
	}
	
	@Override
	public void apply(List<? super NImage> images) {
		logger.info("Applying filter.");
		Collections.shuffle(images);
		
	}

	@Override
	public boolean removeAfterApply() {
		return removeAfterApply;
	}

}
