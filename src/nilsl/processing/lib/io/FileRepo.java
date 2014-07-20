package nilsl.processing.lib.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRepo {
	public static List<String> listFiles(String dirName, final String[] allowedExts) {
		File directory = new File(dirName);
		
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException("Argument is not a directory.");
		}
		List<String> results = new ArrayList<String>();
		File[] files = directory.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				for (String ext: allowedExts)
				{
				 if (name.toLowerCase().endsWith(ext.toLowerCase())) return true;
				}
				return false;
			}
		});

		for (File file : files) {
			if (file.isFile()) {
				try {
					results.add(file.getCanonicalPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return results;
	}
	
	public static List<String> listFiles(String dirName, final String allowedExt) {
		return listFiles(dirName, new String[] {allowedExt});
	}
	
	public static List<String> listFiles(String dirName) {
		return listFiles(dirName, new String[] {"jpg","jpeg","png","gif"});
	}
	
	
}
