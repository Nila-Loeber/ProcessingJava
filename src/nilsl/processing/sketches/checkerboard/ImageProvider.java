package nilsl.processing.sketches.checkerboard;

import processing.core.PGraphics;
import processing.core.PImage;

public interface ImageProvider
{
  void getNextImage(PGraphics pg);
  boolean hasNext(); 
}