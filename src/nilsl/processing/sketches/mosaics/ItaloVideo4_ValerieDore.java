package nilsl.processing.sketches.mosaics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Interval;

import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintInfo;
import nilsl.processing.lib.twodim.imageproviders.AlternatingImageProvider;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.imageproviders.VideoClipInfo;
import nilsl.processing.lib.twodim.imageproviders.VideoImageProvider;

public class ItaloVideo4_ValerieDore extends MosaicEditorApplet {

private static final long serialVersionUID = 1L;

	
	public void setup() {
		String videoBasePath = "/Users/Nils/Documents/Kunst/Italo-Disco/Material/Video/";
		
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings();
		settings.mosInfo.xdim = 5;
		settings.mosInfo.ydim = 5;
				
		settings.mosInfo.imgSizeX = 1280;
		settings.mosInfo.imgSizeY = 720;
		
		settings.filePath="/Users/Nils/Documents/Kunst/Italo-Disco/Compositions/";
		
		super.setup(settings);
		
		mosDrawer = new DefaultMosaicDrawer(settings.mosInfo);
		mosDrawer.canvas=this.canvas;
		
		
			
		List<VideoClipInfo> clipInfos1 = Arrays.asList(
				new VideoClipInfo(new Interval(800,2*1000),6),
				new VideoClipInfo(new Interval(26500,38*1000),5),
				new VideoClipInfo(new Interval(60*1000,62*1000),5),
				new VideoClipInfo(new Interval(68700,70*1000),5),
				new VideoClipInfo(new Interval(94500,96*1000),5),
				new VideoClipInfo(new Interval(1000,230*1000),8)
				);
			
		
		VideoImageProvider vip1 = new VideoImageProvider(videoBasePath + "Valerie_Dore_-_The_Night_original_version_HD_HQ.mp4",clipInfos1);
		vip1.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		vip1.applyFilters();
		
//		VideoImageProvider vip2 = new VideoImageProvider("/Users/Nils/Documents/Processing/Video_Mosaic/Gazebo - I Like Chopin (WWF Club 9).mp4",clipInfos2);
//		vip2.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
//		vip2.ApplyFilters();
//		
		//imageProvider = new AlternatingImageProvider(Arrays.asList(vip1,vip2));
		
		imageProvider = vip1;
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;
		
	} 

	
}
