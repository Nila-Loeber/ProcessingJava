package nilsl.processing.lib.applet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import processing.core.PApplet;
import processing.core.PGraphics;

public class ViewerApplet extends NApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int filenameCounter = 0;
	private String baseDir = "C:\\data\\compositions\\";
	private String baseFilename;
	protected PGraphics canvas;
	protected float zoomFactor=1;

	static final Logger logger = LogManager.getLogger(ViewerApplet.class
			.getPackage().getName());

	public ViewerApplet() {
		super();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
		Date date = new Date();
		baseFilename = dateFormat.format(date);
	}

	protected void HandleSave() {
		canvas.save(baseDir + baseFilename + "_" + filenameCounter + ".png");
		filenameCounter++;
	}

	public void keyPressed() {
		if (key == 's') {
			HandleSave();
		}
		if (key == '+') {
			zoomFactor *= 2;
			background(0);
			draw();
		}
		if (key == '-') {
			zoomFactor /= 2;
			background(0);
			draw();
		}
		if (key == 'l') {
			if (this.looping) noLoop(); else loop();
		}
		
	}

	public void setup(ViewerAppletSettings settings) {
		super.setup(settings);
		canvas = createGraphics(settings.width, settings.height);
		baseDir = settings.filePath;
	}

	public void draw() {
		scale(zoomFactor);
		logger.trace("Draw() called");
		canvas.loadPixels();
		canvas.updatePixels();
		image(canvas, 0, 0, canvas.width, canvas.height);
		scale(1/zoomFactor);
	}

}
