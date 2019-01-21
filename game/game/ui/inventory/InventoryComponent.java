package game.ui.inventory;

import engine.ui.components.implementations.ButtonComponent;
import engine.ui.layout.CustomLayout;
import game.FlotibGame;
import game.inventory.Inventory;

public class InventoryComponent extends CustomLayout {

	public static final int HOLDER_SIZE = ItemHolderComponent.HOLDER_SIZE;
	public static final double SPACE_MOUVEMENT = 1.0;
	
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
				int itemIndex = i + (j * inventory.getColumn());
//				if (itemIndex % 3 == 0) {
//					continue;
//				}
				addComponent(new ItemHolderComponent(i * HOLDER_SIZE * SPACE_MOUVEMENT, j * HOLDER_SIZE * SPACE_MOUVEMENT, inventory, itemIndex));
			}
		}
	}
	
	public static class ItemHolder extends ButtonComponent {
		
		public ItemHolder() {
			super(FlotibGame.TEXTURE_TEST.getImage());
		}
		
	}
	
}