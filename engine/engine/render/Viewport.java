package engine.render;

public class Viewport {
	
	/* Variables */
	private double scale = 1, translateX, translateY;
	
	/* Constructor */
	public Viewport() {
		this(1);
	}
	
	/* Constructor */
	public Viewport(double scale) {
		setScale(scale);
	}
	
	/**
	 * Translate the {@link Viewport} to a specified value on the x axis.
	 * 
	 * @param value
	 *            x value
	 * @see #translate(double, double)
	 */
	public void translateX(double value) {
		this.translateX += value;
	}
	
	/**
	 * Translate the {@link Viewport} to a specified value on the y axis.
	 * 
	 * @param value
	 *            y value
	 * @see #translate(double, double)
	 */
	public void translateY(double value) {
		this.translateY += value;
	}
	
	/**
	 * Translate the {@link Viewport} to a specified x and y value.
	 * 
	 * @param x
	 *            x value
	 * @param y
	 *            y value
	 */
	public void translate(double x, double y) {
		this.translateX += x;
		this.translateY += y;
	}
	
	public double getTranslateX() {
		return translateX;
	}
	
	public void setTranslateX(double translateX) {
		this.translateX = translateX;
	}
	
	public double getTranslateY() {
		return translateY;
	}
	
	public void setTranslateY(double translateY) {
		this.translateY = translateY;
	}
	
	public double getScale() {
		return scale;
	}
	
	public void setScale(double scale) {
		this.scale = scale;
	}
	
	public double untransformX(double x) {
		return x / scale - translateX;
	}
	
	public double untransformY(double y) {
		return y / scale - translateY;
	}
	
	public double transformX(double x) {
		return x * scale + translateX;
	}
	
	public double transformY(double y) {
		return y * scale + translateY;
	}
	
}