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

public class HeroicStrikeAttack extends Attack {
	
	/* Variables */
	private int damage;
	
	/* Constructor */
	public HeroicStrikeAttack() {
		super(FlotibGame.TEXTURE_TEST, 15, Action.CostType.RAGE);
		
		this.damage = 11;
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetRage(-cost);
		source.offsetMana(7);
		
		target.offsetHealth(Randomizer.randomInt(3, 5));
		
		return ACTION_REPLAY;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Heroic Strike") //
				.description("Rage : " + cost) //
				.description("A strong attack that inflicts " + damage + " melee damage to the enemy.").color(Color.YELLOW) //
				.build();
	}
	
	@Override
	public boolean hasCachedTooltip() {
		return false;
	}
	
}