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
	public void render(GraphicsContext graphics) {
		graphics.save();
		
		switch (orientation) {
			case VERTICAL: {
				for (UIComponent component : getChildren()) {
					graphics.translate(0, -component.getHeight());
					component.render(graphics);
				}
				break;
			}
			case HORIZONTAL: {
				for (UIComponent component : getChildren()) {
					graphics.translate(component.getWidth(), 0);
					component.render(graphics);
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