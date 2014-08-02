package nilsl.processing.lib.twodim.imageproviders;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import nilsl.processing.lib.img.filters.FilterCommand;

public class FilterableMultiImageProvider extends MultiImageFileProvider implements Filterable {

	public FilterableMultiImageProvider(List<String> filenames) {
		super(filenames);
	}

	public FilterableMultiImageProvider(String filename) throws ClassNotFoundException, IOException {
		super(filename);
	}
	
	private FilterProcessor processor;

	@Override
	public void ApplyFilters() {
		processor.ApplyFilters(images);
		reset();
	}

	@Override
	public void setFilterProcessor(FilterProcessor processor) {
		// TODO Auto-generated method stub
		this.processor=processor;
	}

	@Override
	public FilterProcessor getFilterProcessor() {
		// TODO Auto-generated method stub
		return processor;
	}

	

	
	
	
}
