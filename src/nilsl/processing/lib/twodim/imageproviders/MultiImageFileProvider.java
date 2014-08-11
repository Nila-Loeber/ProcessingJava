package nilsl.processing.lib.twodim.imageproviders;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nilsl.processing.lib.img.NImage;

public class MultiImageFileProvider extends MultiImageProvider  {

	static final Logger logger = LogManager.getLogger(MultiImageFileProvider.class
			.getPackage().getName());
	
	public MultiImageFileProvider(String dataFile) throws IOException,
			ClassNotFoundException {
		InputStream fis = null;
		fis = new FileInputStream(dataFile);
		ObjectInputStream o = new ObjectInputStream(fis);

		images = (List<NImage>) o.readObject();
		o.close();
		logger.info(String.format("Loaded %d files from %s.",images.size(),dataFile));
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
		logger.info(String.format("Loaded %d files from concrete list of files.",images.size()));
		reset();
	}

}
