package game.ui.inventory;

import caceresenzo.libs.logger.Logger;
import engine.GameEngine;
import engine.game.content.item.GameItem;
import engine.game.content.item.Item;
import engine.ui.components.UIComponent;
import game.FlotibGame;
import game.inventory.Inventory;
import game.item.SpellItem;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class ItemHolderComponent extends UIComponent {
	
	/* Constants */
	public static final int HOLDER_SIZE = 64;
	
	/* Variables */
	private final Inventory inventory;
	private final int itemIndex;
	private Item item;
	private boolean holdingClick;
	
	/* Constructor */
	public ItemHolderComponent(double x, double y, Inventory inventory, int itemIndex) {
		super(x, y, HOLDER_SIZE, HOLDER_SIZE);
		
		this.inventory = inventory;
		this.itemIndex = itemIndex;
		
		refreshItem();
	}
	
	@Override
	public void dispatchMouseClick(MouseButton button, Point2D mouseScreenPosition, boolean pressed) {
		super.dispatchMouseClick(button, mouseScreenPosition, pressed);
		
		refreshItem();
	}
	
	public void refreshItem() {
		item = inventory.getItemAt(itemIndex);
	}
	
	@Override
	public void onMouseMouved(Point2D mouseScreenPosition) {
		super.onMouseMouved(mouseScreenPosition);
		
		if (!selected) {
			holdingClick = false;
		}
	}
	
	@Override
	protected void internalOnMouseClick(MouseButton button, Point2D mouseScreenPosition, boolean pressed) {
		holdingClick = pressed;
		
		if (button.equals(MouseButton.PRIMARY) && !pressed) {
			Logger.info("Item: %s", item != null ? item.getClass().getSimpleName() : null);
			
			if (item instanceof GameItem) {
				((GameItem) item).use(FlotibGame.getGame().getPlayer());
			}
		}
	}
	
	@Override
	public void render(GraphicsContext graphics) {
		graphics.save();
		// graphics.scale(1 / scale, -1 / scale);
		
		double imageX = x * scale;
		double imageY = y * scale;
		double imageWidth = width * scale;
		double imageHeight = height * scale;
		
		graphics.drawImage(FlotibGame.TEXTURE_INVENTORY_HOLDER.getImage(), imageX, imageY, imageWidth, imageHeight);
		
		if (item != null) {
			graphics.drawImage(item.getTexture().getImage(), imageX, imageY, imageWidth, imageHeight);
			
			if (item instanceof SpellItem) {
				SpellItem spellItem = (SpellItem) item;
				
				if (!spellItem.getSpell().canLivingEntityUseIt(FlotibGame.getGame().getPlayer())) {
					graphics.drawImage(FlotibGame.TEXTURE_INVENTORY_MASK_DISABLED.getImage(), imageX, imageY, imageWidth, imageHeight);
				}
			}
		}
		
		graphics.drawImage(FlotibGame.TEXTURE_INVENTORY_HOLDER_BORDER.getImage(), imageX, imageY, imageWidth, imageHeight);
		
		if (selected) {
			graphics.drawImage(FlotibGame.TEXTURE_INVENTORY_ITEM_HIGHLIGHT_TRANSPARENCY.getImage(), imageX, imageY, imageWidth, imageHeight);
			
			if (holdingClick) {
				graphics.drawImage(FlotibGame.TEXTURE_INVENTORY_MASK_HOLD_CLICK.getImage(), imageX, imageY, imageWidth, imageHeight);
			}
		}
		
		if (GameEngine.getGameEngine().isDebugging()) {
			graphics.setFill(Color.WHITE);
			graphics.fillRect(imageX, imageY + height - 15, 25, 15);
			graphics.setFill(Color.BLACK);
			graphics.fillText(String.valueOf(itemIndex), imageX + 3, imageY + height - 3);
		}
		
		graphics.restore();
	}
	
	@Override
	public void computeSize() {
		setSize(HOLDER_SIZE, HOLDER_SIZE);
	}
	
	@Override
	public void attachParent(UIComponent parent) {
		if (!(parent instanceof InventoryComponent)) {
			throw new IllegalStateException("Only InventoryComponent can use this components.");
		}
		
		super.attachParent(parent);
	}
	
	@Override
	public void detachParent(UIComponent parent) {
		throw new IllegalAccessError();
	}
	
}