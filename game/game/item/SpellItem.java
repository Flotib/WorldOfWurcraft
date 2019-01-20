package game.item;

import engine.game.content.item.GameItem;
import game.action.spell.Spell;

public class SpellItem extends GameItem {
	
	public SpellItem(Spell spell) {
		super(spell.getClass().getSimpleName(), spell.getIcon());
	}
	
}