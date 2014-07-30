package nilsl.processing.lib.img;

import java.util.Comparator;

public class BriComparator implements Comparator<NImage>{

	@Override
	public int compare(NImage o1, NImage o2) {
		    if (o1.dominantBri>o2.dominantBri)  
		      return -1;
		    if (o1.dominantBri==o2.dominantBri)
		      return 0;
		    else
		      return 1;
	}

}
