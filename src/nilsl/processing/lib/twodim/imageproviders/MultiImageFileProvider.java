package nilsl.processing.lib.twodim.imageproviders;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.img.NImage;

public class MultiImageFileProvider extends MultiImageProvider  {

	public MultiImageFileProvider(String dataFile) throws IOException,
			ClassNotFoundException {
		InputStream fis = null;
		fis = new FileInputStream(dataFile);
		ObjectInputStream o = new ObjectInputStream(fis);

		images = (List<NImage>) o.readObject();
		o.close();
		
		reset();
	}

	public MultiImageFileProvider(List<String> filenames) {
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

}
