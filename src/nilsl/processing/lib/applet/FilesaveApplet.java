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
	private String baseDir = "C:\\data\\compositions\\";
	private String baseFilename;
	
	public FilesaveApplet()
	{
		super();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
		Date date = new Date();
		baseFilename=dateFormat.format(date);
	}
	
	protected void HandleSave()
	{
		save(baseDir+ baseFilename + "_" + filenameCounter + ".png");
		filenameCounter++;
	}
	
	public void keyPressed() {
		if (key == 's') {
			HandleSave();
		}
	}

}
