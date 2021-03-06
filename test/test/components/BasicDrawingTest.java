package test.components;

import caceresenzo.libs.logger.Logger;
import caceresenzo.libs.thread.ThreadUtils;
import engine.GameEngine;
import engine.texture.Texture;
import engine.texture.TextureManager;
import engine.ui.UILayer;
import engine.ui.components.UIComponent;
import engine.ui.components.implementations.ButtonComponent;
import engine.ui.components.implementations.ImageComponent;
import engine.ui.components.implementations.TextComponent;
import engine.ui.layout.implementations.LinearLayout;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BasicDrawingTest extends GameEngine {
	private double lastX = -1, lastY = -1;

	
	public static final Texture testImage = TextureManager.load("test/test-image.jpg");
	
	private LinearLayout mainLinearLayout;
	
	@Override
	protected void initialize() {
		super.initialize();
		debugging(true);
		
		UILayer mainLayer = uiManager.createLayer(0);
		
		LinearLayout linearLayout = mainLinearLayout = new LinearLayout(10, 10, LinearLayout.VERTICAL);
		final TextComponent helloTextComponent = new TextComponent("count");
		linearLayout.addComponent(helloTextComponent);
		final ImageComponent testImageComponent = new ImageComponent(testImage.getImage());
		linearLayout.addComponent(testImageComponent);
		linearLayout.addComponent(new TextComponent("Hello", 0, 0, 500, 50));
		linearLayout.addComponent(new TextComponent("How Are you today ?"));
		linearLayout.addComponent(new TextComponent("World"));
		linearLayout.addComponent(new ButtonComponent("button with text"));
		linearLayout.addComponent(new ButtonComponent(testImage.getImage()));
		
		helloTextComponent.setFont(Font.font("Courier New", 48));
		helloTextComponent.setColor(Color.AQUAMARINE);
		
		linearLayout.setRenderMode(UIComponent.RENDER_DEBUG);
		// helloTextComponent.setRenderMode(UIComponent.RENDER_DEBUG);
		// testImageComponent.setRenderMode(UIComponent.RENDER_DEBUG);
		
		// LinearLayout horizontalLinearLayout = new LinearLayout(LinearLayout.HORIZONTAL);
		// horizontalLinearLayout.setRenderMode(UIComponent.RENDER_DEBUG);
		// linearLayout.addComponent(horizontalLinearLayout);
		// for (int i = 0; i < 15; i++) {
		// if (i % 2 == 0) {
		// ImageComponent component = new ImageComponent(testImage.getImage());
		// component.setRenderMode(UIComponent.RENDER_DEBUG);
		//
		// horizontalLinearLayout.addComponent(component);
		// } else {
		// TextComponent component = new TextComponent("##" + i);
		// component.setRenderMode(UIComponent.RENDER_DEBUG);
		//
		// horizontalLinearLayout.addComponent(component);
		// }
		// }
		//
		// for (int i = 0; i < 15; i++) {
		// linearLayout.addComponent(new TextComponent("#" + i));
		// }
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5000000; i++) {
					ThreadUtils.sleep(100);
					helloTextComponent.setText("r:" + i);
				}
			}
		}).start();
		
		mainLayer.add(linearLayout);
	}
	
	@Override
	public void tick(double delta) {
		// Logger.info("Mouse: " + mousePosition);
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
	
	@Override
	public void onKeyPressedEvent(KeyEvent keyEvent) {
		super.onKeyPressedEvent(keyEvent);
		
		switch (keyEvent.getCode()) {
			case D: {
				mainLinearLayout.setRenderMode(UIComponent.RENDER_DEBUG);
				break;
			}
			
			case F: {
				mainLinearLayout.setRenderMode(UIComponent.RENDER_NORMAL);
				break;
			}
			
			default: {
				break;
			}
		}
		
		Logger.info("Pressed: " + keyEvent.getCode());
	}
	
	@Override
	public void onScrollEvent(ScrollEvent scrollEvent) {
		if (scrollEvent.isControlDown()) {
			double scale = world.getViewport().getScale();
			
			scale += scrollEvent.getDeltaY() / 10;
			
			scale = Math.min(scale, 200);
			scale = Math.max(10, scale);
			
			world.getViewport().setScale(scale);
		}
	}
	
	@Override
	public void onMousePressedEvent(MouseEvent mouseEvent) {
		super.onMousePressedEvent(mouseEvent);
		
		if (mouseEvent.getButton() == MouseButton.MIDDLE) {
			lastX = mouseEvent.getSceneX();
			lastY = mouseEvent.getSceneY();
		}
	}
	
	@Override
	public void onMouseDraggedEvent(MouseEvent mouseEvent) {
		super.onMouseDraggedEvent(mouseEvent);
		
		switch (mouseEvent.getButton()) {
			case MIDDLE: {
				world.getViewport().translate((mouseEvent.getSceneX() - lastX) / world.getViewport().getScale(), -(mouseEvent.getSceneY() - lastY) / world.getViewport().getScale());
				
				lastX = mouseEvent.getSceneX();
				lastY = mouseEvent.getSceneY();
				break;
			}
			
			default: {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}