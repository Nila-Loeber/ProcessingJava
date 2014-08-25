package nilsl.processing.lib.img;

import java.io.Serializable;

import nilsl.processing.lib.cv.CompostImageProcessing;
import nilsl.processing.lib.img.filters.LabelFilter;

public class CompostImage extends NImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompostImage(String filename) {
		super(filename);		
		fillHeight = CompostImageProcessing.calcFillHeight(this.image,this.image.height/4,this.image.height-this.image.height/800,0.1f);
	}
	
	public int fillHeight;
	
}
