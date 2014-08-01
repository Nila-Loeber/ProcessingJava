package nilsl.processing.lib.applet.mosaic;

import nilsl.processing.lib.applet.FilesaveApplet;
import nilsl.processing.lib.img.filters.RandomizeFilter;
import nilsl.processing.lib.img.filters.RemoveFilter;
import nilsl.processing.lib.img.filters.SwapFilter;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicDrawer2d;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicInfo;
import nilsl.processing.lib.twodim.mosaicdrawers.Zoomable;

public abstract class MosaicEditorApplet extends FilesaveApplet{
	
	protected Integer oldPos=null;
	protected Integer newPos=null;
	
	protected abstract FilterableMultiImageProvider getImageProvider();
	protected abstract MosaicInfo getMosInfo();
	protected abstract MosaicDrawer2d getMosDrawer();
	
	public void handleSwap()
	{
		if (oldPos==null)
		{
			oldPos=getAbsImagePos(mouseX,mouseY);
		}
		else
		{
			newPos=getAbsImagePos(mouseX,mouseY);
			getImageProvider().filters.add(new SwapFilter(oldPos,newPos));
			newPos=null;
			oldPos=null;
		}
		triggerRedraw();
	}
	
	private void triggerRedraw()
	{
		getImageProvider().ApplyFilters();
		getMosDrawer().draw();
	}

	
	
	int calcCurrentPos(int x, int y)
	{
	  return y*getMosInfo().xdim + x;
	}
	
	int getAbsImagePos(int x, int y)
	{
	  int pickupImgXPos = x/getMosInfo().imgSizeX;
	  int pickupImgYPos = y/getMosInfo().imgSizeY;
	  int pickupListPos = calcCurrentPos(pickupImgXPos, pickupImgYPos);
	  return pickupListPos;
	}
	
	
	public void mouseClicked()
	{if (mouseButton==LEFT)
		handleSwap();
	if (mouseButton==RIGHT)
		handleDelete();
	
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
		if (key == 'r') {
			handleRandomize();
		}
				
		super.keyPressed();
		
	}
	private void handleDelete() {
		getImageProvider().filters.add(new RemoveFilter(getAbsImagePos(mouseX, mouseY)));
		triggerRedraw();
	}
	
	private void handleRandomize()
	{
		getImageProvider().filters.add(new RandomizeFilter(true));
		triggerRedraw();
	}
	
}
