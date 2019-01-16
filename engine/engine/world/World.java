package engine.world;

import engine.render.Viewport;

public class World {
	
	/* Constants */
	public static final double INPUT_SELECTION_RADIUS = 0.1;
	public static final double OUTPUT_SELECTION_RADIUS = 0.3;
	public static final double DEFAULT_WORLD_VIEWPORT_SCALE = 50;
	
	/* Variables */
	private final Viewport viewport;
	
	/* Constructor */
	public World() {
		this.viewport = new Viewport(DEFAULT_WORLD_VIEWPORT_SCALE);
	}
	
	public double toWorldX(double x) {
		return viewport.untransformX(x);
	}
	
	public double toWorldY(double y) {
		return viewport.untransformY(y);
	}
	
	public int getCaseX(double x) {
		return (int) Math.floor(toWorldX(x) + 0.5);
	}
	
	public int getCaseY(double y) {
		return (int) Math.ceil(toWorldY(y) - 0.5);
	}
	
	public Viewport getViewport() {
		return viewport;
	}
	
}