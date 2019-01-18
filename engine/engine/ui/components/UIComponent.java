package engine.ui.components;

import engine.collision.Collidable;
import engine.render.Renderable;
import engine.ui.layout.UILayout;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public abstract class UIComponent implements Renderable, Collidable<Point2D> {
	
	/* Constants */
	public static final Font DEFAULT_FONT = Font.font("Arial", 15);
	public static final Paint DEFAULT_COLOR = Color.BLACK;
	
	public static final double INVALID = -1;
	public static final double DEFAULT_SCALE = 1;
	public static final double DEFAULT_CANVAS_RESCALE = 1;
	
	/**
	 * Render the {@link UIComponent} normally.
	 */
	public static final byte RENDER_NORMAL = 0;
	/**
	 * Render the {@link UIComponent} with some debug information like collision box.
	 */
	public static final byte RENDER_DEBUG = 1;
	
	/**
	 * Set the {@link UIComponent} as visible.
	 */
	public static final byte VISIBILITY_VISIBLE = 0;
	/**
	 * Set the {@link UIComponent} as not visible but present.
	 */
	public static final byte VISIBILITY_INVISIBLE = 1;
	/**
	 * Set the {@link UIComponent} as not visible and not present.
	 */
	public static final byte VISIBILITY_GONE = 2;
	
	/* Variables */
	protected double x, y;
	protected double width, height;
	protected double scale = DEFAULT_SCALE;
	protected boolean invalid = true, selected;
	protected UIComponent parent;
	protected byte renderMode, visibility;
	private OnClickListener onClickListener;
	protected boolean enabled, clickable;
	
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
		
		this.enabled = this.clickable = true;
	}
	
	/**
	 * Called when the {@link UIComponent} need to re-compute its size (width and height).
	 */
	public abstract void computeSize();
	
	/**
	 * Start rendering of this {@link UIComponent}.<br>
	 * This will validate component if not already, call {@link #render(GraphicsContext)} and {@link #renderDebug(GraphicsContext)} (if in debug rendering mode).
	 * 
	 * @param graphics
	 *            {@link Canvas}'s {@link GraphicsContext}.
	 */
	public void doRender(GraphicsContext graphics) {
		if (!isValid()) {
			validate();
		}
		
		render(graphics);
		
		if (getRenderMode() == UIComponent.RENDER_DEBUG) {
			renderDebug(graphics);
		}
	}
	
	/**
	 * Render some debug information for the {@link UIComponent}.
	 * 
	 * @param graphics
	 *            {@link Canvas}'s {@link GraphicsContext}.
	 */
	public void renderDebug(GraphicsContext graphics) {
		computeSize();
		
		graphics.save();
		
		double componentX = getAbsoluteX() * scale;
		double componentY = getAbsoluteY() * scale;
		double componentWidth = width * scale;
		double componentHeight = height * scale;
		
		Paint paint = Color.PINK;
		if (selected) {
			paint = Color.GREEN;
		}
		graphics.setStroke(paint);
		graphics.strokeRect(componentX, -componentY - componentHeight, componentWidth, componentHeight);
		
		double cornerLength = 2 * scale;
		
		graphics.setStroke(Color.RED);
		graphics.strokeLine(componentX - 1, -componentY, componentX + cornerLength, -componentY);
		graphics.strokeLine(componentX, -componentY + 1, componentX, -componentY - cornerLength);
		
		graphics.restore();
	}
	
	/**
	 * Validate component if not already.
	 */
	public void validate() {
		if (invalid) {
			computeSize();
			
			this.invalid = false;
		}
	}
	
	/**
	 * Called when the mouse has moved.<br>
	 * Allow component to check if they are being selected or not.
	 * 
	 * @param mouseScreenPosition
	 *            Tarnet new mouse position of screen.
	 */
	public void onMouseMouved(Point2D mouseScreenPosition) {
		if (!enabled) {
			return;
		}
		
		selected = collide(mouseScreenPosition);
	}
	
	@Override
	public boolean collide(Point2D other) {
		if (getVisibility() == VISIBILITY_GONE) {
			return false;
		}
		
		return (getAbsoluteX() <= other.getX() && other.getX() <= getAbsoluteX() + width) && (getAbsoluteY() <= other.getY() && other.getY() <= getAbsoluteY() + height);
	}
	
	/**
	 * Get the highest {@link UIComponent} on the stack.
	 * 
	 * @return Highest {@link UIComponent} on screen.
	 */
	public UIComponent getHeighestComponent() {
		if (selected) {
			return this;
		}
		
		return null;
	}
	
	/**
	 * Dispatch a mouse click event on this {@link UIComponent}.<br>
	 * If there is any {@link OnClickListener} attached to it, this will call {@link OnClickListener#onClick(UIComponent, MouseButton, Point2D, boolean)}.
	 * 
	 * @param button
	 *            Target mouse button.
	 * @param mouseScreenPosition
	 *            Mouse position when event was fired.
	 * @param pressed
	 *            If the mouse click is pressed or released.
	 */
	public void dispatchMouseClick(MouseButton button, Point2D mouseScreenPosition, boolean pressed) {
		if (onClickListener != null && isClickable()) {
			onClickListener.onClick(this, button, mouseScreenPosition, pressed);
		}
	}
	
	/**
	 * @return {@link UIComponent}'s x position.
	 */
	public double getX() {
		return x;
	}
	
	public double getAbsoluteX() {
		double absolute = x;
		
		if (getParent() != null) {
			if (getParent() instanceof UILayout) {
				absolute += ((UILayout) getParent()).getAbsoluteXOf(this);
			} else {
				absolute += getParent().getAbsoluteX();
			}
		}
		
		return absolute;
	}
	
	/**
	 * Set a new x position for this {@link UIComponent}.
	 * 
	 * @param x
	 *            New x position.
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * @return {@link UIComponent}'s y position.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Set a new y position for this {@link UIComponent}.
	 * 
	 * @param y
	 *            New y position.
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	public double getAbsoluteY() {
		double absolute = y;
		
		if (getParent() != null) {
			if (getParent() instanceof UILayout) {
				absolute += ((UILayout) getParent()).getAbsoluteYOf(this);
			} else {
				absolute += getParent().getAbsoluteY();
			}
		}
		
		return absolute;
	}
	
	/**
	 * Set a new x and y position for this {@link UIComponent}.
	 * 
	 * @param x
	 *            New x position.
	 * @param y
	 *            New y position.
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return {@link UIComponent}'s width.
	 */
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	/**
	 * @return {@link UIComponent}'s height.
	 */
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
	
	/**
	 * @return {@link UIComponent}'s scale.
	 */
	public double getScale() {
		return scale;
	}
	
	public void setScale(double scale) {
		this.scale = scale;
	}
	
	/**
	 * @return {@link UIComponent}'s validity.
	 */
	public boolean isValid() {
		return !invalid;
	}
	
	/**
	 * @return {@link UIComponent}'s actual rendering mode.<br>
	 *         Can be {@link #RENDER_NORMAL} or {@link #RENDER_DEBUG}.
	 */
	public byte getRenderMode() {
		return renderMode;
	}
	
	/**
	 * Set new visibility mode for this {@link UIComponent}.<br>
	 * Can be {@link #RENDER_NORMAL}, or {@link #RENDER_DEBUG}.<br>
	 * This will change how the {@link UIComponent} will be render.
	 * 
	 * @param renderMode
	 *            New rendering mode.
	 */
	public void setRenderMode(byte renderMode) {
		this.renderMode = renderMode;
	}
	
	/**
	 * @return {@link UIComponent}'s actual visiblitity mode.<br>
	 *         Can be {@link #VISIBILITY_VISIBLE}, {@link #VISIBILITY_INVISIBLE} or {@link #VISIBILITY_GONE}.
	 */
	public byte getVisibility() {
		return visibility;
	}
	
	/**
	 * Set new visibility mode for this {@link UIComponent}.<br>
	 * Can be {@link #VISIBILITY_VISIBLE}, {@link #VISIBILITY_INVISIBLE} or {@link #VISIBILITY_GONE}.<br>
	 * This will change how the {@link UIComponent} will be render.
	 * 
	 * @param visibility
	 *            New visibility mode.
	 */
	public void setVisibility(byte visibility) {
		this.visibility = visibility;
	}
	
	/**
	 * @return If the {@link UIComponent} can render itself.
	 */
	public boolean canRender() {
		return getVisibility() != VISIBILITY_GONE;
	}
	
	/**
	 * Invalidate this {@link UIComponent}, will cause to re-compute data when validating.
	 */
	public void invalidate() {
		this.invalid = true;
		
		if (getParent() != null) {
			getParent().invalidate();
		}
	}
	
	/**
	 * @return Attached parent.
	 */
	public UIComponent getParent() {
		return parent;
	}
	
	/**
	 * Set a new {@link OnClickListener} for this {@link UIComponent}.
	 * 
	 * @param onClickListener
	 *            Target {@link OnClickListener} instance.
	 */
	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}
	
	/**
	 * @return If the {@link UIComponent} is enabled or not.
	 */
	public boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * Set new enabled state for this {@link UIComponent}.
	 * 
	 * @param enabled
	 *            New state.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * @return If the {@link UIComponent} is allow click event to be dispatched.
	 */
	public boolean isClickable() {
		return clickable;
	}
	
	/**
	 * Set a new clickable state for this {@link UIComponent}.
	 * 
	 * @param clickable
	 *            New state.
	 */
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	/**
	 * Attach a parent to this {@link UIComponent}.
	 * 
	 * @param parent
	 *            Target parent.
	 * @throws IllegalStateException
	 *             If the {@link UIComponent} already have a parent attached.
	 */
	public void attachParent(UIComponent parent) {
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
	public void detachParent(UIComponent parent) {
		if (parent != this.parent) {
			throw new IllegalStateException("Only parent can detach himself.");
		}
		
		this.parent = null;
	}
	
	public interface OnClickListener {
		
		void onClick(UIComponent component, MouseButton button, Point2D mouseScreenPosition, boolean pressed);
		
	}
	
}