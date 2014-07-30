package nilsl.processing.lib.img.filters;

import java.util.List;

import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.img.comparators.BriComparator;
import nilsl.processing.lib.img.comparators.HueComparator;

public class OrderByBriFilter implements FilterCommand {

	@Override
	public void apply(List<NImage> images) {
	images.sort(new BriComparator());
		
	}

	@Override
	public boolean removeAfterApply() {
		return false;
	}

}
