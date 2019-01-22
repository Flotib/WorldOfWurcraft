package game.ui.inventory;

import engine.ui.layout.CustomLayout;
import game.inventory.Inventory;

public class InventoryComponent extends CustomLayout {

	/* Constants */
	public static final int HOLDER_SIZE = ItemHolderComponent.HOLDER_SIZE;
	public static final double SPACE_MOUVEMENT = 1.0;
	
	/* Variables */
	protected final Inventory inventory;
	
	/* Constructor */
	public InventoryComponent(Inventory inventory) {
		this(inventory, 0, 0);
	}
	
	/* Constructor */
	public InventoryComponent(Inventory inventory, double x, double y) {
		super(x, y);
		
		this.inventory = inventory;
		
		for (int i = 0; i < inventory.getColumn(); i++) {
			for (int j = 0; j < inventory.getRow(); j++) {
				int itemIndex = i + (j * inventory.getColumn());
				addComponent(new ItemHolderComponent(i * HOLDER_SIZE * SPACE_MOUVEMENT, j * HOLDER_SIZE * SPACE_MOUVEMENT, inventory, itemIndex));
			}
		}
	}
	
}