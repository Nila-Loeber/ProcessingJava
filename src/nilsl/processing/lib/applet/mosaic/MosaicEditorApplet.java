package nilsl.processing.lib.applet.mosaic;

import nilsl.processing.lib.applet.FilesaveApplet;
import nilsl.processing.lib.img.filters.CopyFilter;
import nilsl.processing.lib.img.filters.NewImageFilter;
import nilsl.processing.lib.img.filters.RandomizeFilter;
import nilsl.processing.lib.img.filters.RemoveFilter;
import nilsl.processing.lib.img.filters.SwapFilter;
import nilsl.processing.lib.twodim.imageproviders.Filterable;
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

	private float zoomFactor = 1;

	public void draw() {
		mosDrawer.draw();
		super.draw();
	}

	protected Filterable imageProvider;
	protected MosaicInfo mosInfo = new MosaicInfo();
	protected EnhanceableMosaicDrawer2d mosDrawer;
	private boolean copyMode = false;

	public void setup() {
		super.setup(mosInfo.xdim * mosInfo.imgSizeX, mosInfo.ydim * mosInfo.imgSizeY);
	}

	public void handleSwap() {
		if (oldPos == null) {
			oldPos = getAbsImagePos(mouseX, mouseY);
		} else {
			newPos = getAbsImagePos(mouseX, mouseY);
			imageProvider.getFilterProcessor().filters.add(new SwapFilter(
					oldPos, newPos));
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
			imageProvider.getFilterProcessor().filters.add(new CopyFilter(
					oldPos, newPos));
			triggerRedraw();
			newPos = null;
		}
	}

	private void triggerRedraw() {
		imageProvider.ApplyFilters();
		mosDrawer.draw();
		super.draw();
	}

	int calcCurrentPos(int x, int y) {
		return y * mosInfo.xdim + x;
	}

	int getAbsImagePos(int x, int y) {
		int pickupImgXPos = (int) (x / (mosInfo.imgSizeX * zoomFactor));
		int pickupImgYPos = (int) (y / (mosInfo.imgSizeY * zoomFactor));
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
			zoomFactor *= 2;
			background(0);
			((Zoomable) mosDrawer).zoom();
			triggerRedraw();
		}
		if (key == '-') {
			zoomFactor /= 2;
			background(0);
			((Zoomable) mosDrawer).unzoom();
			triggerRedraw();
		}
		if (key == 'r') {
			handleRandomize();
		}
		if (key == 'm') {
			for (int i = 0; i < 10; i++) {
				handleRandomize();
				super.HandleSave();
			}
		}
		if (key == 'c') {
			this.copyMode = this.copyMode ^ true;
			oldPos = null;
		}

		super.keyPressed();

	}

	private void handleDelete() {
		imageProvider.getFilterProcessor().filters.add(new RemoveFilter(
				getAbsImagePos(mouseX, mouseY)));
		triggerRedraw();
	}

	protected void handleRandomize() {
		imageProvider.getFilterProcessor().filters
				.add(new RandomizeFilter(true));
		triggerRedraw();
	}

}
