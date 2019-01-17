package engine.ui.components;

import caceresenzo.libs.logger.Logger;
import engine.render.Renderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class UIComponent implements Renderable {
	
	/* Constants */
	public static final Font DEFAULT_FONT = Font.font("Arial", 12);
	
	public static final double INVALID = -1;
	public static final double DEFAULT_SCALE = 1;
	
	public static final byte RENDER_NORMAL = 0;
	public static final byte RENDER_DEBUG = 1;
	
	public static final byte VISIBILITY_VISIBLE = 0;
	public static final byte VISIBILITY_INVSIBLE = 1;
	public static final byte VISIBILITY_GONE = 2;
	
	/* Variables */
	protected double x, y;
	protected double width, height;
	protected double scale = DEFAULT_SCALE;
	protected boolean invalid = true;
	protected UIComponent parent;
	protected byte renderMode, visibility;
	
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
	
	public void doRender(GraphicsContext graphics) {
		if (!isValid()) {
			validate();
		}
		
		render(graphics);
		
		if (getRenderMode() == UIComponent.RENDER_DEBUG) {
			renderDebug(graphics);
		}
	}
	
	public void renderDebug(GraphicsContext graphics) {
		computeSize();
		
		graphics.save();
		graphics.scale(1 / scale, -1 / scale);
		
		double componentX = x * scale;
		double componentY = y * scale;
		double componentWidth = width * scale;
		double componentHeight = height * scale;
		
//		graphics.setFill(Color.GREEN);
//		graphics.fillRect(componentX, -componentY, componentWidth, componentHeight);

		graphics.setStroke(Color.PINK);
		graphics.strokeRect(componentX, -componentY, componentWidth, componentHeight);
		
		double cornerLength = 2 * scale;
		
		graphics.setStroke(Color.RED);
		graphics.strokeLine(componentX - 1, -componentY, componentX + cornerLength, -componentY);
		graphics.strokeLine(componentX, -componentY - 1, componentX, -componentY + cornerLength);
		
		Logger.info(width);
		
		graphics.restore();
	}
	
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
	
	public byte getRenderMode() {
		return renderMode;
	}
	
	public void setRenderMode(byte renderMode) {
		this.renderMode = renderMode;
	}
	
	public byte getVisibility() {
		return visibility;
	}
	
	public void setVisibility(byte visibility) {
		this.visibility = visibility;
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
	
}
