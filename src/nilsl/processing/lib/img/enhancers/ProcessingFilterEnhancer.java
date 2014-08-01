package nilsl.processing.lib.img.enhancers;

import processing.core.PGraphics;

public class ProcessingFilterEnhancer implements ImageEnhancer {

	private int kind;
	private int param;

	public ProcessingFilterEnhancer(int kind, int param) {
		this.kind = kind;
		this.param = param;
	}

	@Override
	public void Enhance(PGraphics pgraphic) {
		pgraphic.beginDraw();
		try // some filters take two args, some take one
		{
			pgraphic.filter(kind, param);
		} catch (RuntimeException e) {
			pgraphic.filter(kind);
		}
		pgraphic.endDraw();
	}

}
