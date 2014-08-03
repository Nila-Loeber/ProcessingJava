package nilsl.processing.sketches.mosaics;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Interval;

import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.imageproviders.VideoClipInfo;
import nilsl.processing.lib.twodim.imageproviders.VideoImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;

public class ItaloVideo1_Gazebo extends MosaicEditorApplet {

private static final long serialVersionUID = 1L;

	
	public void setup() {
		mosInfo.xdim = 4;
		mosInfo.ydim = 4;
				
		mosInfo.imgSizeX = 480/2;
		mosInfo.imgSizeY = 360/2;
		
		mosDrawer = new DefaultMosaicDrawer(mosInfo);
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.POSTERIZE,4));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.GRAY,3));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.THRESHOLD,0.5f));
		//mosDrawer.imageEnhancers.add(new ProcessingFilterEnhancer(PShader.BLUR,5));
		
		//mosDrawer.imageEnhancers.add(new CutupEnhancer(mosInfo.ydim));		
		
		VideoClipInfo clipInfo=new VideoClipInfo(new Interval(10*1000,20*1000),mosInfo.Size());
		List<VideoClipInfo> clipInfos = new ArrayList<VideoClipInfo>();
		clipInfos.add(clipInfo);
		imageProvider = new VideoImageProvider("c:\\data\\videos\\Gazebo - Masterpiece.flv",clipInfos);
		imageProvider.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		imageProvider.ApplyFilters();
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;

		super.setup();
	} 
	
}
