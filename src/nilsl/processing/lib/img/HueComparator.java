package nilsl.processing.lib.img;

import java.util.Comparator;

public class HueComparator implements Comparator<NImage>{

	@Override
	public int compare(NImage o1, NImage o2) {
		    if (o1.dominantHue>o2.dominantHue)  
		      return -1;
		    if (o1.dominantHue==o2.dominantHue)
		      return 0;
		    else
		      return 1;
	}

}
