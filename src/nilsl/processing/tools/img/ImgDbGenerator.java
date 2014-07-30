package nilsl.processing.tools.img;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.img.RecordImage;
import nilsl.processing.lib.img.filters.LabelFilter;
import nilsl.processing.lib.io.FileRepo;

public class ImgDbGenerator {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		OutputStream fos = null;

		final String filename = args[0];
		final String imagePath = args[1];
		if (args.length != 2)
			return;

		List<String> filenames = FileRepo.listFiles(imagePath);
		//List<RecordImage> images = new ArrayList<RecordImage>();
		List<NImage> images = new ArrayList<NImage>();
		
		int i=1;
		
		//LabelFilter labelFilter = new LabelFilter();
		
		for (String name : filenames) {
			try {
				System.out.printf("Processing Image %d of %d\n", i,filenames.size());
				images.add(new NImage(name));			
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			finally
			{
				i++; 
			}
		}
		
//		List<? extends NImage> nimages = images;
//		
//		labelFilter.apply((List<NImage>)nimages);
		
		System.out.printf("Writing Result to %s",filename);
		
		fos = new FileOutputStream(filename);
		ObjectOutputStream o = new ObjectOutputStream(fos);
		o.writeObject(images);
	}

}
