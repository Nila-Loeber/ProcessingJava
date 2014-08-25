package nilsl.processing.lib.img;

import java.io.Serializable;

import nilsl.processing.lib.cv.RecordImageProcessing;
import nilsl.processing.lib.img.filters.LabelFilter;

public class RecordImage extends NImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordImage(String filename) {
		super(filename);
	
		isLabel = RecordImageProcessing.isLabel((NImage)this);
	}
	
	public Boolean isLabel;
	
	
}
