package nilsl.processing.lib.twodim.imageproviders;

import processing.core.PGraphics;

public abstract class ImageProvider
{
  public abstract void getNextImage(PGraphics buffer);
  public abstract boolean hasNext();

}