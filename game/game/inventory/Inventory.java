package game.inventory;

import engine.game.content.item.Item;

public class Inventory {
	
	/* Variables */
	protected final int column, row;
	protected final Item[] items;
	
	/* Constructor */
	public Inventory(int column, int row) {
		this.column = column;
		this.row = row;
		
		this.items = new Item[row * column];
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}

	public Item getItemAt(int index) {
		if (index < items.length) {
			return items[index];
		}
		
		return null;
	}
	
}