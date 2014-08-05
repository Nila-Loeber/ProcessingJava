package nilsl.processing.lib.img.filters;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nilsl.processing.lib.img.NImage;

public class HueBPF implements FilterCommand {

	
	private int min;
	private int max;

	public HueBPF(int min, int max) {
		this.min = min;
		this.max = max;
		
	}
	
	@Override
	public void apply(List<? super NImage> images) {
		List<? super NImage> results = images
				.stream()
				.filter((Object ni) -> ((NImage)ni).dominantHue >= min
						&& ((NImage)ni).dominantHue <= max)
				.collect(Collectors.toList());

		images.clear();
		images.addAll((Collection<NImage>) results);
	}

	@Override
	public boolean removeAfterApply() {
		return false;
	}

}
