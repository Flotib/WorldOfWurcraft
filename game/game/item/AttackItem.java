package game.item;

import engine.game.content.item.GameItem;
import game.action.attack.Attack;

public class AttackItem extends GameItem {
	
	public AttackItem(Attack attack) {
		super(attack.getClass().getSimpleName(), attack.getIcon());
	}
	
}