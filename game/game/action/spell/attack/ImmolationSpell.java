package game.action.spell.attack;

import java.util.List;

import engine.entity.LivingEntity;
import engine.game.content.tooltip.TooltipBuilder;
import engine.game.content.tooltip.TooltipData;
import game.FlotibGame;
import game.action.Action;
import game.action.spell.AttackSpell;
import game.effect.debuff.ImmolationDebuffEffect;
import javafx.scene.paint.Color;

public class ImmolationSpell extends AttackSpell {
	
	/* Variables */
	private int amount, damage, debuffDamage;
	
	/* Constructor */
	public ImmolationSpell() {
		super(FlotibGame.TEXTURE_ICON_SPELL_IMMOLATION, 25, Action.CostType.MANA);
		
		this.amount = 8;
		this.damage = 4;
		this.debuffDamage = amount * 5;
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetMana(cost);
		target.offsetHealth(-damage);
		
		target.giveEffect(new ImmolationDebuffEffect(this));
		
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Immolation") //
				.description("Mana : " + cost) //
				.description("Burns the enemy for " + damage + " Fire damage and then an additional " + debuffDamage + " Fire damage over 15 rounds.").color(Color.YELLOW) //
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