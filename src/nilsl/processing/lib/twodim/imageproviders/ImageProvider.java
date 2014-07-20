package nilsl.processing.lib.twodim.imageproviders;

import processing.core.PGraphics;
import processing.core.PImage;

public abstract class ImageProvider
{
  public abstract void getNextImage(PGraphics buffer);
  public abstract boolean hasNext();

}