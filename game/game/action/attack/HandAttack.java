package game.action.attack;

import java.util.List;

import caceresenzo.libs.random.Randomizer;
import engine.entity.LivingEntity;
import game.FlotibGame;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;

public class HandAttack extends Attack {
	
	public HandAttack() {
		super(FlotibGame.TEXTURE_TEST, 0, CostType.NOTHING);
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		target.offsetHealth(-Randomizer.randomInt(3, 5));
		
		source.offsetHealth(7);
		source.offsetRage(Randomizer.randomInt(2, 4));
		
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Attack") //
				.build();
	}
	
}