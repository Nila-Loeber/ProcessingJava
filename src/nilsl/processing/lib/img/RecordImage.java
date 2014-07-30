package nilsl.processing.lib.img;

import java.io.Serializable;

public class RecordImage extends NImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordImage(String filename) {
		super(filename);

		
	}
	
	public boolean isLabel;
	
	
}
