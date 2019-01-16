package engine.ui.components.implementations;

import engine.ui.components.UIComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ImageComponent extends UIComponent {
	
	/* Variables */
	private Image image;
	
	/* Constructor */
	public ImageComponent(Image image) {
		super();
		
		this.image = image;
	}
	
	/* Constructor */
	public ImageComponent(Image image, double x, double y) {
		super(x, y);
		
		this.image = image;
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
		graphics.save();
		graphics.scale(1 / scale, -1 / scale);
		
		double imageX = x * scale;
		double imageY = y * scale;
		
		graphics.drawImage(image, imageX, -imageY - height, width, height);
		graphics.restore();
	}
	
}