package nilsl.processing.lib.processingshims;

import processing.core.PApplet;

public class PAppletShim {
	static PApplet appl;
	static
	{
		appl=new PApplet();
		appl.init();
	}
	public static PApplet getApplet()
	{
		return appl;
	}
}
