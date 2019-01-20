package game.action.attack.attack;

import java.util.List;

import caceresenzo.libs.random.Randomizer;
import engine.entity.LivingEntity;
import engine.game.content.tooltip.TooltipBuilder;
import engine.game.content.tooltip.TooltipData;
import game.FlotibGame;
import game.action.Action;
import game.action.attack.Attack;
import javafx.scene.paint.Color;

public class RitualOfSacrificeAttack extends Attack {
	
	/* Variables */
	private int cost2;
	private CostType costType2;
	
	private int damage;
	
	/* Constructor */
	public RitualOfSacrificeAttack() {
		super(FlotibGame.TEXTURE_ICON_SPELL_RITUALOFSACRIFICE, 24, Action.CostType.MANA);
		
		this.cost2 = 15;
		this.costType2 = Action.CostType.RAGE;
		
		this.damage = 18;
	}
	
	@Override
	public boolean canLivingEntityUseIt(LivingEntity livingEntity) {
		return super.canLivingEntityUseIt(livingEntity) && hasEnough(livingEntity, costType2, cost2);
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetMana(cost);
		source.offsetRage(cost2);
		
		target.offsetHealth(-(Randomizer.randomInt(3, 5) + damage));
		
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Ritual of Sacrifice") //
				.description("Mana : " + cost) //
				.description("Rage : " + cost2) //
				.description("Empowered your weapon with mana and hit the enemy. Deal " + damage + " in addition to your weapon base damage.").color(Color.YELLOW) //
				.build();
	}
	
	@Override
	public boolean hasCachedTooltip() {
		return false;
	}
	
}