package game.tooltip;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class TooltipBuilder {
	
	public static final TooltipBuilder DEFAULT = new TooltipBuilder();
	
	private List<TooltipData> tooltipDatas;
	private TooltipData actual;
	
	public TooltipBuilder() {
		this.tooltipDatas = new ArrayList<>();
	}
	
	public TooltipBuilder title(String text) {
		finish();
		actual = new TooltipData(TooltipData.Type.TITLE);
		
		return this;
	}
	
	public TooltipBuilder description(String text) {
		finish();
		actual = new TooltipData(TooltipData.Type.TITLE);
		
		return this;
	}
	
	public TooltipBuilder color(Paint color) {
		if (actual != null) {
			actual.setColor(color);
		}
		
		return this;
	}
	
	public TooltipBuilder font(Font font) {
		if (actual != null) {
			actual.setFont(font);
		}
		
		return this;
	}
	
	private void finish() {
		if (actual != null) {
			tooltipDatas.add(actual);
			
			actual = null;
		}
	}
	
	public List<TooltipData> build() {
		return tooltipDatas;
	}
	
}