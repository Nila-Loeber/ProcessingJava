package nilsl.processing.tools.img;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.img.CompostImage;
import nilsl.processing.lib.io.FileRepo;

public class ImgDbGenerator {

	public static void main(String[] args) throws IOException {
		OutputStream fos = null;

		final String filename = args[0];
		final String imagePath = args[1];
		if (args.length != 2)
			return;

		List<String> filenames = FileRepo.listFiles(imagePath);
		List<CompostImage> images = new ArrayList<CompostImage>();
		
		int i=1;
	
		
		for (String name : filenames) {
			try {
				System.out.printf("Processing Image %d of %d\n", i,filenames.size());
				CompostImage image = new CompostImage(name);
				image.disposeImage(); 	// Ditch the actual image, we're only 
										// interested in metadata.
				images.add(image);			
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
			finally
			{
				i++; 
			}
		}

		System.out.printf("Writing Result to %s",filename);
		
		fos = new FileOutputStream(filename);
		ObjectOutputStream o = new ObjectOutputStream(fos);
		o.writeObject(images);
		o.close();
	}

}
