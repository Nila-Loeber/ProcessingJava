package nilsl.processing.sketches.mosaics;

import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.applet.FilesaveApplet;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.img.filters.LabelFilter;
import nilsl.processing.lib.img.filters.SwapFilter;
import nilsl.processing.lib.io.FileRepo;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicDrawer2d;
import nilsl.processing.lib.twodim.mosaicdrawers.Zoomable;

public class MultiImage extends FilesaveApplet {

	private static final long serialVersionUID = 1L;
	private static final int imgSize = 200;
	private static final int xdim = 5;
	private static final int ydim = 5;

	private FilterableMultiImageProvider imgProvider;
	Integer oldPos=null;
	Integer newPos=null;
	
	public void setup() {
		size(xdim * imgSize, ydim * imgSize);

		md = new DefaultMosaicDrawer(xdim, ydim, imgSize, imgSize);

		List<String> files = FileRepo.listFiles("c:\\data\\unsorted");

		System.out.println("Files found: " + files.size());

		FilterCommand labelFilter = new LabelFilter();
		List<FilterCommand> filters = new ArrayList<FilterCommand>();
		filters.add(labelFilter);
		imgProvider = new FilterableMultiImageProvider(files.subList(0,50),filters);
		//imgProvider = new FilterableMultiImageProvider(files,filters);
		imgProvider.ApplyFilters();
		
		md.imageProvider = imgProvider;
		md.parentApplet = this;

	}

	MosaicDrawer2d md;

	public void draw() {

		md.draw();
	}

	
	public void handleSwap()
	{
		if (oldPos==null)
		{
			oldPos=PickUpSwapImage(mouseX,mouseY);
		}
		else
		{
			newPos=PickUpSwapImage(mouseX,mouseY);
			imgProvider.filters.add(new SwapFilter(oldPos,newPos));
			imgProvider.ApplyFilters();
			newPos=null;
			oldPos=null;
		}
	}
	
	
	
	int getCurrentPos(int x, int y)
	{
	  return y*xdim + x;
	}
	
	int PickUpSwapImage(int x, int y)
	{
	  int pickupImgXPos = x/imgSize;
	  int pickupImgYPos = y/imgSize;
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
			((Zoomable) md).zoom();
		}
		if (key == '-') {
			background(0);
			((Zoomable) md).unzoom();
		}
		super.keyPressed();
	}

}
