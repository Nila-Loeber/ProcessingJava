package nilsl.processing.lib.img.filters;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nilsl.processing.lib.img.CompostImage;
import nilsl.processing.lib.img.NImage;
import nilsl.processing.lib.img.comparators.BriComparator;
import nilsl.processing.lib.img.comparators.JarFillHeightComparator;

public class JarFillHeightFilter implements FilterCommand {

	boolean desc;

	static final Logger logger = LogManager.getLogger(JarFillHeightFilter.class
			.getPackage().getName());

	public JarFillHeightFilter(boolean desc) {
		this.desc = desc;
	}

	@Override
	public void apply(List<? super NImage> images) {
		logger.info("Applying filter.");
		if (images.size() > 0 && images.get(0) instanceof CompostImage) {
			((List<NImage>) images).sort(new JarFillHeightComparator());
			if (desc)
				Collections.reverse(images);
		}
	}

	@Override
	public boolean removeAfterApply() {
		return true;
	}

}
