package game.action;

import java.util.List;

import engine.entity.LivingEntity;
import engine.texture.Texture;
import engine.tick.Tickable;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;

public class Action implements Tickable {
	
	/* Constants */
	public static final int ACTION_REPLAY = -2;
	public static final int ACTION_SUCCESS = -1;
	
	/* Variables */
	protected final Texture icon;
	protected int cost;
	protected CostType costType;
	
	/* Constructor */
	public Action(Texture icon, int cost, CostType costType) {
		this.icon = icon;
		this.cost = cost;
		this.costType = costType;
	}
	
	public int use(LivingEntity source, LivingEntity target) {
		return ACTION_SUCCESS;
	}
	
	@Override
	public void tick(double delta) {
		;
	}
	
	public List<TooltipData> createTooltip() {
		return TooltipBuilder.DEFAULT.build();
	}
	
	public boolean hasCachedTooltip() {
		return true;
	}
	
	public enum CostType {
		NOTHING, HEALTH, MANA, RAGE;
	}
	
}