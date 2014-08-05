package nilsl.processing.lib.twodim.imageproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nilsl.processing.lib.img.NImage;
import processing.core.PGraphics;
import processing.core.PImage;

public abstract class MultiImageProvider extends ImageProvider implements Resetable {

	protected List<? super NImage> images = new ArrayList<NImage>();
	Iterator<? super NImage> iterator;

	public MultiImageProvider() {
		super();
	}

	@Override
	public void getNextImage(PGraphics buffer) {
		buffer.beginDraw();
		PImage nextImage = ((NImage)iterator.next()).GetImage();
		nextImage.parent = buffer.parent;
		buffer.image(nextImage, 0, 0, buffer.width,
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