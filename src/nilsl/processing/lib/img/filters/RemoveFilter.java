package nilsl.processing.lib.img.filters;

import java.util.List;

import nilsl.processing.lib.img.NImage;

public class RemoveFilter implements FilterCommand {

	private int imgToRemove;

	public RemoveFilter(int imgToRemove) {
	this.imgToRemove=imgToRemove;
	}
	
	@Override
	public void apply(List<NImage> images) {
		images.remove(imgToRemove);
	}

	@Override
	public boolean removeAfterApply() {
		// TODO Auto-generated method stub
		return true;
	}

}
