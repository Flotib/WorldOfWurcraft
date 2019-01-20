package game.action.spell.healing;

import java.util.List;

import caceresenzo.libs.random.Randomizer;
import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.Action;
import game.action.spell.HealingSpell;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;
import javafx.scene.paint.Color;

public class FlashHealHealingSpell extends HealingSpell {
	
	/* Variables */
	private int minHealing, maxHealing;
	
	/* Constructor */
	public FlashHealHealingSpell() {
		super(FlotibGame.TEXTURE_TEST, 35, Action.CostType.MANA);
		
		this.minHealing = 54;
		this.maxHealing = 67;
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetMana(-cost);
		source.offsetHealth(Randomizer.randomInt(minHealing, maxHealing));
		
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Renew") //
				.description("Mana : " + cost) //
				.description("Instant") //
				.description("Heals you for " + minHealing + " to " + maxHealing + ".").color(Color.YELLOW) //
				.build();
	}
	
	@Override
	public boolean hasCachedTooltip() {
		return false;
	}
	
}