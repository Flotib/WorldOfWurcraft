package game.action.attack.attack;

import java.util.List;

import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.Action;
import game.action.attack.Attack;
import game.effect.buff.BloodRageBuffEffect;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;
import javafx.scene.paint.Color;

public class BloodRageAttack extends Attack {
	
	/* Variables */
	private int amount;
	
	/* Constructor */
	public BloodRageAttack() {
		super(FlotibGame.TEXTURE_TEST, 0, Action.CostType.HEALTH);
		
		this.amount = 20;
	}
	
	@Override
	public void prepare(LivingEntity nextSource, LivingEntity nextTarget) {
		cost = (int) (nextSource.getMaxHealth() * 0.16);
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetHealth(-cost);
		source.offsetRage(amount);
		source.offsetMana(7);
		
		source.giveEffect(new BloodRageBuffEffect());
		
		return ACTION_REPLAY;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Bloodrage") //
				.description("16% of base Health") //
				.description("Instant") //
				.description("Generates " + amount + " rage at the cost of health, and then generates an additional 10 rage over 10 sec.").color(Color.YELLOW) //
				.build();
	}
	
	@Override
	public boolean hasCachedTooltip() {
		return false;
	}
	
}