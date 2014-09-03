package nilsl.processing.lib.img.filters;

import java.util.Collections;
import java.util.List;

import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.img.comparators.BriComparator;

public class OrderByBriFilter implements FilterCommand {

	private boolean desc;

	public OrderByBriFilter(boolean desc) {
		this.desc = desc;
	}

	@Override
	public void apply(List<? super NImage> images) {
		((List<NImage>) images).sort(new BriComparator());
		if (desc)
			Collections.reverse(images);

	}

	@Override
	public boolean removeAfterApply() {
		return true;
	}

}
