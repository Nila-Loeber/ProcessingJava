package nilsl.processing.lib.onedim.counters;

public interface Counter1d {

	public abstract int getCurPos();

	public abstract boolean eof();

	public abstract void inc();
	
	public abstract void reset();

}
