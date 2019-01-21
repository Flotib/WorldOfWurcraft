package engine.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import engine.ui.components.UIComponent;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
		
		resortLayers();
		
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
				component.doRender(graphics);
			}
		}
	}
	
	public void dispatchMouseMoved(Point2D mouseScreenPosition) {
		for (UILayer layer : layers) {
			for (UIComponent component : layer.getComponents()) {
				component.onMouseMouved(mouseScreenPosition);
			}
		}
	}
	
	public void dispatchMouseClick(MouseEvent mouseEvent, boolean pressed) {
		dispatchMouseClick(mouseEvent.getButton(), new Point2D(mouseEvent.getX(), mouseEvent.getY()), pressed);
	}
	
	public void dispatchMouseClick(MouseButton button, Point2D mouseScreenPosition, boolean pressed) {
		for (UILayer layer : layers) {
			for (UIComponent component : layer.getComponents()) {
				UIComponent highestComponent = component.getHeighestComponent();
				
				if (highestComponent != null) {
					highestComponent.dispatchMouseClick(button, mouseScreenPosition, pressed);
					return;
				}
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