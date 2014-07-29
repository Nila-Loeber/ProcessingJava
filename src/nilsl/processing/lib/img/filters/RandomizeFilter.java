package nilsl.processing.lib.img.filters;

import java.util.Collections;
import java.util.List;

import nilsl.processing.lib.img.NImage;

public class RandomizeFilter implements FilterCommand {

	boolean removeAfterApply;
	
	public RandomizeFilter(boolean removeAfterApply)
	{
		this.removeAfterApply=removeAfterApply;
	}
	
	@Override
	public void apply(List<NImage> images) {
		Collections.shuffle(images);
	}

	@Override
	public boolean removeAfterApply() {
		return removeAfterApply;
	}

}
