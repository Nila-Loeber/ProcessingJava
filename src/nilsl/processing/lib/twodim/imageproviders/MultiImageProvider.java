package nilsl.processing.lib.twodim.imageproviders;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nilsl.processing.lib.img.NImage;
import processing.core.PGraphics;

public class MultiImageProvider extends ImageProvider implements Resetable {

	List<NImage> images;
	Iterator<NImage> iterator;

	public MultiImageProvider(String dataFile) throws IOException,
			ClassNotFoundException {
		InputStream fis = null;
		fis = new FileInputStream(dataFile);
		ObjectInputStream o = new ObjectInputStream(fis);

		images = (List<NImage>) o.readObject();
		o.close();
		
		reset();
	}

	public MultiImageProvider(List<String> filenames) {
		images = new ArrayList<NImage>();
		for (String filename : filenames) {
			try {
				images.add(new NImage(filename));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		reset();
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
