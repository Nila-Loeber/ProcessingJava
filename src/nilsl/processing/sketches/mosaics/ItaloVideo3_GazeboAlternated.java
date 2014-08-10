package nilsl.processing.sketches.mosaics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Interval;

import nilsl.processing.lib.applet.FilesaveApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.MosaicInfo;
import nilsl.processing.lib.twodim.drawers.mosaic.TintDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintInfo;
import nilsl.processing.lib.twodim.imageproviders.AlternatingImageProvider;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.imageproviders.VideoClipInfo;
import nilsl.processing.lib.twodim.imageproviders.VideoImageProvider;

public class ItaloVideo3_GazeboAlternated extends FilesaveApplet {

private static final long serialVersionUID = 1L;

	DefaultMosaicDrawer mosDrawer;
	
	public void setup() {
		String videoBasePath = "/Users/Nils/Documents/Kunst/Italo-Disco/Material/Video/";
		
		MosaicInfo mosInfo = new MosaicInfo();
		mosInfo.xdim = 5;
		mosInfo.ydim = 5;
	
		mosInfo.imgSizeX = 1280;
		mosInfo.imgSizeY = 720;
		
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings(mosInfo);
		
		
		settings.filePath="/Users/Nils/Documents/Kunst/Italo-Disco/Compositions/";
		
		super.setup(settings);
		
		mosDrawer = new DefaultMosaicDrawer(settings.mosInfo);
		mosDrawer.canvas=this.canvas;
		
		
			
		List<VideoClipInfo> clipInfos1 = Arrays.asList(
				new VideoClipInfo(new Interval(600,2*1000),5),
				new VideoClipInfo(new Interval(26500,38*1000),5),
				new VideoClipInfo(new Interval(60*1000,62*1000),5)
				);
			
		List<VideoClipInfo> clipInfos2 = Arrays.asList(
				new VideoClipInfo(new Interval(37000,42000),15)
				
				);
		
		VideoImageProvider vip1 = new VideoImageProvider(videoBasePath + "Valerie_Dore_-_The_Night_original_version_HD_HQ.mp4",clipInfos1);
		vip1.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		vip1.ApplyFilters();
		
		VideoImageProvider vip2 = new VideoImageProvider("/Users/Nils/Documents/Processing/Video_Mosaic/Gazebo - I Like Chopin (WWF Club 9).mp4",clipInfos2);
		vip2.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		vip2.ApplyFilters();
		
		AlternatingImageProvider imageProvider = new AlternatingImageProvider(Arrays.asList(vip1,vip2));
		
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;		
	} 
	
	@Override
	public void draw()
	{
		mosDrawer.draw();
		super.draw();
	}
	

	
}
