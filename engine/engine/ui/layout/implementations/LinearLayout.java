package engine.ui.layout.implementations;

import engine.ui.components.UIComponent;
import engine.ui.layout.UILayout;
import javafx.scene.canvas.GraphicsContext;

public class LinearLayout extends UILayout {
	
	/* Constants */
	public static final int VERTICAL = 0;
	public static final int HORIZONTAL = 1;
	
	/* Variables */
	public int orientation;
	
	/* Constructor */
	/**
	 * Default orientation: {@link #VERTICAL}.
	 */
	public LinearLayout() {
		this(VERTICAL);
	}
	
	/* Constructor */
	public LinearLayout(int orientation) {
		super();
		
		this.orientation = orientation;
	}
	
	/* Constructor */
	public LinearLayout(int x, int y, int orientation) {
		super(x, y);
		
		this.orientation = orientation;
	}
	
	@Override
	public void computeSize() {
		double newWidth = 0, newHeight = 0;
		
		switch (orientation) {
			case VERTICAL: {
				for (UIComponent component : getChildren()) {
					component.validate();
					
					newHeight += component.getHeight();
					if (component.getWidth() > newWidth) {
						newWidth = component.getWidth();
					}
				}
				break;
			}
			case HORIZONTAL: {
				for (UIComponent component : getChildren()) {
					component.validate();
					
					newWidth += component.getWidth();
					if (component.getHeight() > newHeight) {
						newHeight = component.getHeight();
					}
				}
				break;
			}
			
			default: {
				throw new IllegalStateException("Invalid orientation.");
			}
		}
		
		setSize(newWidth, newHeight);
	}
	
	@Override
	public double getAbsoluteXOf(UIComponent targetComponent) {
		if (targetComponent.getParent() != this) {
			throw new IllegalStateException("The parent component is not the same.");
		}
		
		double absoluteX = getAbsoluteX();
		
		for (UIComponent component : getChildren()) {
			component.validate();
			
			if (component == targetComponent) {
				break;
			}
			
			if (orientation == HORIZONTAL) {
				absoluteX += component.getWidth();
			}
		}
		
		return absoluteX + targetComponent.getX();
	}
	
	@Override
	public double getAbsoluteYOf(UIComponent targetComponent) {
		if (targetComponent.getParent() != this) {
			throw new IllegalStateException("The parent component is not the same.");
		}
		
		double absoluteY = getAbsoluteY();
		
		for (UIComponent component : getChildren()) {
			component.validate();
			
			if (component == targetComponent) {
				break;
			}
			
			if (orientation == VERTICAL) {
				absoluteY += component.getHeight();
			}
		}
		
		return absoluteY + targetComponent.getY();
	}
	
	@Override
	public void render(GraphicsContext graphics) {
		graphics.save();
		graphics.translate(x, -y);
		
		switch (orientation) {
			case VERTICAL: {
				for (UIComponent component : getChildren()) {
					graphics.translate(0, -component.getHeight() * scale);
					component.render(graphics);
				}
				
				break;
			}
			
			case HORIZONTAL: {
				for (UIComponent component : getChildren()) {
					component.render(graphics);
					graphics.translate(component.getWidth() * scale, 0);
				}
				
				break;
			}
			
			default: {
				throw new IllegalStateException("Invalid orientation.");
			}
		}
		
		graphics.restore();
	}
	
}