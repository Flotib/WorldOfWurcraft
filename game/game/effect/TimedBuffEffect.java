package game.effect;

import engine.game.content.effect.TimedEffect;
import engine.texture.Texture;

public class TimedBuffEffect extends TimedEffect {
	
	public TimedBuffEffect(Texture texture, int remainingTime) {
		super(texture, remainingTime);
	}
	
}