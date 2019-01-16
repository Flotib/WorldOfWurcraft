package editor;

import engine.GameEngine;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class Editor extends GameEngine {
	
	/* Mouse */
	private double lastX = -1, lastY = -1;
	
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
		
		graphics.setFill(Color.rgb(0, 0, 0));
		canvas.getGraphicsContext2D().fillRect(world.toWorldX(mousePosition.getX()), world.toWorldY(mousePosition.getY()), 1, 1);
		
		graphics.restore();
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