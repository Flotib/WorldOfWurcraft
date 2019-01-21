package game.action.spell.attack;

import java.util.List;

import caceresenzo.libs.random.Randomizer;
import engine.entity.LivingEntity;
import engine.game.content.tooltip.TooltipBuilder;
import engine.game.content.tooltip.TooltipData;
import game.FlotibGame;
import game.action.Action;
import game.action.spell.AttackSpell;
import game.effect.debuff.BurningDebuffEffect;
import javafx.scene.paint.Color;

public class FireballSpell extends AttackSpell {
	
	/* Variables */
	private int minDamage, maxDamage, debuffDamage;
	
	/* Constructor */
	public FireballSpell() {
		super(FlotibGame.TEXTURE_ICON_SPELL_FIREBALL, 30, Action.CostType.MANA);
		
		this.minDamage = 14;
		this.maxDamage = 23;
		this.debuffDamage = 2;
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetMana(-cost);
		target.offsetHealth(-Randomizer.randomInt(minDamage, maxDamage));
		
		target.giveEffect(new BurningDebuffEffect(this));
		
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Fireball") //
				.description("Mana : " + cost) //
				.description("Hurls a fiery ball that causes " + minDamage + " to " + maxDamage + " Fire damage and an additional " + debuffDamage + " Fire damage over 4 rounds.").color(Color.YELLOW) //
				.build();
	}
	
	@Override
	public boolean hasCachedTooltip() {
		return false;
	}
	
	public int getDebuffDamage() {
		return debuffDamage;
	}
	
}