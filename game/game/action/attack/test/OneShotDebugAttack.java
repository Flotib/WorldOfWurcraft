package game.action.attack.test;

import java.util.List;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.attack.Attack;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;
import javafx.scene.paint.Color;

public class OneShotDebugAttack extends Attack {
	
	public OneShotDebugAttack() {
		super(FlotibGame.TEXTURE_TEST, 0, CostType.NOTHING);
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		target.kill();
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("[Debug] Oneshot") //
				.description("K.O").color(Color.YELLOW) //
				.build();
	}
	
}