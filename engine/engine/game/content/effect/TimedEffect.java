package engine.game.content.effect;

import engine.texture.Texture;

public class TimedEffect extends Effect {
	
	/* Variables */
	protected int remainingTime;
	
	/* Constructor */
	public TimedEffect(Texture texture, int remainingTime) {
		super(texture);
		
		this.remainingTime = remainingTime;
	}
	
	/**
	 * Call this function after you code in the {@link #execute(engine.entity.LivingEntity)} function.<br>
	 * This will check remaining time and end it if there are no time left.
	 */
	public void finishExecute() {
		remainingTime--;
		
		if (remainingTime <= 0) {
			endEffect();
		}
	}
	
}