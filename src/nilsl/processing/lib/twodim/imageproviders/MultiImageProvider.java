package nilsl.processing.lib.twodim.imageproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nilsl.processing.lib.img.NImage;
import processing.core.PGraphics;


public class MultiImageProvider extends ImageProvider implements Resetable {

	List<NImage> images;
	Iterator<NImage> iterator;
	
	public MultiImageProvider(List<String> filenames)
	{
		images = new ArrayList<NImage>();
		for (String filename: filenames)
		{
			try {
				images.add(new NImage(filename));
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}	
		reset();
	}
	
	@Override
	public void getNextImage(PGraphics buffer) {
		buffer.beginDraw();
		buffer.image(iterator.next().image,0,0,buffer.width,buffer.height);
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
