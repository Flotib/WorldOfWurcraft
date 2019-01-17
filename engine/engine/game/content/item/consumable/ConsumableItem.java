package engine.game.content.item.consumable;

import engine.game.content.item.base.GameItem;
import engine.texture.Texture;

public abstract class ConsumableItem<T> extends GameItem {
	
	public ConsumableItem(String name, Texture texture) {
		super(name, texture);
	}
	
	public abstract boolean use(T with);
	
}