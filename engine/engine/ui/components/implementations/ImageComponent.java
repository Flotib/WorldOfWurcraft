package engine.ui.components.implementations;

import caceresenzo.libs.logger.Logger;
import engine.ui.components.UIComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ImageComponent extends UIComponent {
	
	/* Variables */
	protected Image image;
	
	/* Constructor */
	public ImageComponent(Image image) {
		this(image, 0, 0);
	}
	
	/* Constructor */
	public ImageComponent(Image image, double x, double y) {
		this(image, 0, 0, INVALID, INVALID);
	}
	
	/* Constructor */
	public ImageComponent(Image image, double x, double y, double width, double height) {
		super(x, y, width, height);
		
		this.image = image;
	}
	
	@Override
	public void computeSize() {
		if (width == INVALID || height == INVALID) {
			setSize(image.getWidth(), image.getHeight());
		}
	}
	
	@Override
	public void render(GraphicsContext graphics) {
		if (image == null) {
			return;
		}
		
		graphics.save();
		
		double imageX = x * scale;
		double imageY = y * scale;
		double imageWidth = width * scale;
		double imageHeight = height * scale;
		
		Logger.info(getAbsoluteY());
		
		graphics.drawImage(image, imageX, imageY, imageWidth, imageHeight);
		graphics.restore();
	}
	
}