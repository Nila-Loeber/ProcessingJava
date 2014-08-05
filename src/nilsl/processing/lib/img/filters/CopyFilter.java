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
	public void apply(List<? super NImage> images) {
		if (oldPos != newPos) {
			images.set(newPos, (NImage)images.get(oldPos));
		}

	}

	@Override
	public boolean removeAfterApply() {
		return true;
	}

}
