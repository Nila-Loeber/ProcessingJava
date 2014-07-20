package nilsl.processing.lib.twodim.imageproviders;

import nilsl.processing.lib.img.NImage;
import processing.core.PGraphics;


public class MonoImageProvider implements ImageProvider {

	NImage image;
	
	public MonoImageProvider(String filepath)
	{
		image = new NImage(filepath);
	}
	
	@Override
	public void getNextImage(PGraphics buffer) {
		buffer.beginDraw();
		buffer.image(image.image,0,0,buffer.width,buffer.height);
		buffer.endDraw();
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
