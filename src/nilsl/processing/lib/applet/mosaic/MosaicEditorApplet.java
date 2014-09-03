package nilsl.processing.lib.applet.mosaic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nilsl.processing.lib.applet.ViewerApplet;
import nilsl.processing.lib.applet.NApplet;
import nilsl.processing.lib.img.filters.CopyFilter;
import nilsl.processing.lib.img.filters.NewImageFilter;
import nilsl.processing.lib.img.filters.RandomizeFilter;
import nilsl.processing.lib.img.filters.RemoveFilter;
import nilsl.processing.lib.img.filters.SwapFilter;
import nilsl.processing.lib.twodim.drawers.mosaic.EnhanceableMosaicDrawer2d;
import nilsl.processing.lib.twodim.drawers.mosaic.MosaicInfo;
import nilsl.processing.lib.twodim.imageproviders.Filterable;
import nilsl.processing.lib.twodim.imageproviders.ReInitable;

public abstract class MosaicEditorApplet extends ViewerApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer oldPos = null;
	protected Integer newPos = null;

	static final Logger logger = LogManager.getLogger(MosaicEditorApplet.class
			.getPackage().getName());

	public void draw() {
		mosDrawer.draw();
		super.draw();
	}

	protected Filterable imageProvider;
	private MosaicInfo mosInfo;
	protected EnhanceableMosaicDrawer2d mosDrawer;
	private boolean copyMode = false;

	public void setup(MosaicEditorAppletSettings settings) {
		mosInfo = settings.mosInfo;
		super.setup(settings);
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
		imageProvider.applyFilters();
		super.draw();
	}

	int calcCurrentPos(int x, int y) {
		return y * mosInfo.xdim + x;
	}

	int getAbsImagePos(int x, int y) {
		int pickupImgXPos = (int) (x / (mosInfo.imgSizeX * zoomFactor));
		int pickupImgYPos = (int) (y / (mosInfo.imgSizeY * zoomFactor));
		int pickupListPos = calcCurrentPos(pickupImgXPos, pickupImgYPos);
		logger.trace(String.format("getAbsImagePos x: %d y: %d result: %d",
				pickupImgXPos, pickupImgYPos, pickupListPos));
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
		switch (key) {
		case 'r':
			handleRandomize();
			break;
		case 'R':
			handleReInit();
			break;
		case 'm':
			for (int i = 0; i < 10; i++) {
				handleRandomize();
				super.handleSave();
			}
			break;
		case 'c':
			this.copyMode = this.copyMode ^ true;
			oldPos = null;
			break;
		default:
			super.keyPressed();
			break;
		}

	}

	protected void handleReInit() {
		if (imageProvider instanceof ReInitable)
			((ReInitable) imageProvider).reInit();

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
