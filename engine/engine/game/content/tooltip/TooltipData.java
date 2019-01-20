package engine.game.content.tooltip;

import engine.ui.components.UIComponent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class TooltipData {
	
	/* Variable */
	private final Type type;
	private final String text;
	private Paint color;
	private Font font;
	
	/* Constructor */
	protected TooltipData(Type type, String text) {
		this.type = type;
		this.text = text;
		
		this.color = UIComponent.DEFAULT_COLOR;
		this.font = UIComponent.DEFAULT_FONT;
	}
	
	/**
	 * @return {@link TooltipData}'s {@link Type}.
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * @return {@link TooltipData}'s text.
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @return {@link TooltipData}'s text color.
	 */
	public Paint getColor() {
		return color;
	}
	
	/**
	 * Set a new text color for this {@link TooltipData}.
	 * 
	 * @param color
	 *            New color.
	 */
	public void setColor(Paint color) {
		this.color = color;
	}
	
	/**
	 * @return {@link TooltipData}'s text font.
	 */
	public Font getFont() {
		return font;
	}
	
	/**
	 * Set a new text font for this {@link TooltipData}.
	 * 
	 * @param font
	 *            New font.
	 */
	public void setFont(Font font) {
		this.font = font;
	}
	
	/**
	 * {@link TooltipData}
	 * 
	 * @author Enzo CACERES
	 */
	public enum Type {
		/**
		 * The {@link TooltipData} should be a title.
		 */
		TITLE,
		
		/**
		 * The {@link TooltipData} should be a description.
		 */
		DESCRIPTION
	}
	
}