package nilsl.processing.lib.img.filters;

import java.util.List;

import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.img.comparators.HueComparator;

public class OrderByHueFilter implements FilterCommand {

	@Override
	public void apply(List<? super NImage> images) {
		((List<NImage>)images).sort(new HueComparator());
		
	}

	@Override
	public boolean removeAfterApply() {
		return false;
	}

}
