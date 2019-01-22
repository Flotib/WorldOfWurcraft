package engine.ui.layout;

import engine.ui.components.UIComponent;
import javafx.scene.canvas.GraphicsContext;

public abstract class CustomLayout extends UILayout {
	
	/* Constructor */
	public CustomLayout() {
		super();
	}

	/* Constructor */
	public CustomLayout(double x, double y) {
		super(x, y);
	}
	
	@Override
	public double getAbsoluteXOf(UIComponent targetComponent) {
		return getAbsoluteX() + targetComponent.getX();
	}
	
	@Override
	public double getAbsoluteYOf(UIComponent targetComponent) {
		return getAbsoluteY() + targetComponent.getY();
	}
	
	@Override
	public void render(GraphicsContext graphics) {
		graphics.save();
		graphics.translate(x, y);

		for (UIComponent component : getChildren()) {
			component.render(graphics);
		}
		
		graphics.restore();
	}
	
	@Override
	public void computeSize() {
		double maxWidth = 0, maxHeight = 0;
		
		if (hasChildren()) {
			for (UIComponent component : getChildren()) {
				double componentMaxPositionX = component.getX() + component.getWidth();
				double componentMaxPositionY = component.getY() + component.getHeight();
				
				if (componentMaxPositionX > maxWidth) {
					maxWidth = componentMaxPositionX;
				}
				
				if (componentMaxPositionY > maxHeight) {
					maxHeight = componentMaxPositionY;
				}
			}
		}
		
		setSize(maxWidth, maxHeight);
	}
	
}