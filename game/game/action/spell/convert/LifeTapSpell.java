package game.action.spell.convert;

import java.util.List;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.Action;
import game.action.spell.Spell;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;
import javafx.scene.paint.Color;

public class LifeTapSpell extends Spell {
	
	/* Variables */
	private int amount;
	
	/* Constructor */
	public LifeTapSpell() {
		super(FlotibGame.TEXTURE_ICON_SPELL_LIFETAP, 20, Action.CostType.HEALTH);
		
		this.amount = 20;
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetHealth(-cost);
		source.offsetMana(amount);
		
		return ACTION_REPLAY;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Life Tap") //
				.description("Instant") //
				.description("Converts " + cost + " health into " + amount + " mana.").color(Color.YELLOW) //
				.build();
	}
	
	@Override
	public boolean hasCachedTooltip() {
		return false;
	}
	
}