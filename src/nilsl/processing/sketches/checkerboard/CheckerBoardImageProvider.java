package nilsl.processing.sketches.checkerboard;


import processing.core.PGraphics;


public class CheckerBoardImageProvider implements ImageProvider {


	private int color;

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	public CheckerBoardImageProvider() {
		color=255;
	}
	
	@Override
	public void getNextImage(PGraphics buffer) {
		buffer.beginDraw();
		buffer.background(color);
		buffer.endDraw();
		color=Math.abs(color-255);
	}

}
