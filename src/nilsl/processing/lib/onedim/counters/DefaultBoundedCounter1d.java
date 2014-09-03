package nilsl.processing.lib.onedim.counters;

public class DefaultBoundedCounter1d implements Counter1d {

	int counter;
	private int step;
	private int start;
	private int end;

	public DefaultBoundedCounter1d(int start, int step, int end) {
		this.start = start;
		this.step = step;
		this.end = end;
		counter = start;
	}

	@Override
	public int getCurPos() {
		return counter;
	}

	@Override
	public boolean eof() {
		return counter >= end;
	}

	@Override
	public void inc() {
		if (counter <= end)
			counter += step;
	}

	@Override
	public void reset() {
		counter = 0;

	}

}
