package game.action.attack.test;

import java.util.List;

import engine.entity.LivingEntity;
import engine.game.content.tooltip.TooltipBuilder;
import engine.game.content.tooltip.TooltipData;
import game.FlotibGame;
import game.action.attack.Attack;
import javafx.scene.paint.Color;

public class LevelUpDebugAttack extends Attack {
	
	public LevelUpDebugAttack() {
		super(FlotibGame.TEXTURE_ICON_TEST, 0, CostType.NOTHING);
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.levelUp();
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("[Debug] Player level up") //
				.description("Level up the player").color(Color.YELLOW) //
				.build();
	}
	
}