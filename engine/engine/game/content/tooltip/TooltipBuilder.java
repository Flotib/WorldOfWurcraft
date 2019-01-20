package engine.game.content.tooltip;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class TooltipBuilder {
	
	/* Constants */
	public static final TooltipBuilder DEFAULT = new TooltipBuilder();
	
	/* Variables */
	private List<TooltipData> tooltipDatas;
	private TooltipData actual;
	
	/* Constructor */
	public TooltipBuilder() {
		this.tooltipDatas = new ArrayList<>();
	}
	
	/**
	 * Create a title item for the building tooltip.
	 * 
	 * @param text
	 *            Target title text.
	 * @return Itself
	 */
	public TooltipBuilder title(String text) {
		return createTooltipData(TooltipData.Type.TITLE, text);
	}
	
	/**
	 * Add a description.
	 * 
	 * @param text
	 *            Target description text.
	 * @return Itself
	 */
	public TooltipBuilder description(String text) {
		return createTooltipData(TooltipData.Type.DESCRIPTION, text);
	}
	
	/**
	 * Add an empty line.
	 * 
	 * @return Itself
	 */
	public TooltipBuilder empty() {
		return createTooltipData(TooltipData.Type.DESCRIPTION, "").finish();
	}
	
	/**
	 * Create a {@link TooltipData} and store it.
	 * 
	 * @param type
	 *            Target {@link Type}.
	 * @param text
	 *            Target text.
	 * @return Itself
	 */
	private TooltipBuilder createTooltipData(TooltipData.Type type, String text) {
		finish();
		actual = new TooltipData(type, text);
		
		return this;
	}
	
	/**
	 * Set the actual {@link TooltipData} a new text color.<br>
	 * Null values will be ignored.
	 * 
	 * @param color
	 *            New color.
	 * @return Itself.
	 */
	public TooltipBuilder color(Paint color) {
		if (actual != null) {
			actual.setColor(color);
		}
		
		return this;
	}
	
	/**
	 * Set the actual {@link TooltipData} a new text font.<br>
	 * Null values will be ignored.
	 * 
	 * @param font
	 *            New font.
	 * @return Itself.
	 */
	public TooltipBuilder font(Font font) {
		if (actual != null) {
			actual.setFont(font);
		}
		
		return this;
	}
	
	/**
	 * Finish and validate the actual {@link TooltipData}.
	 * 
	 * @return Itself.
	 */
	private TooltipBuilder finish() {
		if (actual != null) {
			tooltipDatas.add(actual);
			
			actual = null;
		}
		
		return this;
	}
	
	/**
	 * @return A {@link List} of {@link TooltipData} build with the {@link TooltipBuilder}.
	 */
	public List<TooltipData> build() {
		return tooltipDatas;
	}
	
}