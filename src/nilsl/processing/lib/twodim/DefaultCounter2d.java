package nilsl.processing.lib.twodim;

public class DefaultCounter2d implements Counter2d {

	protected int curX = 0;
	protected int curY = 0;
	protected int width;
	protected int height;
	protected int maxX;
	protected int maxY;

	public DefaultCounter2d(int width, int height) {
		this.maxX = width - 1;
		this.maxY = height - 1;
		this.width = width;
		this.height = height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nilsloeber.processing.lib.twodim.Counter2d#getCurX()
	 */
	@Override
	public int getCurX() {
		return curX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nilsloeber.processing.lib.twodim.Counter2d#getCurY()
	 */
	@Override
	public int getCurY() {
		return curY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nilsloeber.processing.lib.twodim.Counter2d#incX()
	 */
	@Override
	public void incX() {
		curX++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nilsloeber.processing.lib.twodim.Counter2d#incY()
	 */
	@Override
	public void incY() {
		curY++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nilsloeber.processing.lib.twodim.Counter2d#getCurPos()
	 */
	@Override
	public int getCurPos() {
		return (curY * width + curX) + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nilsloeber.processing.lib.twodim.Counter2d#hasNext()
	 */
	@Override
	public boolean eof() {
		return getCurPos() == (width * height) + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nilsloeber.processing.lib.twodim.Counter2d#inc()
	 */
	@Override
	public void inc() {
		System.out.println(curX + ":" + curY + " : CurPos: " + getCurPos());
		if (curX == maxX && curY == maxY) {
			curX = maxX + 1; // advance to EOF position
			return;
		}
		curX++;
		if (curX > maxX) {
			curX = 0;
			if (curY < maxY) {
				curY++;
			}
		}
	}
}
