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
	
	public Effect attachPreviousEffect(Effect effect) {
		this.previousEffect = effect;
		onPreviousEffectAttached(effect);
		return this;
	}
	
	public void onPreviousEffectAttached(Effect previousEffect) {
		;
	}
	
	public void execute(LivingEntity livingEntity) {
		;
	}
	
	public void endEffect() {
		this.ended = true;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public boolean isEnded() {
		return ended;
	}
	
	@Deprecated
	public Effect getPreviousEffect() {
		return previousEffect;
	}
	
}