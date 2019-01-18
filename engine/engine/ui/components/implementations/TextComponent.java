package engine.ui.components.implementations;

import com.sun.javafx.tk.Toolkit;

import engine.ui.components.UIComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class TextComponent extends UIComponent {
	
	/* Variables */
	private String text;
	private Font font;
	private Paint color;
	
	/* Constructor */
	public TextComponent() {
		this(null);
	}
	
	public TextComponent(String text) {
		this(text, 0, 0);
	}
	
	public TextComponent(String text, double x, double y) {
		this(text, x, y, INVALID, INVALID);
	}
	
	public TextComponent(String text, double x, double y, double width, double height) {
		super(x, y, width, height);
		
		this.text = text;
		this.font = DEFAULT_FONT;
		this.color = DEFAULT_COLOR;
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
		
		if (font == null) {
			this.font = DEFAULT_FONT;
		}
		
		invalidate();
	}
	
	public Paint getColor() {
		return color;
	}
	
	public void setColor(Paint color) {
		this.color = color;
		
		if (color == null) {
			this.color = DEFAULT_COLOR;
		}
		
		invalidate();
	}
	
	@Override
	public void computeSize() {
		double textWidth = Toolkit.getToolkit().getFontLoader().getFontMetrics(getFont()).computeStringWidth(text);
		double textHeight = getFont().getSize();
		
		if (width != INVALID && textWidth < width) {
			textWidth = width;
		}
		
		if (height != INVALID && textHeight < height) {
			textHeight = height;
		}
		
		setSize(textWidth, textHeight);
	}
	
	@Override
	public void render(GraphicsContext graphics) {
		graphics.save();
		graphics.setFill(getColor());
		graphics.setFont(getFont());
		
		graphics.scale(1 / scale, -1 / scale);
		
		double textX = x * scale;
		double textY = y * scale;
		
		graphics.fillText(String.valueOf(text), textX, -textY);
		graphics.restore();
	}
	
}