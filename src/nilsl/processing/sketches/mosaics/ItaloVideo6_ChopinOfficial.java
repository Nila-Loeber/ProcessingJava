package nilsl.processing.sketches.mosaics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Interval;

import processing.opengl.PShader;
import nilsl.processing.lib.applet.mosaic.MosaicEditorApplet;
import nilsl.processing.lib.applet.mosaic.MosaicEditorAppletSettings;
import nilsl.processing.lib.img.enhancers.ProcessingFilterEnhancer;
import nilsl.processing.lib.img.filters.FilterCommand;
import nilsl.processing.lib.twodim.drawers.mosaic.DefaultMosaicDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintDrawer;
import nilsl.processing.lib.twodim.drawers.mosaic.TintInfo;
import nilsl.processing.lib.twodim.imageproviders.AlternatingImageProvider;
import nilsl.processing.lib.twodim.imageproviders.FilterProcessor;
import nilsl.processing.lib.twodim.imageproviders.ImageProvider;
import nilsl.processing.lib.twodim.imageproviders.VideoClipInfo;
import nilsl.processing.lib.twodim.imageproviders.VideoImageProvider;

public class ItaloVideo6_ChopinOfficial extends MosaicEditorApplet {

	private static final long serialVersionUID = 1L;

	public void setup() {
		String videoBasePath = "/Users/Nils/Documents/Kunst/Italo-Disco/Material/Video/";

		MosaicEditorAppletSettings settings = new MosaicEditorAppletSettings();
		settings.mosInfo.xdim = 5;
		settings.mosInfo.ydim = 5;

		settings.mosInfo.imgSizeX = 640;
		settings.mosInfo.imgSizeY = 360;

		settings.filePath = "/Users/Nils/Documents/Kunst/Italo-Disco/Compositions/";

		super.setup(settings);

		mosDrawer = new DefaultMosaicDrawer(settings.mosInfo);
		mosDrawer.canvas = this.canvas;

		// List<VideoClipInfo> clipInfos1 = Arrays.asList(
		// new VideoClipInfo(new Interval(800,9*1000),5),
		// new VideoClipInfo(new Interval(30*1000,35*1000),5),
		// new VideoClipInfo(new Interval(50*1000,55*1000),5),
		// new VideoClipInfo(new Interval(60*1000,67*1000),5),
		// new VideoClipInfo(new Interval(87*1000,95*1000),5)
		//
		// );

		List<VideoClipInfo> clipInfos1 = Arrays.asList(

		new VideoClipInfo(new Interval(30 * 1000, 41 * 1000), 35)

		);

		VideoImageProvider vip1 = new VideoImageProvider(videoBasePath
				+ "Gazebo_-_I_Like_Chopin.mp4", clipInfos1);
		vip1.setFilterProcessor(new FilterProcessor(
				new ArrayList<FilterCommand>()));
		vip1.applyFilters();

		imageProvider = vip1;

		mosDrawer.imageProvider = (ImageProvider) imageProvider;
		mosDrawer.parentApplet = this;

	}

}
