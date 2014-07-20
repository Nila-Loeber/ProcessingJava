package nilsl.processing.sketches.checkerboard;

import nilsl.processing.lib.twodim.DefaultCounter2d;

public class DirectionChangerCounter2d extends DefaultCounter2d {

	public DirectionChangerCounter2d(int maxX, int maxY) {
		super(maxX, maxY);
		// TODO Auto-generated constructor stub
	}

	private int direction = 1;

	@Override
	public void inc() {
		if (direction == 1) {
			if (curX == maxX) {
				if (curY == maxY) {
					return;
				}
				curX = maxX;
				curY++;
				direction = -1;
			} else {
				curX++;
			}
			return;
		} else {
			if (curX == 0) {
				if (curY == maxY) {
					return;
				}
				curX = 0;
				curY++;
				direction = 1;
			} else {
				curX--;
			}
			return;
		}
	}

	@Override
	public void incX() {
		curX += 1 * direction;
	}

	@Override
	public boolean eof() {
		System.out.println(getCurPos());
		return getCurPos() > maxX * maxY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nilsloeber.processing.lib.twodim.Counter2d#incY()
	 */
	@Override
	public void incY() {
		curY += 1 * direction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nilsloeber.processing.lib.twodim.Counter2d#getCurPos()
	 */
	@Override
	public int getCurPos() {
		if (direction == 1) {
			return curY * (maxX+1) + curX;
		} else {
			return curY * (maxX+1) + (maxX-curX);
		}
	}

}
