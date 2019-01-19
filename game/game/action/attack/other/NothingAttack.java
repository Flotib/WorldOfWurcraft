package game.action.attack.other;

import java.util.List;

import game.FlotibGame;
import game.action.attack.Attack;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;
import javafx.scene.paint.Color;

public class NothingAttack extends Attack {
	
	public NothingAttack() {
		super(FlotibGame.TEXTURE_TEST, 0, CostType.NOTHING);
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("[Debug] Do nothing :/") //
				.description("You have basically do nothing").color(Color.YELLOW) //
				.description("Yeah... Noob.").color(Color.YELLOW) //
				.build();
	}
	
}