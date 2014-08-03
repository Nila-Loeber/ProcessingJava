package nilsl.processing.lib.twodim.imageproviders;


import org.joda.time.Interval;

public class VideoClipInfo {
	Interval interval;
	int numPictures;
	
	public VideoClipInfo(Interval interval,int numPictures) {
		this.interval = interval;
		this.numPictures = numPictures;
	}
	
}
