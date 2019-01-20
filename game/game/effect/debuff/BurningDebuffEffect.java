package game.effect.debuff;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.spell.attack.FireballSpell;
import game.effect.TimedDebuffEffect;

public class BurningDebuffEffect extends TimedDebuffEffect {
	
	/* Variables */
	private int damage;
	
	/* Constructor */
	public BurningDebuffEffect(FireballSpell fireballSpell) {
		super(FlotibGame.TEXTURE_ICON_EFFECT_DEBUFF_BURNING, 4);
		
		this.damage = fireballSpell.getDebuffDamage() / 2;
	}
	
	@Override
	public boolean execute(LivingEntity livingEntity) {
		if (remainingTime % 2 == 1) {
			livingEntity.offsetHealth(-damage);
		}

		finishExecute();
		return true;
	}
	
}