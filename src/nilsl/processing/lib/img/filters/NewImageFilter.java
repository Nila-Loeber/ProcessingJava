package nilsl.processing.lib.img.filters;

import java.util.List;

import nilsl.processing.lib.img.NImage;

public class NewImageFilter implements FilterCommand {

	
	
	private int pos;

	public NewImageFilter(int pos) {
		this.pos = pos;
	}

	@Override
	public void apply(List<? super NImage> images) {
		int replacementImgPos = (int) (Math.random()*images.size());
		images.set(pos, (NImage)images.get(replacementImgPos));
	}

	@Override
	public boolean removeAfterApply() {
		// TODO Auto-generated method stub
		return true;
	}

}
