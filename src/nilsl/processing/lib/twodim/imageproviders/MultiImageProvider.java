package nilsl.processing.lib.twodim.imageproviders;

import java.util.Iterator;
import java.util.List;

import nilsl.processing.lib.img.NImage;
import processing.core.PGraphics;

public abstract class MultiImageProvider extends ImageProvider implements Resetable {

	protected List<NImage> images;
	Iterator<NImage> iterator;

	public MultiImageProvider() {
		super();
	}

	@Override
	public void getNextImage(PGraphics buffer) {
		buffer.beginDraw();
		buffer.image(iterator.next().GetImage(), 0, 0, buffer.width,
				buffer.height);
		buffer.endDraw();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public void reset() {
		iterator = images.iterator();
	}

}