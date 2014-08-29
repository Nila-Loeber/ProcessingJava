package nilsl.processing.lib.twodim.imageproviders;

import java.util.Iterator;
import java.util.List;

import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.img.filters.FilterCommand;

public class FilterProcessor {
	public final List<FilterCommand> filters;

	public FilterProcessor(List<FilterCommand> filters) {
		this.filters = filters;
	}
	
	public void applyFilters(List<? super NImage> images) {
		for(Iterator<FilterCommand> i = filters.iterator(); i.hasNext();) {
			FilterCommand filter = i.next();
			filter.apply(images);
		    if (filter.removeAfterApply()) i.remove();
		 }
	}
}
