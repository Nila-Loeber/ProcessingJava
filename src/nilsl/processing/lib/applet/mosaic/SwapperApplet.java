package nilsl.processing.lib.applet.mosaic;

import nilsl.processing.lib.applet.FilesaveApplet;
import nilsl.processing.lib.img.filters.SwapFilter;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicDrawer2d;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicInfo;
import nilsl.processing.lib.twodim.mosaicdrawers.Zoomable;

public abstract class SwapperApplet extends FilesaveApplet{
	
	protected Integer oldPos=null;
	protected Integer newPos=null;
	
	protected abstract FilterableMultiImageProvider getimageProvider();
	protected abstract MosaicInfo getMosInfo();
	protected abstract MosaicDrawer2d getMosDrawer();
	
	public void handleSwap()
	{
		if (oldPos==null)
		{
			oldPos=PickUpSwapImage(mouseX,mouseY);
		}
		else
		{
			newPos=PickUpSwapImage(mouseX,mouseY);
			getimageProvider().filters.add(new SwapFilter(oldPos,newPos));
			getimageProvider().ApplyFilters();
			newPos=null;
			oldPos=null;
		}
	}
	
	
	
	int getCurrentPos(int x, int y)
	{
	  return y*getMosInfo().xdim + x;
	}
	
	int PickUpSwapImage(int x, int y)
	{
	  int pickupImgXPos = x/getMosInfo().imgSizeX;
	  int pickupImgYPos = y/getMosInfo().imgSizeY;
	  int pickupListPos = getCurrentPos(pickupImgXPos, pickupImgYPos);
	  return pickupListPos;
	}
	
	
	public void mouseClicked()
	{
		handleSwap();
	}
	
	@Override
	public void keyPressed() {
		if (key == '+') {
			background(0);
			((Zoomable) getMosDrawer()).zoom();
		}
		if (key == '-') {
			background(0);
			((Zoomable) getMosDrawer()).unzoom();
		}
		super.keyPressed();
	}
}
