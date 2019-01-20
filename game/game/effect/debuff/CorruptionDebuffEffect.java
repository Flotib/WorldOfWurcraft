package game.effect.debuff;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.spell.attack.CorruptionSpell;
import game.effect.TimedDebuffEffect;

public class CorruptionDebuffEffect extends TimedDebuffEffect {
	
	/* Variables */
	private int damage;
	
	/* Constructor */
	public CorruptionDebuffEffect(CorruptionSpell corruptionSpell) {
		super(FlotibGame.TEXTURE_ICON_EFFECT_DEBUFF_CORRUPTION, 12);
		
		this.damage = corruptionSpell.getAmount();
	}
	
	@Override
	public boolean execute(LivingEntity livingEntity) {
		if (remainingTime == 10 || remainingTime == 7 || remainingTime == 4 || remainingTime == 1) {
			livingEntity.offsetHealth(-damage);
		}
		
		finishExecute();
		return true;
	}
	
}