package nilsl.processing.lib.onedim.counters;

public class ModularCounter1d implements Counter1d {

	int counter = 0;
	private int step;
	private int modulus;

	public ModularCounter1d(int step, int modulus) {
		this.step = step;
		this.modulus = modulus;
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
		counter += step;
		if (counter % modulus == 0)
			counter = 0;
	}

	@Override
	public void reset() {
		counter = 0;

	}

}
