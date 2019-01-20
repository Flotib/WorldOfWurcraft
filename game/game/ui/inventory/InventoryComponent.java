package game.ui.inventory;

import engine.game.content.item.Item;
import engine.ui.components.implementations.ButtonComponent;
import engine.ui.components.implementations.ImageComponent;
import engine.ui.layout.CustomLayout;
import game.FlotibGame;
import game.inventory.Inventory;
import javafx.scene.canvas.GraphicsContext;

public class InventoryComponent extends CustomLayout {
	
	public static final int HOLDER_SIZE = 64;
	
	/* Variables */
	protected final Inventory inventory;
	
	/* Constructor */
	public InventoryComponent(Inventory inventory) {
		this(inventory, 0, 0);
	}
	
	/* Constructor */
	public InventoryComponent(Inventory inventory, int x, int y) {
		super(x, y);
		
		this.inventory = inventory;
		
		for (int i = 0; i < inventory.getColumn(); i++) {
			for (int j = 0; j < inventory.getRow(); j++) {
				final int itemIndex = i + (j * inventory.getColumn());
				addComponent(new ImageComponent(FlotibGame.TEXTURE_TEST.getImage(), i * HOLDER_SIZE * 1.2, j * HOLDER_SIZE * 1.2, HOLDER_SIZE, HOLDER_SIZE) {
					@Override
					public void render(GraphicsContext graphics) {
						Item item = inventory.getItemAt(itemIndex);
						if (item != null) {
							image = inventory.getItemAt(itemIndex).getTexture().getImage();
						} else {
							image = null;
						}
						super.render(graphics);
					}
				});
				// addComponent(new TextComponent(String.valueOf(i + (j * inventory.getColumn())), i * 32 * 1.2, j * 32 * 1.2, 32, 32));
			}
		}
	}
	
	public static class ItemHolder extends ButtonComponent {
		
		public ItemHolder() {
			super(FlotibGame.TEXTURE_TEST.getImage());
		}
		
	}
	
}