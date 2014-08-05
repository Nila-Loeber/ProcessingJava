package nilsl.processing.lib.img.filters;

import java.util.List;
import java.util.stream.Collectors;

import nilsl.processing.lib.img.NImage;

public class SizeFilter implements FilterCommand {

	private int y;
	private int x;

	public SizeFilter(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void apply(List<? super NImage> images) {
		images = images
				.stream()
				.filter((Object ni) -> ((NImage)ni).width >= this.x
						&& ((NImage)ni).height >= this.y)
				.collect(Collectors.toList());
		;
	}

	@Override
	public boolean removeAfterApply() {
		return false;
	}

}
