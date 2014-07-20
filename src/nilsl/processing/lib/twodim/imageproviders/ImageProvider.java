package nilsl.processing.lib.twodim.imageproviders;

import processing.core.PGraphics;
import processing.core.PImage;

public interface ImageProvider
{
  void getNextImage(PGraphics buffer);
  boolean hasNext(); 
}