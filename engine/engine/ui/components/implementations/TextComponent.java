package engine.ui.components.implementations;

import com.sun.javafx.tk.Toolkit;

import engine.ui.components.UIComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextComponent extends UIComponent {
	
	public static final Font HUD_FONT = Font.font("Arial", 12); //
	
	private String text;
	
	public TextComponent() {
		super();
		
		createLabel(null);
	}
	
	public TextComponent(String text) {
		super();
		
		createLabel(text);
	}
	
	private void createLabel(String text) {
		this.text = text;
	}
	
	@Override
	public void computeSize() {
		double textWidth = Toolkit.getToolkit().getFontLoader().getFontMetrics(HUD_FONT).computeStringWidth(text);
		
		setSize(textWidth, 12);
	}
	
	@Override
	public void render(GraphicsContext graphics) {
		graphics.setFill(Color.BLACK);
		graphics.setFont(HUD_FONT);
		
		graphics.save();
		graphics.scale(1 / scale, -1 / scale);
		
		double textX = x * scale;
		double textY = y * scale;
		
		graphics.fillText(String.valueOf(text), textX, -textY);
		graphics.restore();
	}
	
}