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
import nilsl.processing.lib.applet.mosaic.midi.ControlType;
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

			ControlType ct = determineControlType(cc);

			switch (ct) {
			case SLIDER:
				handleSlider(cc);
				break;
			case ROTARY:
				handleRotary(cc);
				break;
			case OTHER:
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

		private void handleRotary(ControlChange cc) {
			int knobIndex = getKeyByValue(rotaryKnobMappings, cc.number);
			if (cc.value==1) rotaryKnobs[knobIndex]++;
			if (cc.value==127) rotaryKnobs[knobIndex]--;
			logger.trace(String.format("Moved rotary knob %d. New Value: %d.",knobIndex,rotaryKnobs[knobIndex]));
			
		}

		private void handleSlider(ControlChange cc) {
			int sliderIndex = getKeyByValue(sliderMappings, cc.number);
			sliders[sliderIndex] = cc.value;
			logger.trace("Moved slider " + sliderIndex);
		}

		private ControlType determineControlType(ControlChange cc) {
			if (sliderMappings.containsValue(cc.number)) return ControlType.SLIDER;
			if (rotaryKnobMappings.containsValue(cc.number)) return ControlType.ROTARY;
			return ControlType.OTHER;
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

	protected int[] rotaryKnobs = new int[8];
	private HashMap<Integer, Integer> rotaryKnobMappings;

	static final Logger logger = LogManager
			.getLogger(MidifiedMosaicEditorApplet.class.getPackage().getName());

	public void setup(MosaicEditorAppletSettings settings) {
		logger.info("Found MIDI inputs: ",
				Arrays.toString(MidiBus.availableInputs()));
		midiBus = new MidiBus((PApplet) this, 0, 0);

		setupControllerMappings();
		setupRotaryKnobMappings();

		midiBus.addMidiListener(midiListener);

		super.setup(settings);
	}

	private void setupRotaryKnobMappings() {
		rotaryKnobMappings = new HashMap<Integer, Integer>();
		rotaryKnobMappings.put(0, 106);
		rotaryKnobMappings.put(1, 102);
		rotaryKnobMappings.put(2, 107);
		rotaryKnobMappings.put(3, 103);
		rotaryKnobMappings.put(4, 108);
		rotaryKnobMappings.put(5, 104);
		rotaryKnobMappings.put(6, 109);
		rotaryKnobMappings.put(7, 105);

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
