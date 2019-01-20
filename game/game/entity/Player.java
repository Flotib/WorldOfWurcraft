package game.entity;

import engine.entity.LivingEntity;
import engine.texture.Texture;

public class Player extends LivingEntity {
	
	private Texture texture;
	private boolean canPlay, hasPlay;
	private LivingEntity target;
	
	public Player(String name, double health, double mana, double rage, int level, Texture texture, double x, double y, double width, double height) {
		super(name, health, mana, rage, 0, level, x, y, width, height);
		
		this.texture = texture;
	}
	
	@Override
	public void onLevelChanged() {
		maxHealth = baseHealth * level;
		maxMana = baseMana * level;
		
		setHealth(maxHealth);
		setMana(maxMana);
		
		int difference = 0;
		if (level <= 28) {
			difference = 0;
		} else if (level == 29) {
			difference = 1;
		} else if (level == 30) {
			difference = 3;
		} else if (level == 31) {
			difference = 6;
		} else if (level >= 32) {
			difference = 5 * (level - 30);
		}
		
		maxExperience = ((8 * level) + difference) * ((level * 5) + 45);
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public boolean canPlay() {
		return canPlay;
	}

	public void setCanPlay(boolean canPlay) {
		this.canPlay = canPlay;
	}

	public boolean isHasPlay() {
		return hasPlay;
	}

	public void setHasPlay(boolean hasPlay) {
		this.hasPlay = hasPlay;
	}

	public LivingEntity getTarget() {
		return target;
	}

	public void setTarget(LivingEntity target) {
		this.target = target;
	}
	
}