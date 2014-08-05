package nilsl.processing.lib.img.filters;

import java.util.Collections;
import java.util.List;

import nilsl.processing.lib.img.NImage;

public class SwapFilter implements FilterCommand {

	private int oldPos;
	private int newPos;

	public SwapFilter(int oldPos, int newPos) {
		this.oldPos = oldPos;
		this.newPos = newPos;
	}

	@Override
	public void apply(List<? super NImage> images) {
		if (oldPos != newPos) {
			Collections.swap(images, oldPos, newPos);
		}
	}

	@Override
	public boolean removeAfterApply() {
		return true;
	}

}
