package engine;

import engine.ui.UIManager;
import engine.ui.components.UIComponent;
import engine.world.World;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameEngine extends Application {
	
	/* Instance */
	private static GameEngine ENGINE;
	
	/* Fps Counter */
	private int fps, frameNumber;
	private long lastFPSUpdate, lastFrameTime = -1;
	private AnimationTimer timer;
	
	/* Views */
	protected Stage stage = null;
	protected Canvas canvas;
	
	/* Managers */
	protected UIManager uiManager;
	
	/* Mouse */
	protected Point2D mousePosition = new Point2D(0, 0);
	
	/* Variables */
	protected World world;
	
	/* Constructor */
	public GameEngine() {
		super();
		
		ENGINE = this;
		
		this.uiManager = UIManager.getManager();
		this.world = createWorld();
		
		initialize();
	}
	
	protected void initialize() {
		;
	}
	
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		stage.setMaximized(true);
		stage.setTitle("Elektrode");
		stage.setMinWidth(580);
		stage.setMinHeight(460);
		stage.setScene(createScene(stage));
		stage.show();
		
		timer = new AnimationTimer() {
			public void handle(long now) {
				long nowms = now / 1000000l;
				
				if (lastFrameTime < 0) {
					lastFrameTime = nowms;
				}
				
				double delta = nowms - lastFrameTime;
				
				loop(delta, nowms);
				
				lastFrameTime = nowms;
			}
		};
		
		timer.start();
	}
	
	private void updateStageTitle() {
		stage.setTitle(String.valueOf(fps));
	}
	
	public void loop(double delta, long now) {
		frameNumber++;
		
		if (now - lastFPSUpdate > 1000L) {
			fps = frameNumber;
			updateStageTitle();
			
			frameNumber = 0;
			lastFPSUpdate = now;
		}
		
		tick(delta);
		render(delta);
		renderComponents();
	}
	
	public void tick(double delta) {
		;
	}
	
	public void render(double delta) {
		;
	}
	
	public void renderComponents() {
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		
		graphics.save();
		graphics.scale(UIComponent.DEFAULT_CANVAS_RESCALE, -UIComponent.DEFAULT_CANVAS_RESCALE);

		graphics.save();
		uiManager.render(canvas);
		graphics.restore();
			
		graphics.restore();
	}
	
	public World createWorld() {
		return new World();
	}
	
	public void onScrollEvent(ScrollEvent scrollEvent) {
		;
	}
	
	public void onMousePressedEvent(MouseEvent mouseEvent) {
		;
	}
	
	public void onMouseReleasedEvent(MouseEvent mouseEvent) {
		;
	}
	
	public void onMouseDraggedEvent(MouseEvent mouseEvent) {
		mousePosition = computeSceneCursorPosition(mouseEvent.getX(), mouseEvent.getY());
	}
	
	public void onMouseMovedEvent(MouseEvent mouseEvent) {
		mousePosition = computeSceneCursorPosition(mouseEvent.getX(), mouseEvent.getY());
	}
	
	public void onKeyPressedEvent(KeyEvent keyEvent) {
		;
	}

	private void onKeyReleasedEvent(KeyEvent keyEvent) {
		;
	}
	
	public Point2D computeSceneCursorPosition(double x, double y) {
		double sceneCX = x - canvas.getWidth() / 2;
		double sceneCY = -y + canvas.getHeight() / 2;
		
		return new Point2D(sceneCX, sceneCY);
	}
	
	protected void renderGrid() {
		double scale = world.getViewport().getScale();
		double translateX = world.getViewport().getTranslateX();
		double translateY = world.getViewport().getTranslateY();
		
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		
		graphics.save();
		graphics.setStroke(Color.rgb(0, 0, 0));
		graphics.setLineWidth(1 / scale);
		
		int startX = 0;
		int startY = 0;
		int endX = 0;
		int endY = 0;
		
		int caseCountX = (int) (canvas.getWidth() / scale) + 1;
		int caseCountY = (int) (canvas.getHeight() / scale) + 1;
		
		startX = -caseCountX / 2 - 1;
		startY = -caseCountY / 2 - 1;
		
		startX -= translateX + 1;
		startY -= translateY + 1;
		
		endX = startX + caseCountX + 1;
		endY = startY + caseCountY + 1;
		
		for (int x = startX; x < endX; x++) {
			graphics.strokeLine(x + 0.5, startY + 0.5, x + 0.5, endY + 0.5);
		}
		for (int y = startY; y < endY; y++) {
			graphics.strokeLine(startX + 0.5, y + 0.5, endX + 0.5, y + 0.5);
		}
		graphics.restore();
	}
	
	private Scene createScene(Stage stage) {
		Group group = new Group();
		group.getChildren().add(canvas = new Canvas());
		
		Scene scene = new Scene(group);
		
		canvas.setOnScroll(new EventHandler<ScrollEvent>() {
			public void handle(ScrollEvent event) {
				onScrollEvent(event);
			}
		});
		
		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				onMousePressedEvent(event);
			}
		});
		
		canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				onMouseReleasedEvent(event);
			}
		});
		
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				onMouseDraggedEvent(event);
			}
		});
		
		canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				onMouseMovedEvent(event);
			}
		});
		
		canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				onKeyPressedEvent(event);
			}
		});
		
		canvas.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				onKeyReleasedEvent(event);
			}
		});
		
		canvas.widthProperty().bind(scene.widthProperty());
		canvas.heightProperty().bind(scene.heightProperty());
		
		return scene;
	}
	
	public static GameEngine getGameEngine() {
		if (ENGINE == null) {
			throw new IllegalStateException("You must create the engine first.");
		}
		
		return ENGINE;
	}
	
}