package game.action.attack.attack;

import java.util.List;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.Action;
import game.action.attack.Attack;
import game.effect.debuff.BleedingRendDebuffEffect;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;
import javafx.scene.paint.Color;

public class RendAttack extends Attack {
	
	/* Variables */
	private int amount, debuffDamage;
	
	/* Constructor */
	public RendAttack() {
		super(FlotibGame.TEXTURE_ICON_ABILITY_REND, 10, Action.CostType.RAGE);

		this.amount = 5;
		this.debuffDamage = 15;
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetRage(-cost);
		source.offsetMana(7);
		
		target.giveEffect(new BleedingRendDebuffEffect(this));
		
		return ACTION_REPLAY;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Rend") //
				.description("Rage : " + cost) //
				.description("Wounds the target causing them to bleed for " + debuffDamage + " damage over 9 rounds.").color(Color.YELLOW) //
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