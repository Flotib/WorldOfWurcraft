package engine.ui;

import java.util.ArrayList;
import java.util.List;

import engine.ui.components.UIComponent;

public class UILayer implements Comparable<UILayer> {
	
	/* Variables */
	private int index;
	private List<UIComponent> components;
	
	/* Constructor */
	protected UILayer(int index) {
		this.index = index;
		this.components = new ArrayList<>();
	}
	
	public int getIndex() {
		return index;
	}
	
	public void clear() {
		components.clear();
	}
	
	public void add(UIComponent element) {
		components.add(element);
	}
	
	public UIComponent remove(int index) {
		return components.remove(index);
	}
	
	public boolean remove(Object object) {
		return components.remove(object);
	}
	
	public List<UIComponent> getComponents() {
		return components;
	}
	
	@Override
	public int compareTo(UILayer other) {
		return this.index - other.index;
	}
	
}