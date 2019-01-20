package game.action;

import java.util.List;

import engine.entity.LivingEntity;
import engine.texture.Texture;
import engine.tick.Tickable;
import game.tooltip.TooltipBuilder;
import game.tooltip.TooltipData;
import javafx.scene.paint.Color;

public class Action implements Tickable {
	
	/* Constants */
	/**
	 * The {@link Action} success.
	 */
	public static final int ACTION_SUCCESS = -1;
	/**
	 * The {@link Action} success and the {@link LivingEntity} who use it can play again.
	 */
	public static final int ACTION_REPLAY = -2;
	
	/* Variables */
	protected final Texture icon;
	protected int cost;
	protected CostType costType;
	protected final List<TooltipData> cachedTooltip;
	
	/* Constructor */
	public Action(Texture icon, int cost, CostType costType) {
		this.icon = icon;
		this.cost = cost;
		this.costType = costType;
		
		this.cachedTooltip = getTooltip();
	}
	
	/**
	 * Prepare the next use of this {@link Action}.
	 * 
	 * @param nextSource
	 *            Next user of this {@link Action}.
	 * @param nextTarget
	 *            Next target of this {@link Action}.
	 */
	public void prepare(LivingEntity nextSource, LivingEntity nextTarget) {
		;
	}
	
	/**
	 * Use this {@link Action}.
	 * 
	 * @param source
	 *            User of this {@link Action}.
	 * @param target
	 *            Target of this {@link Action}.
	 * @return Result of this {@link Action}.<br>
	 *         Can be {@link #ACTION_SUCCESS} or {@link #ACTION_REPLAY} of any developer-defined constant that will be handled later.
	 */
	public int use(LivingEntity source, LivingEntity target) {
		return ACTION_SUCCESS;
	}
	
	/**
	 * Check if an {@link LivingEntity} can use this {@link Action}.<br>
	 * This will check the cost and return true or false if the {@link LivingEntity} has more or less that the {@link Action}'s cost.
	 * 
	 * @param livingEntity
	 *            Target {@link LivingEntity} to test.
	 * @return If the {@link LivingEntity} can use it.
	 * @see #hasEnough(LivingEntity)
	 */
	public boolean canLivingEntityUseIt(LivingEntity livingEntity) {
		return hasEnough(livingEntity, costType, cost);
	}
	
	/**
	 * Check if a {@link LivingEntity}'s value in a domain (health, mana, ...) is geater than a cost.
	 * 
	 * @param livingEntity
	 *            Target {@link LivingEntity} to test.
	 * @param costType
	 *            Target {@link CostType}.
	 * @param cost
	 *            Target cost.
	 * @return If <code>cost <= value</code>.
	 */
	protected boolean hasEnough(LivingEntity livingEntity, CostType costType, int cost) {
		double value = 0;
		
		switch (costType) {
			case HEALTH: {
				value = livingEntity.getHealth();
				break;
			}
			case MANA: {
				value = livingEntity.getMana();
				break;
			}
			case RAGE: {
				value = livingEntity.getRage();
				break;
			}
			
			default: {
				/* NOTHING or NULL */
				return true;
			}
		}
		
		return cost <= value;
	}
	
	@Override
	public void tick(double delta) {
		;
	}
	
	public List<TooltipData> getTooltip() {
		if (hasCachedTooltip()) {
			if (cachedTooltip != null) {
				return cachedTooltip;
			}
		}
		
		return createTooltip();
	}
	
	/**
	 * @return A {@link List} containing all {@link TooltipData} to describe this {@link Action}.
	 */
	public List<TooltipData> createTooltip() {
		return new TooltipBuilder() //
				.title(getClass().getSimpleName()) //
				.description("This action don't have any custom tooltip.").color(Color.RED) //
				.build();
	}
	
	/**
	 * @return If the tooltip cache should be used.<br>
	 *         If yes, one definitive {@link #createTooltip()} call will be make and stored in cache.<br>
	 *         If not, everytime {@link #getTooltip()} will be called, this function will be too and the cache will stay null. Useful if the tooltip contains dynamic data.
	 */
	public boolean hasCachedTooltip() {
		return true;
	}
	
	/**
	 * Type of cost.
	 * 
	 * @author Enzo CACERES
	 */
	public enum CostType {
		/**
		 * That should cost nothing.<br>
		 * The cost value will be ignored.
		 */
		NOTHING,
		
		/**
		 * This will cost some health.
		 */
		HEALTH,
		
		/**
		 * This will cost some mana.
		 */
		MANA,
		
		/**
		 * This will cost some rage.
		 */
		RAGE;
	}
	
}