package nilsl.processing.lib.applet.mosaic;

import nilsl.processing.lib.applet.FilesaveApplet;
import nilsl.processing.lib.img.filters.CopyFilter;
import nilsl.processing.lib.img.filters.NewImageFilter;
import nilsl.processing.lib.img.filters.RandomizeFilter;
import nilsl.processing.lib.img.filters.RemoveFilter;
import nilsl.processing.lib.img.filters.SwapFilter;
import nilsl.processing.lib.twodim.imageproviders.Filterable;
import nilsl.processing.lib.twodim.imageproviders.FilterableMultiImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.EnhanceableMosaicDrawer2d;
import nilsl.processing.lib.twodim.mosaicdrawers.MosaicInfo;
import nilsl.processing.lib.twodim.mosaicdrawers.Zoomable;

public abstract class MosaicEditorApplet extends FilesaveApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer oldPos = null;
	protected Integer newPos = null;

	public void draw() {

		mosDrawer.draw();
	}

	protected Filterable imageProvider;
	protected MosaicInfo mosInfo = new MosaicInfo();
	protected EnhanceableMosaicDrawer2d mosDrawer;
	private boolean copyMode = false;

	public void setup() {
		size(mosInfo.xdim * mosInfo.imgSizeX, mosInfo.ydim * mosInfo.imgSizeY);
	}

	public void handleSwap() {
		if (oldPos == null) {
			oldPos = getAbsImagePos(mouseX, mouseY);
		} else {
			newPos = getAbsImagePos(mouseX, mouseY);
			imageProvider.getFilterProcessor().filters.add(new SwapFilter(oldPos, newPos));
			triggerRedraw();
			newPos = null;
			oldPos = null;
		}
	}

	public void handleCopy() {
		if (oldPos == null) {
			oldPos = getAbsImagePos(mouseX, mouseY);
		} else {
			newPos = getAbsImagePos(mouseX, mouseY);
			imageProvider.getFilterProcessor().filters.add(new CopyFilter(oldPos, newPos));
			triggerRedraw();
			newPos = null;
		}
	}
	
	private void triggerRedraw() {
		imageProvider.ApplyFilters();
		mosDrawer.draw();
	}

	int calcCurrentPos(int x, int y) {
		return y * mosInfo.xdim + x;
	}

	int getAbsImagePos(int x, int y) {
		int pickupImgXPos = x / mosInfo.imgSizeX;
		int pickupImgYPos = y / mosInfo.imgSizeY;
		int pickupListPos = calcCurrentPos(pickupImgXPos, pickupImgYPos);
		return pickupListPos;
	}

	public void mouseClicked() {
		if (mouseButton == LEFT)
			if (copyMode) {
				handleCopy();
			} else {
				handleSwap();
			}
		if (mouseButton == RIGHT)
			handleDelete();

		if (mouseButton == CENTER)
			handleNewImage();
		
	}

	

	private void handleNewImage() {
		int pos = getAbsImagePos(mouseX, mouseY);
		imageProvider.getFilterProcessor().filters.add(new NewImageFilter(pos));
		triggerRedraw();
		
	}

	@Override
	public void keyPressed() {
		if (key == '+') {
			background(0);
			((Zoomable) mosDrawer).zoom();
		}
		if (key == '-') {
			background(0);
			((Zoomable) mosDrawer).unzoom();
		}
		if (key == 'r') {
			handleRandomize();
		}
		if (key == 'c') {
			this.copyMode = this.copyMode ^ true;
			oldPos=null;
		}
	
		super.keyPressed();

	}

	private void handleDelete() {
		imageProvider.getFilterProcessor().filters.add(new RemoveFilter(getAbsImagePos(mouseX,
				mouseY)));
		triggerRedraw();
	}

	private void handleRandomize() {
		imageProvider.getFilterProcessor().filters.add(new RandomizeFilter(true));
		triggerRedraw();
	}

}
