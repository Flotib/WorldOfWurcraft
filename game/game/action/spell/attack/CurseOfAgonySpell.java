package game.action.spell.attack;

import java.util.List;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.Action;
import game.action.spell.AttackSpell;
import game.effect.debuff.CurseOfAgonyDebuffEffect;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;
import javafx.scene.paint.Color;

public class CurseOfAgonySpell extends AttackSpell {
	
	/* Variables */
	private int debuffMinDamage, debuffMaxDamage;
	
	/* Constructor */
	public CurseOfAgonySpell() {
		super(FlotibGame.TEXTURE_TEST, 25, Action.CostType.MANA);
		
		this.debuffMinDamage = 7;
		this.debuffMaxDamage = debuffMinDamage * 12;
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetMana(cost);
		
		target.giveEffect(new CurseOfAgonyDebuffEffect(this));
		
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Curse of Agony") //
				.description("Mana : " + cost) //
				.description("Curses the target with agony, causing " + debuffMaxDamage + " Shadow damage over 24 rounds. This damage is dealt slowly at first, and builds up as the Curse reaches its full duration. Only one Curse can be active on any one target.").color(Color.YELLOW) //
				.build();
	}
	
	@Override
	public boolean hasCachedTooltip() {
		return false;
	}
	
	public int getDebuffMinDamage() {
		return debuffMinDamage;
	}
	
}