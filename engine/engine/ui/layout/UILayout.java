package engine.ui.layout;

import java.util.ArrayList;
import java.util.List;

import engine.ui.components.UIComponent;
import javafx.geometry.Point2D;

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

		if (getVisibility() == VISIBILITY_GONE) {
			return;
		}
		
		if (children != null) {
			for (UIComponent component : getChildren()) {
				component.onMouseMouved(mouseScreenPosition);
			}
		}
	}
	
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
	
	public boolean hasComponents() {
		return getChildren().isEmpty();
	}
	
	public boolean addComponent(UIComponent element) {
		element.attachParent(this);
		
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