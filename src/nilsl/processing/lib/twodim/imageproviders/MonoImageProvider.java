package nilsl.processing.lib.twodim.imageproviders;

import nilsl.processing.lib.img.NImage;
import processing.core.PGraphics;


public class MonoImageProvider extends ImageProvider implements Resetable {

	NImage image;
	
	public MonoImageProvider(String filepath)
	{
		image = new NImage(filepath);
	}
	
	@Override
	public void getNextImage(PGraphics buffer) {
		buffer.beginDraw();
		buffer.image(image.getImage(),0,0,buffer.width,buffer.height);
		buffer.endDraw();
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public void reset() {
	
	}

	
}
