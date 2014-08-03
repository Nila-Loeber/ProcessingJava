package nilsl.processing.lib.processingshims;

import processing.core.PApplet;

public class PAppletShim {
	static NApplet appl;
	static
	{
		appl=new NApplet();
		appl.init();
	}
	public static PApplet getApplet()
	{
		return appl;
	}
}
