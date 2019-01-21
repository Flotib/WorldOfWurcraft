package engine;

import engine.ui.UILayer;
import engine.ui.UIManager;
import engine.ui.components.UIComponent;
import engine.ui.components.implementations.TextComponent;
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
	
	/* Constants */
	public static final int DEBUG_LAYER_INDEX = 1000;
	
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
	protected UILayer debugLayer;
	
	/* Mouse */
	protected Point2D mousePosition;
	
	/* Debug */
	private boolean debugging;
	private TextComponent debuggingMousePositionTextComponent;
	private Point2D debuggingMouseSavedPosition;
	
	/* Variables */
	protected World world;
	
	/* Constructor */
	public GameEngine() {
		super();
		
		ENGINE = this;
		
		this.uiManager = UIManager.getManager();
		
		this.debugLayer = uiManager.createLayer(DEBUG_LAYER_INDEX);
		
		this.mousePosition = new Point2D(0, 0);
		
		this.world = createWorld();
		
		initialize();
	}
	
	/**
	 * Do code when your game initialize.
	 */
	protected void initialize() {
		debugLayer.add(debuggingMousePositionTextComponent = new TextComponent());
		debuggingMouseSavedPosition = new Point2D(0, 0);
	}
	
	@Override
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
	
	/**
	 * Called when the window's title need to be updated.
	 */
	protected void updateStageTitle() {
		stage.setTitle(String.valueOf(fps));
	}
	
	/**
	 * Main loop function.
	 * 
	 * @param delta
	 *            Delta time.
	 * @param now
	 *            Time now.
	 */
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
	
	/**
	 * Called when the game has ticked.
	 * 
	 * @param delta
	 *            Delta time.
	 */
	public void tick(double delta) {
		;
	}
	
	/**
	 * Called when the game need to render.
	 * 
	 * @param delta
	 *            Delta time.
	 */
	public void render(double delta) {
		;
	}
	
	/**
	 * {@link UIComponent} rendering pipeline.
	 */
	public void renderComponents() {
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		
		graphics.save();
		graphics.scale(UIComponent.DEFAULT_CANVAS_RESCALE, UIComponent.DEFAULT_CANVAS_RESCALE);
		
		if (debugging) {
			graphics.setFill(Color.RED);
			graphics.fillRect(debuggingMouseSavedPosition.getX() - 2, debuggingMouseSavedPosition.getY() - 2, 4, 4);
		}
		
		graphics.setFill(Color.BLACK);
		
		graphics.save();
		uiManager.render(canvas);
		graphics.restore();
		
		graphics.setStroke(Color.RED);
		graphics.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		graphics.restore();
	}
	
	/**
	 * Create the actual world.
	 * 
	 * @return World instance.
	 */
	public World createWorld() {
		return new World();
	}
	
	public void onScrollEvent(ScrollEvent scrollEvent) {
		;
	}
	
	public void onMousePressedEvent(MouseEvent mouseEvent) {
		uiManager.dispatchMouseClick(mouseEvent, true);
	}
	
	public void onMouseReleasedEvent(MouseEvent mouseEvent) {
		uiManager.dispatchMouseClick(mouseEvent, false);
	}
	
	public void onMouseDraggedEvent(MouseEvent mouseEvent) {
		mousePosition = computeScreenCursorPosition(mouseEvent.getX(), mouseEvent.getY());
		
		uiManager.dispatchMouseMoved(mousePosition);
		
		if (debugging) {
			debuggingMousePositionTextComponent.setColor(Color.PURPLE);
			debuggingMousePositionTextComponent.setText(String.format("-> x:%s,y:%s", mousePosition.getX() - debuggingMouseSavedPosition.getX(), mousePosition.getY() - debuggingMouseSavedPosition.getY()));
			debuggingMousePositionTextComponent.setPosition(new Point2D(mousePosition.getX(), mousePosition.getY()));
		}
	}
	
	public void onMouseMovedEvent(MouseEvent mouseEvent) {
		mousePosition = computeScreenCursorPosition(mouseEvent.getX(), mouseEvent.getY());
		
		uiManager.dispatchMouseMoved(mousePosition);
		
		if (debugging) {
			debuggingMousePositionTextComponent.setColor(Color.BLACK);
			debuggingMousePositionTextComponent.setText(String.format("x:%s,y:%s", mouseEvent.getX(), mouseEvent.getY()));
			debuggingMousePositionTextComponent.setPosition(debuggingMouseSavedPosition = mousePosition);
		}
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
	
	public Point2D computeScreenCursorPosition(double x, double y) {
		double sceneCX = x;
		double sceneCY = y;
		
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
		canvas.setFocusTraversable(true);
		
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
		
		// canvas.scaleXProperty().bind(scene.widthProperty().divide(1920));
		// canvas.scaleYProperty().bind(scene.heightProperty().divide(1080));
		
		return scene;
	}
	
	/**
	 * Enable or disable debugging of the {@link GameEngine}.
	 * 
	 * @param enable
	 *            New enabled state, <code>false</code> by default.
	 */
	public void debugging(boolean enable) {
		this.debugging = enable;
	}
	
	public boolean isDebugging() {
		return debugging;
	}
	
	public static GameEngine getGameEngine() {
		if (ENGINE == null) {
			throw new IllegalStateException("You must create the engine first.");
		}
		
		return ENGINE;
	}
	
}