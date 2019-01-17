package engine.ui.components.implementations;

import com.sun.javafx.tk.Toolkit;

import engine.ui.components.UIComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextComponent extends UIComponent {
	
	/* Variables */
	private String text;
	private Font font;
	
	/* Constructor */
	public TextComponent() {
		this(null);
	}
	
	public TextComponent(String text) {
		this(text, 0, 0);
	}
	
	public TextComponent(String text, int x, int y) {
		super();
		
		this.text = text;
		this.font = DEFAULT_FONT;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
		
		invalidate();
	}
	
	public Font getFont() {
		return font;
	}
	
	public void setFont(Font font) {
		this.font = font;
		
		invalidate();
	}
	
	@Override
	public void computeSize() {
		double textWidth = Toolkit.getToolkit().getFontLoader().getFontMetrics(DEFAULT_FONT).computeStringWidth(text);
		
		setSize(textWidth, 12);
	}
	
	@Override
	public void render(GraphicsContext graphics) {
		graphics.setFill(Color.BLACK);
		graphics.setFont(DEFAULT_FONT);
		
		graphics.save();
		graphics.scale(1 / scale, -1 / scale);
		
		double textX = x * scale;
		double textY = y * scale;
		
		graphics.fillText(String.valueOf(text), textX, -textY);
		graphics.restore();
	}
	
}