package engine.ui.layout;

import java.util.ArrayList;
import java.util.List;

import caceresenzo.libs.logger.Logger;
import engine.ui.components.UIComponent;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class UILayout extends UIComponent {
	
	/* Variables */
	private List<UIComponent> children;
	
	/* Constructor */
	public UILayout() {
		super();
	}
	
	/* Constructor */
	public UILayout(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void onMouseMouved(Point2D mouseScreenPosition) {
		super.onMouseMouved(mouseScreenPosition);
		
		if (!enabled || getVisibility() == VISIBILITY_GONE) {
			return;
		}
		
		if (children != null) {
			for (UIComponent component : getChildren()) {
				component.onMouseMouved(mouseScreenPosition);
			}
		}
	}
	
	@Override
	public UIComponent getHeighestComponent() {
		if (selected) {
			if (children != null) {
				for (UIComponent component : getChildren()) {
					UIComponent highestComponent = component.getHeighestComponent();
					
					if (highestComponent != null && highestComponent.isEnabled()) {
						return highestComponent;
					}
				}
			}
			
			return this;
		}
		
		return null;
	}
	
	@Override
	public void renderDebug(GraphicsContext graphics) {
		super.renderDebug(graphics);
		
		if (children != null) {
			for (UIComponent component : getChildren()) {
				component.renderDebug(graphics);
			}
		}
	}
	
	/**
	 * Compute an absolute x screen position for a given {@link UIComponent}.
	 * 
	 * @param targetComponent
	 *            Target {@link UIComponent}.
	 * @return Absolute x screen position.
	 */
	public abstract double getAbsoluteXOf(UIComponent targetComponent);
	
	/**
	 * Compute an absolute y screen position for a given {@link UIComponent}.
	 * 
	 * @param targetComponent
	 *            Target {@link UIComponent}.
	 * @return Absolute y screen position.
	 */
	public abstract double getAbsoluteYOf(UIComponent targetComponent);
	
	/**
	 * Optimization.<br>
	 * Create only the list if require.
	 * 
	 * @return
	 */
	private List<UIComponent> checkAndCreate() {
		if (children == null) {
			children = new ArrayList<>();
		}
		
		return children;
	}
	
	protected boolean hasChildren() {
		return children != null;
	}
	
	public boolean hasComponents() {
		return getChildren().isEmpty();
	}
	
	public boolean addComponent(UIComponent element) {
		element.attachParent(this);
		Logger.info("Attached parent");
		
		return getChildren().add(element);
	}
	
	public void addComponent(int index, UIComponent element) {
		element.attachParent(this);
		
		children.add(index, element);
	}
	
	public boolean hasComponent(Object object) {
		return getChildren().contains(object);
	}
	
	public UIComponent removeComponent(int index) {
		getChildren().get(index).detachParent(this);
		
		return getChildren().remove(index);
	}
	
	public boolean removeComponent(UIComponent element) {
		element.detachParent(this);
		
		return getChildren().remove(element);
	}
	
	public void clearComponents() {
		children.clear();
	}
	
	public UIComponent getComponent(int index) {
		return children.get(index);
	}
	
	public UIComponent setComponent(int index, UIComponent element) {
		return children.set(index, element);
	}
	
	/**
	 * @return Children list present in this {@link UILayout}.
	 */
	public List<UIComponent> getChildren() {
		return checkAndCreate();
	}
	
}