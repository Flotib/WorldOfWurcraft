package game.action.spell.attack;

import java.util.List;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.Action;
import game.action.spell.AttackSpell;
import game.effect.debuff.CorruptionDebuffEffect;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;
import javafx.scene.paint.Color;

public class CorruptionSpell extends AttackSpell {
	
	/* Variables */
	private int amount, debuffMaxDamage;
	
	/* Constructor */
	public CorruptionSpell() {
		super(FlotibGame.TEXTURE_TEST, 35, Action.CostType.MANA);
		
		this.amount = 10;
		this.debuffMaxDamage = amount * 4;
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetMana(cost);
		
		target.giveEffect(new CorruptionDebuffEffect(this));
		
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Corruption") //
				.description("Mana : " + cost) //
				.description("Corrupts the target, causing " + debuffMaxDamage + " Shadow damage over 12 rounds.").color(Color.YELLOW) //
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