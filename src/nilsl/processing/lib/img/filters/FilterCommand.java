package nilsl.processing.lib.img.filters;

import java.util.List;

import nilsl.processing.lib.img.NImage;

public interface FilterCommand {
	void apply(List<NImage> images);
	boolean removeAfterApply();
}
