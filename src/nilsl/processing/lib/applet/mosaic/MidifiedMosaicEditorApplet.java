package nilsl.processing.lib.applet.mosaic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import processing.core.PApplet;
import nilsl.processing.lib.applet.ViewerApplet;
import nilsl.processing.lib.applet.NApplet;
import nilsl.processing.lib.img.filters.CopyFilter;
import nilsl.processing.lib.img.filters.NewImageFilter;
import nilsl.processing.lib.img.filters.RandomizeFilter;
import nilsl.processing.lib.img.filters.RemoveFilter;
import nilsl.processing.lib.img.filters.SwapFilter;
import nilsl.processing.lib.twodim.drawers.mosaic.EnhanceableMosaicDrawer2d;
import nilsl.processing.lib.twodim.drawers.mosaic.MosaicInfo;
import nilsl.processing.lib.twodim.imageproviders.Filterable;
import nilsl.processing.lib.twodim.imageproviders.ReInitable;
import themidibus.ControlChange;
import themidibus.MidiBus;
import themidibus.MidiListener;
import themidibus.Note;
import themidibus.ObjectMidiListener;
import themidibus.SimpleMidiListener;
import themidibus.StandardMidiListener;

public abstract class MidifiedMosaicEditorApplet extends MosaicEditorApplet {

	ObjectMidiListener midiListener = new ObjectMidiListener() {

		@Override
		public void noteOn(Note arg0) {
		}

		@Override
		public void noteOff(Note arg0) {
		}

		@Override
		public void controllerChange(ControlChange cc) {
			logger.trace(String
					.format("controllerChange received. Channel: %d Number: %d Value: %d",
							cc.channel, cc.number, cc.value));

			if (sliderMappings.containsValue(cc.number)) {
				int sliderIndex = getKeyByValue(sliderMappings, cc.number);
				sliders[sliderIndex] = cc.value;
				logger.trace("Moved slider " + sliderIndex);
			} else {
				switch (cc.number) {
				case 25:
					handleSave();
					break;
				default:
					return;
				}
			}
			handleReInit();
		}
	};

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private MidiBus midiBus;

	private MidiBus midiBus;

	protected int[] sliders = new int[8];
	private HashMap<Integer, Integer> sliderMappings;

	static final Logger logger = LogManager
			.getLogger(MidifiedMosaicEditorApplet.class.getPackage().getName());

	public void setup(MosaicEditorAppletSettings settings) {
		logger.info("Found MIDI inputs: ",
				Arrays.toString(MidiBus.availableInputs()));
		midiBus = new MidiBus((PApplet) this, 0, 0);

		setupControllerMappings();

		midiBus.addMidiListener(midiListener);

		super.setup(settings);
	}

	private void setupControllerMappings() {
		sliderMappings = new HashMap<Integer, Integer>();
		sliderMappings.put(0, 74);
		sliderMappings.put(1, 71);
		sliderMappings.put(2, 91);
		sliderMappings.put(3, 93);
		sliderMappings.put(4, 73);
		sliderMappings.put(5, 72);
		sliderMappings.put(6, 5);
		sliderMappings.put(7, 84);

	}

	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

}
