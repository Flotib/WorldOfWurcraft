package game.effect.debuff;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.attack.attack.RendAttack;
import game.effect.TimedDebuffEffect;

public class BleedingRendDebuffEffect extends TimedDebuffEffect {
	
	/* Variables */
	private int damage;
	
	/* Constructor */
	public BleedingRendDebuffEffect(RendAttack rendAttack) {
		super(FlotibGame.TEXTURE_ICON_EFFECT_DEBUFF_REND, 9);
		
		this.damage = rendAttack.getAmount();
	}
	
	@Override
	public boolean execute(LivingEntity livingEntity) {
		if (remainingTime == 7 || remainingTime == 4 || remainingTime == 1) {
			livingEntity.offsetHealth(-damage);
		}
		
		finishExecute();
		return true;
	}
	
}