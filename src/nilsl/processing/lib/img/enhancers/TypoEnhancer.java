package nilsl.processing.lib.img.enhancers;

import nilsl.processing.lib.onedim.counters.Counter1d;
import nilsl.processing.lib.processingshims.PAppletShim;
import nilsl.processing.lib.txt.textproviders.TextProvider;
import processing.core.PFont;
import processing.core.PGraphics;

public class TypoEnhancer implements ImageEnhancer {

	Counter1d counter1d;
	PFont font;
	private float fontSize;
	private int x;
	private int y;
	private TextProvider textProvider;
	private int color;

	public TypoEnhancer(String fontPath, float fontSize, int x, int y,
			int color, TextProvider provider) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.textProvider = provider;
		font = PAppletShim.getApplet().loadFont(fontPath);
		this.fontSize = fontSize;
	}

	@Override
	public void Enhance(PGraphics pgraphic) {
		pgraphic.beginDraw();
		pgraphic.textFont(font);
		pgraphic.textSize(fontSize);
		pgraphic.fill(color);
		pgraphic.text(textProvider.getNextWord(), x, y);
		pgraphic.endDraw();
	}

}
