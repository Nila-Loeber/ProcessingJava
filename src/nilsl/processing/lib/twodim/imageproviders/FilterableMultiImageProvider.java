package nilsl.processing.lib.twodim.imageproviders;

import java.io.IOException;
import java.util.List;

import nilsl.processing.lib.applet.ViewerApplet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		this.processor=processor;
	}

	@Override
	public FilterProcessor getFilterProcessor() {
		return processor;
	}

	

	
	
	
}
