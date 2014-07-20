package nilsl.processing.lib.applet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import processing.core.PApplet;

public class FilesaveApplet extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int filenameCounter = 0;
	private String baseFilename;
	
	public FilesaveApplet()
	{
		super();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
		Date date = new Date();
		baseFilename=dateFormat.format(date);
	}
	
	public void keyPressed() {
		if (key == 's') {

			save(baseFilename + "_" + filenameCounter + ".png");
			filenameCounter++;

		}
	}

}
