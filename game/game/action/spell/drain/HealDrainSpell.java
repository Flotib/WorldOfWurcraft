package game.action.spell.drain;

import java.util.List;

import caceresenzo.libs.random.Randomizer;
import engine.entity.LivingEntity;
import engine.game.content.tooltip.TooltipBuilder;
import engine.game.content.tooltip.TooltipData;
import game.FlotibGame;
import game.action.Action;
import game.action.spell.HealingSpell;
import javafx.scene.paint.Color;

public class HealDrainSpell extends HealingSpell {
	
	/* Variables */
	private int min, max;
	
	/* Constructor */
	public HealDrainSpell() {
		super(FlotibGame.TEXTURE_ICON_SPELL_LIFEDRAIN, 17, Action.CostType.MANA);
		
		this.min = 3;
		this.max = min * 5;
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetMana(-cost);
		
		int drain = min * Randomizer.nextRangeInt(1, 5);
		if (target.getHealth() < drain) {
			drain = (int) target.getHealth();
		}
		
		target.offsetHealth(-drain);
		source.offsetHealth(drain);
		
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Heal Drain") //
				.description("Mana : " + cost) //
				.description("Transfers between " + min + " and " + max + " health from the target to the caster.").color(Color.YELLOW) //
				.build();
	}
	
	@Override
	public boolean hasCachedTooltip() {
		return false;
	}
	
}