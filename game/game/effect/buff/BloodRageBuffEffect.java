package game.effect.buff;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.effect.TimedBuffEffect;

public class BloodRageBuffEffect extends TimedBuffEffect {
	
	public BloodRageBuffEffect() {
		super(FlotibGame.TEXTURE_ICON_EFFECT_BUFF_BLOODRAGE, 10);
	}
	
	@Override
	public boolean execute(LivingEntity livingEntity) {
		livingEntity.offsetRage(1);
		
		finishExecute();
		return true;
	}
	
}