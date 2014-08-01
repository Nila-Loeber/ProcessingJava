package nilsl.processing.lib.onedim.counters;

public class DefaultCounter1d implements Counter1d {

	int counter = 0;
	private int step;

	public DefaultCounter1d(int step) {
		this.step = step;
	}

	public DefaultCounter1d() {
		this.step = 1;
	}

	@Override
	public int getCurPos() {
		return counter;
	}

	@Override
	public boolean eof() {
		return false;
	}

	@Override
	public void inc() {
		counter+=step;

	}

	@Override
	public void reset() {
		counter=0;
		
	}

}
