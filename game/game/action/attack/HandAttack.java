package game.action.attack;

import java.util.List;

import caceresenzo.libs.random.Randomizer;
import engine.entity.LivingEntity;
import engine.game.content.tooltip.TooltipBuilder;
import engine.game.content.tooltip.TooltipData;
import game.FlotibGame;

public class HandAttack extends Attack {
	
	public HandAttack() {
		super(FlotibGame.TEXTURE_ICON_ABILITY_BASEATTACK, 0, CostType.NOTHING);
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		target.offsetHealth(-Randomizer.randomInt(3, 5));
		
		source.offsetMana(7);
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