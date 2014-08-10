package nilsl.processing.sketches.mosaics;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Interval;

import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.enhancers.CutupEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.twodim.drawers.mosaic.CutupDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintInfo;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.imageproviders.VideoClipInfo;
import nilsl.processing.lib.twodim.imageproviders.VideoImageProvider;

public class ItaloVideo2_GazeboCutup extends MosaicEditorApplet {

private static final long serialVersionUID = 1L;

	
	public void setup() {
		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings();
		settings.mosInfo.xdim = 1;
		settings.mosInfo.ydim = 12;
				
		settings.mosInfo.imgSizeX = 960;
		settings.mosInfo.imgSizeY = 720 / settings.mosInfo.ydim;
		
		settings.filePath="/Users/Nils/Documents/Kunst/Italo-Disco/Compositions/";
		
		super.setup(settings);
		
		mosDrawer = new CutupDrawer(settings.mosInfo);
		mosDrawer.canvas=this.canvas;

		mosDrawer.imageEnhancers.add(new CutupEnhancer(settings.mosInfo.ydim));		
		
		//VideoClipInfo clipInfo1=new VideoClipInfo(new Interval(37*1000,38*1000),20);
		VideoClipInfo clipInfo2=new VideoClipInfo(new Interval(37*1000,38*1000),settings.mosInfo.Size());
		//VideoClipInfo clipInfo2=new VideoClipInfo(new Interval(40*1000,60*1000),settings.mosInfo.Size()/2);
		List<VideoClipInfo> clipInfos = new ArrayList<VideoClipInfo>();
		//clipInfos.add(clipInfo1);
		clipInfos.add(clipInfo2);
		
		imageProvider = new VideoImageProvider("/Users/Nils/Documents/Processing/Video_Mosaic/Gazebo - I Like Chopin (WWF Club 9).mp4",clipInfos);
		imageProvider.setFilterProcessor(new FilterProcessor(new ArrayList<FilterCommand>()));
		imageProvider.ApplyFilters();
		
		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;
		
	} 

	
}
