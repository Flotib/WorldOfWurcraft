package game.effect.debuff;

import caceresenzo.libs.random.Randomizer;
import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.spell.attack.TwilightImmolationSpell;
import game.effect.TimedDebuffEffect;

public class TwilightImmolationDebuffEffect extends TimedDebuffEffect {
	
	/* Variables */
	private int amount;
	private LivingEntity sender;
	
	/* Constructor */
	public TwilightImmolationDebuffEffect(TwilightImmolationSpell twilightImmolationSpell, LivingEntity sender) {
		super(FlotibGame.TEXTURE_ICON_SPELL_TWILIGHTIMMOLATION, 15);
		
		this.amount = twilightImmolationSpell.getAmount();
		this.sender = sender;
	}
	
	@Override
	public boolean execute(LivingEntity livingEntity) {
		if (remainingTime % 2 == 1) {
			if (Randomizer.randomInt(1, 10) != Randomizer.randomInt(1, 10)) {
				sender.offsetHealth(amount);
				livingEntity.offsetHealth(-amount);
			}
			
		}
		
		finishExecute();
		return true;
	}
	
}