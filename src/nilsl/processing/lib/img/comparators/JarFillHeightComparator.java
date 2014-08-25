package nilsl.processing.lib.img.comparators;

import java.util.Comparator;

import nilsl.processing.lib.img.CompostImage;
import nilsl.processing.lib.img.NImage;

public class JarFillHeightComparator implements Comparator<NImage> {

	@Override
	public int compare(NImage o1, NImage o2) {
		    if (((CompostImage)o1).fillHeight > ((CompostImage)o2).fillHeight)  
		      return -1;
		    if (((CompostImage)o1).fillHeight == ((CompostImage)o2).fillHeight)
		      return 0;
		    else
		      return 1;
	}
}
