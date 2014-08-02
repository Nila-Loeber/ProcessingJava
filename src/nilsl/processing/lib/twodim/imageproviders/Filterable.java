package nilsl.processing.lib.twodim.imageproviders;

public interface Filterable {
	void ApplyFilters();
	void setFilterProcessor(FilterProcessor processor);
	FilterProcessor getFilterProcessor();
}
