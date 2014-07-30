package nilsl.processing.lib.img.filters;

import java.util.List;

import nilsl.processing.lib.img.HueComparator;
import nilsl.processing.lib.img.NImage;

public class OrderByHueFilter implements FilterCommand {

	@Override
	public void apply(List<NImage> images) {
	images.sort(new HueComparator());
		
	}

	@Override
	public boolean removeAfterApply() {
		return false;
	}

}
