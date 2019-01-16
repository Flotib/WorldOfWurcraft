package engine.ui.components;

import engine.render.Renderable;

public abstract class UIComponent implements Renderable {
	
	/* Constants */
	public static final double INVALID = -1;
	public static final double DEFAULT_SCALE = 1;
	
	/* Variables */
	protected double x, y;
	protected double width, height;
	protected double scale = DEFAULT_SCALE;
	protected boolean invalid;
	protected UIComponent parent;
	
	/* Constructor */
	public UIComponent() {
		this(0, 0, INVALID, INVALID);
	}
	
	/* Constructor */
	public UIComponent(double x, double y) {
		this(x, y, INVALID, INVALID);
	}
	
	/* Constructor */
	public UIComponent(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Called when the {@link UIComponent} need to re-compute its size (width and height).
	 */
	public abstract void computeSize();
	
	public void validate() {
		if (invalid) {
			computeSize();
			this.invalid = false;
		}
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public double getScale() {
		return scale;
	}
	
	public void setScale(double scale) {
		this.scale = scale;
	}
	
	public boolean isValid() {
		return !invalid;
	}
	
	/**
	 * Invalidate this {@link UIComponent}, will cause to re-compute data when validating.
	 */
	public void invalidate() {
		this.invalid = true;
	}
	
	/**
	 * @return Attached parent.
	 */
	public UIComponent getParent() {
		return parent;
	}
	
	/**
	 * Attach a parent to this {@link UIComponent}.
	 * 
	 * @param parent
	 *            Target parent.
	 * @throws IllegalStateException
	 *             If the {@link UIComponent} already have a parent attached.
	 */
	protected void attachParent(UIComponent parent) {
		if (this.parent != null) {
			throw new IllegalStateException("This component already have a parent attached.");
		}
		
		if (this.parent == parent) {
			invalidate();
		}
		
		this.parent = parent;
	}
	
	/**
	 * Detach the parent to this {@link UIComponent}.
	 * 
	 * @param parent
	 *            Target parent.
	 * @throws IllegalStateException
	 *             If the detached parent is not the same as attached parent.
	 */
	protected void detachParent(UIComponent parent) {
		if (parent != this.parent) {
			throw new IllegalStateException("Only parent can detach himself.");
		}
		
		this.parent = null;
	}
	
}
