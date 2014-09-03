package nilsl.processing.lib.twodim.imageproviders;

public interface Filterable {
	void applyFilters();

	void setFilterProcessor(FilterProcessor processor);

	FilterProcessor getFilterProcessor();
}
