package game.tooltip;

import engine.ui.components.UIComponent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class TooltipData {
	
	private Type type;
	private String text;
	private Paint color;
	private Font font;
	
	protected TooltipData(Type type) {
		this.type = type;
		
		this.color = UIComponent.DEFAULT_COLOR;
		this.font = UIComponent.DEFAULT_FONT;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Paint getColor() {
		return color;
	}
	
	public void setColor(Paint color) {
		this.color = color;
	}
	
	public Font getFont() {
		return font;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	
	public enum Type {
		TITLE, DESCRIPTION
	}
	
}