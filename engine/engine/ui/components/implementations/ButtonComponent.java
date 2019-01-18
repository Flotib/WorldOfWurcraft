package engine.ui.components.implementations;

import com.sun.javafx.tk.Toolkit;

import engine.ui.components.UIComponent;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ButtonComponent extends TextComponent {
	
	/* Constants */
	public static final double BUTTON_MARGIN = 2;
	public static final Paint BUTTON_DEFAULT_COLOR = Color.GRAY;
	
	/* Variables */
	private Image image;
	private Paint buttonColor;
	
	/* Constructor */
	public ButtonComponent(Image image) {
		this(image, 0, 0);
	}
	
	/* Constructor */
	public ButtonComponent(String text) {
		this(text, 0, 0);
	}
	
	/* Constructor */
	public ButtonComponent(String text, double x, double y) {
		this(text, x, y, INVALID, INVALID);
	}
	
	/* Constructor */
	public ButtonComponent(Image image, double x, double y) {
		this(image, x, y, INVALID, INVALID);
	}
	
	/* Constructor */
	public ButtonComponent(String text, double x, double y, double width, double height) {
		super(text, x, y);
		
		setSize(width, height);
		
		this.buttonColor = BUTTON_DEFAULT_COLOR;
	}
	
	/* Constructor */
	public ButtonComponent(Image image, double x, double y, double width, double height) {
		super(null, x, y);
		
		setSize(width, height);
		
		this.image = image;
		this.buttonColor = BUTTON_DEFAULT_COLOR;
	}
	
	@Override
	public void computeSize() {
		if (image == null) {
			double textWidth = Toolkit.getToolkit().getFontLoader().getFontMetrics(getFont()).computeStringWidth(getText()) + (BUTTON_MARGIN * 2);
			double textHeight = getFont().getSize() + (BUTTON_MARGIN * 2);
			
			if (width != INVALID && textWidth < width) {
				textWidth = width;
			}
			
			if (height != INVALID && textHeight < height) {
				textHeight = height;
			}
			
			setSize(textWidth, textHeight);
		} else {
			if (width == INVALID || height == INVALID) {
				setSize(image.getWidth(), image.getHeight());
				setSize(width + (BUTTON_MARGIN * 2), height + (BUTTON_MARGIN * 2));
			}
		}
		
	}
	
	@Override
	public void render(GraphicsContext graphics) {
		graphics.save();
		graphics.setFill(getButtonColor());
		
		double buttonX = x * scale;
		double buttonY = y * scale;
		double buttonWidth = width * scale;
		double buttonHeight = height * scale;
		
		graphics.fillRoundRect(buttonX, -buttonY, buttonWidth, buttonHeight, 1, 4);
		
		graphics.restore();
		
		if (image == null) {
			graphics.save();
			graphics.setFill(getColor());
			graphics.setFont(getFont());
			
			graphics.scale(1 / scale, -1 / scale);
			
			double textX = (x + BUTTON_MARGIN) * scale;
			double textY = (y + BUTTON_MARGIN) * scale;
			
			if (selected) {
				textX -= 1;
				textY += 1;
			}
			
			graphics.fillText(String.valueOf(getText()), textX, -textY);
			graphics.restore();
		} else {
			graphics.save();
			graphics.scale(1 / scale, -1 / scale);
			
			double imageX = x * scale;
			double imageY = y * scale;
			double imageWidth = width * scale;
			double imageHeight = height * scale;
			
			if (selected) {
				imageX -= 1;
				imageY += 1;
			}
			
			graphics.drawImage(image, imageX, -imageY - imageHeight, imageWidth, imageHeight);
			graphics.restore();
		}
	}
	
	@Override
	public void dispatchMouseClick(MouseButton button, Point2D mouseScreenPosition, boolean pressed) {
		super.dispatchMouseClick(button, mouseScreenPosition, pressed);
		
		if (isClickable()) {
			internalOnMouseClick(button, mouseScreenPosition, pressed);
		}
	}
	
	/**
	 * Internaly do the same thing as {@link UIComponent#dispatchMouseClick(MouseButton, Point2D, boolean)}.
	 * 
	 * @param button
	 *            Target mouse button.
	 * @param mouseScreenPosition
	 *            Mouse position when event was fired.
	 * @param pressed
	 *            If the mouse click is pressed or released.
	 */
	protected void internalOnMouseClick(MouseButton button, Point2D mouseScreenPosition, boolean pressed) {
		;
	}
	
	@Override
	public void setText(String text) {
		if (image != null) {
			throw new IllegalStateException("This button has an image attached to it.");
		}
		
		super.setText(text);
	}
	
	@Override
	public String getText() {
		if (image != null) {
			throw new IllegalStateException("This button has an image attached to it.");
		}
		
		return super.getText();
	}
	
	public Paint getButtonColor() {
		return buttonColor;
	}
	
	public void setButtonColor(Paint color) {
		this.buttonColor = color;
		
		if (color == null) {
			this.buttonColor = BUTTON_DEFAULT_COLOR;
		}
		
		invalidate();
	}
	
}