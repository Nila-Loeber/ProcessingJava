package nilsl.processing.sketches.mosaics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Interval;

import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.twodim.imageproviders.AlternatingImageProvider;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.imageproviders.VideoClipInfo;
import nilsl.processing.lib.twodim.imageproviders.VideoImageProvider;
import nilsl.processing.lib.twodim.mosaicdrawers.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.TintDrawer;
import nilsl.processing.lib.twodim.mosaicdrawers.TintInfo;

public class ItaloVideo3_GazeboAlternated extends MosaicEditorApplet {

private static final long serialVersionUID = 1L;

	
	public void setup() {
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings();
		settings.mosInfo.xdim = 3;
		settings.mosInfo.ydim = 3;
				
		settings.mosInfo.imgSizeX = 960/4;
		settings.mosInfo.imgSizeY = 720/4;
		
		settings.filePath="/Users/Nils/Documents/Kunst/Italo-Disco/Compositions/";
		
		super.setup(settings);
		
		mosDrawer = new DefaultMosaicDrawer(settings.mosInfo);
		mosDrawer.canvas=this.canvas;
		
		VideoClipInfo clipInfo1=new VideoClipInfo(new Interval(37*1000,42*1000),8);
		List<VideoClipInfo> clipInfos = new ArrayList<VideoClipInfo>();
		clipInfos.add(clipInfo1);
			
		List<VideoClipInfo> clipInfos2 = Arrays.asList(new VideoClipInfo(new Interval(137*1000,142*1000),8));
			
		
		VideoImageProvider vip1 = new VideoImageProvider("/Users/Nils/Documents/Processing/Video_Mosaic/Gazebo - I Like Chopin (WWF Club 9).mp4",clipInfos);
		vip1.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		vip1.ApplyFilters();
		
		VideoImageProvider vip2 = new VideoImageProvider("/Users/Nils/Documents/Processing/Video_Mosaic/Gazebo - I Like Chopin (WWF Club 9).mp4",clipInfos2);
		vip2.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		vip2.ApplyFilters();
		
		imageProvider = new AlternatingImageProvider(Arrays.asList(vip1,vip2));
		
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;
		
	} 

	
}
