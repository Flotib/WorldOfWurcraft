package game.action.spell.attack;

import java.util.List;

import caceresenzo.libs.random.Randomizer;
import engine.entity.LivingEntity;
import game.FlotibGame;
import game.action.Action;
import game.action.spell.HealingSpell;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;
import javafx.scene.paint.Color;

public class BloodBlastSpell extends HealingSpell {
	
	/* Variables */
	private int cost2;
	private CostType costType2;
	
	private int min, max;
	
	/* Constructor */
	public BloodBlastSpell() {
		super(FlotibGame.TEXTURE_ICON_SPELL_BLOODBLAST, 60, Action.CostType.MANA);
		
		this.cost2 = 50;
		this.costType2 = Action.CostType.RAGE;
		
		this.min = 37;
		this.max = 45;
	}
	
	@Override
	public boolean canLivingEntityUseIt(LivingEntity livingEntity) {
		return super.canLivingEntityUseIt(livingEntity) && hasEnough(livingEntity, costType2, cost2);
	}
	
	@Override
	public int use(LivingEntity source, LivingEntity target) {
		source.offsetMana(-cost);
		source.offsetRage(-cost2);
		
		source.offsetHealth(-(Randomizer.randomInt(min, max)));
		
		return ACTION_SUCCESS;
	}
	
	@Override
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title("Heal Drain") //
				.description("Mana : " + cost) //
				.description("Drain between " + min + " and " + max + " mana point from your target.").color(Color.YELLOW) //
				.build();
	}
	
	@Override
	public boolean hasCachedTooltip() {
		return false;
	}
	
}