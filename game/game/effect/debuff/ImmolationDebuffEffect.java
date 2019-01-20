package game.effect.debuff;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.spell.attack.ImmolationSpell;
import game.effect.TimedDebuffEffect;

public class ImmolationDebuffEffect extends TimedDebuffEffect {
	
	/* Variables */
	private int amount;
	
	/* Constructor */
	public ImmolationDebuffEffect(ImmolationSpell immolationSpell) {
		super(FlotibGame.TEXTURE_TEST, 15);
		
		this.amount = immolationSpell.getAmount();
	}
	
	@Override
	public boolean execute(LivingEntity livingEntity) {
		if (remainingTime % 2 == 1) {
			livingEntity.offsetHealth(-amount);
		}
		
		finishExecute();
		return true;
	}
	
}