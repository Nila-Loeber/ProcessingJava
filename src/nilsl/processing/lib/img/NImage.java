package nilsl.processing.lib.img;

import java.io.Serializable;

import nilsl.processing.lib.processingshims.PAppletShim;
import processing.core.*;

public class NImage implements Serializable
	{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	  private transient PImage image;
	  public String imageFilename;
	  int[] histogramHue=new int[256];
	  int[] histogramSat=new int[256];
	  int[] histogramBri=new int[256];
	  public int dominantHue;
	  public int dominantSat;
	  public int dominantBri;

	  public int width;
	  public int height;
	  
	  public NImage(PImage image)
	  {
		  this.image = image;
		  buildHistograms();
		  buildDominantValues();
		  width = image.width;
		  height = image.height;
	  }
	  
	  
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
	    buildDominantValues();
	  }


	private void buildDominantValues() {
		dominantHue = calcDominantValue(histogramHue);
	    dominantSat = calcDominantValue(histogramSat);
	    dominantBri = calcDominantValue(histogramBri);
	}

	  void buildHistograms()
	  {
		GetImage().loadPixels();
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
	  
	  private int calcDominantValue(int[] histogram)
	  {
		int maxCount = PApplet.max(histogram);
	    int dominantVal=0;
	    for (int i=0; i<histogram.length; i++)
	    {
	      if (histogram[i]==maxCount) dominantVal = i;
	    }
	    return dominantVal;
	  }

	
	  
	  
	}


