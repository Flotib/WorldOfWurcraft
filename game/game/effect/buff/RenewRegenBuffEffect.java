package game.effect.buff;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.spell.healing.RenewHealingSpell;
import game.effect.TimedBuffEffect;

public class RenewRegenBuffEffect extends TimedBuffEffect {
	
	/* Variables */
	private int amount;
	
	/* Constructor */
	public RenewRegenBuffEffect(RenewHealingSpell renewHealingSpell) {
		super(FlotibGame.TEXTURE_TEST, 15);
		
		this.amount = renewHealingSpell.getAmount();
	}
	
	@Override
	public boolean execute(LivingEntity livingEntity) {
		livingEntity.offsetHealth(amount);
		
		finishExecute();
		return true;
	}
	
}