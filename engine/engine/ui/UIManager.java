package engine.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import engine.ui.components.UIComponent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class UIManager {
	
	/* Instance */
	private static UIManager MANAGER;
	
	/* Variables */
	private List<UILayer> layers;
	
	/* Constructor */
	private UIManager() {
		layers = new ArrayList<>();
	}
	
	/**
	 * If you change index of some of the layer, re-sort them to apply new rendering order.
	 */
	public void resortLayers() {
		Collections.sort(layers);
	}
	
	public UILayer createLayer(int index) {
		UILayer layer = new UILayer(index);
		layers.add(layer);
		
		return layer;
	}
	
	/**
	 * @return {@link List} of {@link UILayer} present on this {@link UILayer}.
	 */
	public List<UILayer> getLayers() {
		return layers;
	}
	
	public void render(Canvas canvas) {
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		
		for (UILayer layer : layers) {
			for (UIComponent component : layer.getComponents()) {
				if (!component.isValid()) {
					component.validate();
				}
				
				component.render(graphics);
			}
		}
	}
	
	/**
	 * @return {@link UIManager}'s singleton.
	 */
	public static UIManager getManager() {
		if (MANAGER == null) {
			MANAGER = new UIManager();
		}
		
		return MANAGER;
	}
	
}