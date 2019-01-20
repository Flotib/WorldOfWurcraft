package game.action.attack.attack;

import java.util.List;

import caceresenzo.libs.random.Randomizer;
import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.Action;
import game.action.attack.Attack;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;
import javafx.scene.paint.Color;

public class BloodyHitAttack extends Attack {
	
	/* Variables */
	private int amount, damage;
	
	/* Constructor */
	public BloodyHitAttack() {
		super(FlotibGame.TEXTURE_ICON_ABILITY_BLOODYHIT, 20, Action.CostType.RAGE);
		
		this.damage = 4;
	}
	
	@Override
	public void prepare(LivingEntity nextSource, LivingEntity nextTarget) {
		amount = (int) (nextSource.getBaseHealth() * 0.16);
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetRage(-cost);
		source.offsetMana(7);
		source.offsetHealth(amount);
		
		target.offsetHealth(-(Randomizer.nextRangeInt(3, 5) + damage));
		
		return ACTION_REPLAY;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Bloody Hit") //
				.description("Rage : " + cost) //
				.description("Bloody attack who inflicts " + damage + " damage to the target and give you back 16% of your base health.").color(Color.YELLOW) //
				.build();
	}
	
	@Override
	public boolean hasCachedTooltip() {
		return false;
	}
	
}