package engine.ui.layout;

import java.util.ArrayList;
import java.util.List;

import engine.ui.components.UIComponent;

public abstract class UILayout extends UIComponent {
	
	private List<UIComponent> children;
	
	public UILayout() {
		super();
	}
	
	public UILayout(int x, int y) {
		super(x, y, INVALID, INVALID);
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
	
	public List<UIComponent> getChildren() {
		return checkAndCreate();
	}
	
}