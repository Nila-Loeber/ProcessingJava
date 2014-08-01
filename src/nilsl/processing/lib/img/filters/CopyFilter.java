package nilsl.processing.lib.img.filters;

import java.util.List;

import nilsl.processing.lib.img.NImage;

public class CopyFilter implements FilterCommand {

	private int oldPos;
	private int newPos;

	public CopyFilter(int oldPos, int newPos) {
		this.oldPos = oldPos;
		this.newPos = newPos;
	}
	
	@Override
	public void apply(List<NImage> images) {
		if (oldPos != newPos) {
			images.set(newPos, images.get(oldPos));
		}

	}

	@Override
	public boolean removeAfterApply() {
		return true;
	}

}
