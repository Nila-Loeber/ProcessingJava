package nilsl.processing.lib.img;

import java.io.Serializable;

import nilsl.processing.lib.img.filters.LabelFilter;

public class RecordImage extends NImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordImage(String filename) {
		super(filename);
		LabelFilter labelFilter = new LabelFilter();	// HACK: Not good performance-wise to instantiate
														// a new instance for each RecordImage. Factory? 
														// make static? Inject value from outside?
		isLabel = labelFilter.isLabel((NImage)this);
	}
	
	public Boolean isLabel;
	
	
}
