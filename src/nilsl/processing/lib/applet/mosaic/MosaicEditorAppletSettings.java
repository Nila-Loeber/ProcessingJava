package nilsl.processing.lib.applet.mosaic;

import nilsl.processing.lib.applet.ViewerAppletSettings;
import nilsl.processing.lib.twodim.drawers.Drawer;
import nilsl.processing.lib.twodim.drawers.mosaic.MosaicInfo;

public class MosaicEditorAppletSettings extends ViewerAppletSettings {
	public MosaicInfo mosInfo;

	public MosaicEditorAppletSettings(MosaicInfo mosInfo, String filePath,
			Drawer drawer) {
		super(filePath, drawer);
		this.mosInfo = mosInfo;
		width = mosInfo.xdim * mosInfo.imgSizeX;
		height = mosInfo.ydim * mosInfo.imgSizeY;
		this.mosInfo = mosInfo;
	}
}
