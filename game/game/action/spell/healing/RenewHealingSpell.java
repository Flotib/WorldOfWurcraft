package game.action.spell.healing;

import java.util.List;

import engine.entity.LivingEntity;
import engine.game.content.tooltip.TooltipBuilder;
import engine.game.content.tooltip.TooltipData;
import game.FlotibGame;
import game.action.Action;
import game.action.spell.HealingSpell;
import game.effect.buff.RenewRegenBuffEffect;
import javafx.scene.paint.Color;

public class RenewHealingSpell extends HealingSpell {
	
	/* Variables */
	private int amount;
	private int buffMaxHealing;
	
	/* Constructor */
	public RenewHealingSpell() {
		super(FlotibGame.TEXTURE_ICON_SPELL_RENEW, 20, Action.CostType.MANA);
		
		this.amount = 20;
		this.buffMaxHealing = amount * 15;
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetMana(-cost);
		
		source.giveEffect(new RenewRegenBuffEffect(this));
		
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Renew") //
				.description("Mana : " + cost) //
				.description("Heals you of " + buffMaxHealing + " damage over 15 rounds.").color(Color.YELLOW) //
				.build();
	}
	
	@Override
	public boolean hasCachedTooltip() {
		return false;
	}
	
	public int getAmount() {
		return amount;
	}
	
}