package nilsl.processing.lib.img;

import java.io.Serializable;

import nilsl.processing.lib.processingshims.PAppletShim;
import processing.core.*;

public class NImage implements Comparable<NImage>, Serializable
	{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  private transient PImage image;
	  public String imageFilename;
	  int[] histogramHue=new int[256];
	  int[] histogramSat=new int[256];
	  int[] histogramBri=new int[256];
	  int dominantHue;

	  public PImage GetImage()
	  {
		  if (image==null)
		  {
			  PImage img = PAppletShim.getApplet().loadImage(this.imageFilename);
			    image=img;
		  }
		  return image;
	  }
	  
	  public NImage(String filename)
	  {
	    imageFilename = filename;
	    buildHistograms();
	    dominantHue = calcDominantHue_MaxValue();
	  }

	  void buildHistograms()
	  {
	    for (int i=0; i<GetImage().pixels.length; i++)
	    {
	      int pixel=GetImage().pixels[i];
	      int hue = (int)(PAppletShim.getApplet().hue(pixel));
	      histogramHue[hue]++;
	      int sat = (int)(PAppletShim.getApplet().saturation(pixel));
	      histogramSat[sat]++;
	      int bri = (int)(PAppletShim.getApplet().brightness(pixel));
	      histogramBri[bri]++;
	    }
	  }
	  
	  public int compareTo(NImage o)
	  {
	    NImage other=o;
	    if (other.dominantHue>dominantHue)  
	      return -1;
	    if (other.dominantHue==dominantHue)
	      return 0;
	    else
	      return 1;
	  }

	  private int calcDominantHue_MaxValue()
	  {
		int maxCount = PApplet.max(histogramHue);
	    int dominantHue=0;
	    for (int i=0; i<histogramHue.length; i++)
	    {
	      if (histogramHue[i]==maxCount) dominantHue = i;
	    }
	    return dominantHue;
	  }

	
	  
	  
	}


