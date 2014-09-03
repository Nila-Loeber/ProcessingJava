package nilsl.processing.lib.txt.textproviders;

import nilsl.processing.lib.onedim.counters.ModularCounter1d;

public class CounterProvider implements TextProvider {

	private ModularCounter1d counter1d;

	public CounterProvider(int max) {
		counter1d = new ModularCounter1d(1, max);
	}

	@Override
	public String getNextWord() {
		counter1d.inc();
		return String.valueOf(counter1d.getCurPos());
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return true;
	}

}
