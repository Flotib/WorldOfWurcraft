package engine.game.content.effect;

import engine.entity.LivingEntity;
import engine.texture.Texture;

public class Effect {
	
	/* Variables */
	private final Texture texture;
	private boolean ended;
	private @Deprecated Effect previousEffect;
	
	/* Constructor */
	public Effect(Texture texture) {
		this.texture = texture;
	}
	
	/**
	 * Attach the previous effect to this new effect.<br>
	 * If an {@link LivingEntity} already have this effect, attaching the old one to the new old will allow the new effect to get value from the old one. And you can maybe set the new one stronger because you was already having it.
	 * 
	 * @param effect
	 *            Old {@link Effect}
	 * @return Itself
	 */
	public Effect attachPreviousEffect(Effect effect) {
		this.previousEffect = effect;
		onPreviousEffectAttached(effect);
		return this;
	}
	
	/**
	 * Called when the previous {@link Effect} has been attached.
	 * 
	 * @param previousEffect
	 *            Old effect
	 * @see #attachPreviousEffect(Effect)
	 */
	public void onPreviousEffectAttached(Effect previousEffect) {
		;
	}
	
	/**
	 * Execute the effect on a {@link LivingEntity}.
	 * 
	 * @param livingEntity
	 *            Target {@link LivingEntity}.
	 * @return True if the {@link LivingEntity} can do action after, and false if the {@link LivingEntity} should not play.
	 */
	public boolean execute(LivingEntity livingEntity) {
		return true;
	}
	
	/**
	 * End this effect quickly.
	 */
	public void endEffect() {
		this.ended = true;
	}
	
	/**
	 * @return Texture icon that is supposed to represent this {@link Effect}.
	 */
	public Texture getTexture() {
		return texture;
	}
	
	/**
	 * @return If this has ended and should be remove from its host.
	 */
	public boolean isEnded() {
		return ended;
	}
	
	/**
	 * @deprecated This function is not supposed to stay, please do not use it.
	 * @return The attached previous effect.
	 */
	@Deprecated
	public Effect getPreviousEffect() {
		return previousEffect;
	}
	
}