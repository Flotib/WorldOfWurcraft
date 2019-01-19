package game.effect;

import engine.game.content.effect.TimedEffect;
import engine.texture.Texture;

public class TimedDebuffEffect extends TimedEffect {
	
	public TimedDebuffEffect(Texture texture, int remainingTime) {
		super(texture, remainingTime);
	}
	
}