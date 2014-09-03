package nilsl.processing.lib.img.comparators;

import java.util.Comparator;

import nilsl.processing.lib.img.NImage;

public class HueComparator implements Comparator<NImage> {

	@Override
	public int compare(NImage o1, NImage o2) {
		if (o1.dominantHue > o2.dominantHue)
			return -1;
		if (o1.dominantHue == o2.dominantHue)
			return 0;
		else
			return 1;
	}

}
