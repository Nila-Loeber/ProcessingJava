package nilsl.processing.lib.twodim.imageproviders;

import java.util.Iterator;
import java.util.List;

import nilsl.processing.lib.img.filters.FilterCommand;

public class FilterableMultiImageProvider extends MultiImageProvider {

	public final List<FilterCommand> filters;

	public FilterableMultiImageProvider(List<String> filenames,
			List<FilterCommand> filters) {
		super(filenames);
		this.filters = filters;
	}

	public void ApplyFilters() {
		for(Iterator<FilterCommand> i = filters.iterator(); i.hasNext();) {
			FilterCommand filter = i.next();
			filter.apply(images);
		    if (filter.removeAfterApply()) i.remove();
		 }
		reset();
	}
}
