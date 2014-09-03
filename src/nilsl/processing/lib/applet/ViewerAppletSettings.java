package nilsl.processing.lib.applet;

import nilsl.processing.lib.twodim.drawers.Drawer;

public class ViewerAppletSettings extends NAppletSettings {
	public String filePath;
	Drawer drawer;

	public ViewerAppletSettings(String filePath, Drawer drawer) {
		this.filePath = filePath;
		this.drawer = drawer;

	}

}
