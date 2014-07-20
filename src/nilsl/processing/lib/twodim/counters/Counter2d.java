package nilsl.processing.lib.twodim.counters;

public interface Counter2d {

	public abstract int getCurX();

	public abstract int getCurY();

	public abstract void incX();

	public abstract void incY();

	public abstract int getCurPos();

	public abstract boolean eof();

	public abstract void inc();

}