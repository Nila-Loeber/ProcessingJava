package nilsl.processing.lib.twodim.drawers.mosaic;

import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.img.enhancers.ImageEnhancer;

public abstract class EnhanceableMosaicDrawer2d extends MosaicDrawer2d {

	public final List<ImageEnhancer> imageEnhancers = new ArrayList<ImageEnhancer>();
}
