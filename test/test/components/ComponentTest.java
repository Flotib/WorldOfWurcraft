package test.components;

import java.util.Date;
import java.util.Random;

import engine.GameEngine;
import engine.ui.UILayer;
import engine.ui.components.UIComponent;
import engine.ui.components.implementations.TextComponent;
import engine.ui.layout.implementations.LinearLayout;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class ComponentTest extends GameEngine {
	
	@Override
	protected void initialize() {
		super.initialize();
		
		UILayer mainLayer = uiManager.createLayer(0);
		
		LinearLayout linearLayout = new LinearLayout(80, 80, LinearLayout.VERTICAL);
		final TextComponent helloText = new TextComponent("Hello World");
		linearLayout.addComponent(helloText);
		final TextComponent helloText2 = new TextComponent("Hello World 2");
		linearLayout.addComponent(helloText2);
		linearLayout.setRenderMode(UIComponent.RENDER_DEBUG);
		
		helloText.setOnClickListener(new UIComponent.OnClickListener() {
			@Override
			public void onClick(UIComponent component, MouseButton button, Point2D mouseScreenPosition, boolean pressed) {
				if (!pressed) {
					helloText.setText("date : " + new Date().toString());
				}
			}
		});
		
		helloText2.setOnClickListener(new UIComponent.OnClickListener() {
			@Override
			public void onClick(UIComponent component, MouseButton button, Point2D mouseScreenPosition, boolean pressed) {
				if (!pressed) {
					helloText2.setText("random: " + new Random().nextLong());
				}
			}
		});
		
		mainLayer.add(linearLayout);
	}
	
	@Override
	public void render(double delta) {
		double scale = world.getViewport().getScale();
		double translateX = world.getViewport().getTranslateX();
		double translateY = world.getViewport().getTranslateY();
		
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		
		graphics.setFill(Color.rgb(255, 255, 255));
		graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		graphics.save();
		graphics.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
		graphics.scale(1, -1);
		
		graphics.scale(scale, scale);
		graphics.translate(translateX, translateY);
		
		renderGrid();
		
		graphics.restore();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}