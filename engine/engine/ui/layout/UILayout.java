package engine.ui.layout;

import java.util.ArrayList;
import java.util.Collection;
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
		return getChildren().add(element);
	}
	
	public void addComponent(int index, UIComponent element) {
		children.add(index, element);
	}
	
	public boolean addAllComponents(Collection<? extends UIComponent> collection) {
		return getChildren().addAll(collection);
	}
	
	public boolean addAllComponents(int index, Collection<? extends UIComponent> collection) {
		return getChildren().addAll(index, collection);
	}
	
	public boolean hasComponent(Object object) {
		return getChildren().contains(object);
	}
	
	public UIComponent removeComponent(int index) {
		return children.remove(index);
	}
	
	public boolean removeComponent(Object object) {
		return getChildren().remove(object);
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