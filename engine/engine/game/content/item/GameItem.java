package engine.game.content.item;

import engine.entity.LivingEntity;
import engine.texture.Texture;

public class GameItem extends Item {

	public GameItem(String name, Texture texture) {
		super(name, texture);
	}
	
	public void use(LivingEntity livingEntity) {
		;
	}
	
}