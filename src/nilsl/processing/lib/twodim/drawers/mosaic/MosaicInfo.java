package nilsl.processing.lib.twodim.drawers.mosaic;

public class MosaicInfo {
	public int imgSizeX;
	public int imgSizeY;
	public int xdim;
	public int ydim;
	
	public int Size()
	{
		return xdim*ydim;
	}
}