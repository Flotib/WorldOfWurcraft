package game.action.spell;

import engine.texture.Texture;
import game.action.Action;

public class Spell extends Action {
	
	public Spell(Texture icon, int cost, CostType costType) {
		super(icon, cost, costType);
	}
	
}