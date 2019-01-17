package engine.game;

public class GameObject {
	
	/* Variables */
	public double x, y;
	public double width, height;
	
	/* Constructor */
	public GameObject() {
		this(0, 0, 0, 0);
	}
	
	/* Constructor */
	public GameObject(double x, double y) {
		this(x, y, 0, 0);
	}
	
	/* Constructor */
	public GameObject(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * @return Object's x position.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Set new object's x position.
	 * 
	 * @param x
	 *            New x value.
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * @return Object's y position.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Set new object's y position.
	 * 
	 * @param y
	 *            New y value.
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * @return Object's width.
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * Set new object's width.
	 * 
	 * @param width
	 *            New width value.
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	
	/**
	 * @return Object's height.
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * Set new object's height.
	 * 
	 * @param height
	 *            New height value.
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
}