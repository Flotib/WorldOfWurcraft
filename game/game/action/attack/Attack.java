package game.action.attack;

import engine.texture.Texture;
import game.action.Action;

public class Attack extends Action {
	
	public Attack(Texture icon, int cost, CostType costType) {
		super(icon, cost, costType);
	}
	
}