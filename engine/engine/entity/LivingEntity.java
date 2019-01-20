package engine.entity;

import java.util.ArrayList;
import java.util.List;

import engine.game.content.effect.Effect;

public class LivingEntity extends Entity {
	
	/* Constants */
	public static final double RAGE_MAX = 100D;
	public static final double DEFAULT_EXPERIENCE_MULTIPLIER = 1.2D;
	
	/* Variables */
	protected String name;
	protected double mana, maxMana;
	protected final double baseMana;
	protected double rage;
	protected double experience, maxExperience;
	protected int level;
	protected List<Effect> effects;
	
	/* Constructor */
	public LivingEntity(String name, double health, double mana, double rage, double experience, int level) {
		this(name, health, mana, rage, experience, level, 0, 0, 0, 0);
	}
	
	/* Constructor */
	public LivingEntity(String name, double health, double mana, double rage, double experience, int level, double x, double y) {
		this(name, health, mana, rage, experience, level, x, y, 0, 0);
	}
	
	/* Constructor */
	public LivingEntity(String name, double health, double mana, double rage, double experience, int level, double x, double y, double width, double height) {
		super(health, x, y, width, height);
		
		this.name = name;
		this.mana = maxMana = baseMana = mana;
		this.rage = rage;
		this.experience = maxExperience = experience;
		this.level = level;
		
		this.effects = new ArrayList<>();
	}
	
	/**
	 * Will call {@link #levelUp(double)} with a increase of the max experience value of 20% (1.20) (using {@link #DEFAULT_EXPERIENCE_MULTIPLIER}).
	 * 
	 * @see #levelUp(double) Leveling up with custom multiplier.
	 */
	public void levelUp() {
		levelUp(DEFAULT_EXPERIENCE_MULTIPLIER);
	}
	
	/**
	 * Level up this {@link LivingEntity}.<br>
	 * Once done, this function will call event {@link #onLevelChanged()}.
	 * 
	 * @param nextMaxExperienceMultiplier
	 *            Pourcentage multiplier required to set the {@link #maxExperience} value for the next level.<br>
	 *            Exemple: <code>1.20</code> will correspond to an increase of 20% from the previous value.
	 */
	public void levelUp(double nextMaxExperienceMultiplier) {
		level += 1;
		experience -= maxExperience;
		maxExperience *= nextMaxExperienceMultiplier;
		
		onLevelChanged();
	}
	
	/**
	 * Event function.<br>
	 * Should be called when the level up fonction has been called.
	 */
	public void onLevelChanged() {
		;
	}
	
	/**
	 * Check the experience and level up if the max experience has been reached.<br>
	 * This will use the {@link #DEFAULT_EXPERIENCE_MULTIPLIER} as level multiplier.
	 * 
	 * @see #checkLevel(double)
	 */
	public void checkLevel() {
		checkLevel(DEFAULT_EXPERIENCE_MULTIPLIER);
	}
	
	/**
	 * Check the experience and level up if the max experience has been reached and use a level multiplier to set the next max experience value.<br>
	 * This function will call herself recursively to get as much level as possible.
	 * 
	 * @param nextMaxExperienceMultiplier
	 *            Next max experience multiplier.
	 * @see #levelUp(double)
	 */
	public void checkLevel(double nextMaxExperienceMultiplier) {
		if (experience > maxExperience) {
			levelUp(nextMaxExperienceMultiplier);
			checkLevel();
		}
	}
	
	/**
	 * Offset this {@link Entity}'s mana value.
	 * 
	 * @param offset
	 *            Offset value.
	 */
	public void offsetMana(double offset) {
		double newMana = health + offset;
		
		if (newMana > maxMana) {
			newMana = maxMana;
		}
		
		setMana(newMana);
	}
	
	/**
	 * Offset this {@link LivingEntity}'s rage value.
	 * 
	 * @param offset
	 *            Offset value.
	 */
	public void offsetRage(double offset) {
		double newRage = health + offset;
		
		if (newRage > getRageMax()) {
			newRage = getRageMax();
		}
		
		setRage(newRage);
	}
	
	public void giveEffect(Effect effect) {
		if (effect != null) {
			Effect oldEffect = getEffectByClass(effect.getClass());
			
			if (oldEffect != null) {
				effect.attachPreviousEffect(effect);
				cancelEffect(oldEffect);
			}
			
			effects.add(effect);
		}
	}
	
	public Effect getEffectByClass(Class<? extends Effect> effectClass) {
		if (effectClass != null) {
			for (Effect effect : effects) {
				if (effect.getClass().equals(effectClass)) {
					return effect;
				}
			}
		}
		
		return null;
	}
	
	public void cancelEffect(Effect effect) {
		if (effects.contains(effect)) {
			effects.remove(effect);
		}
	}
	
	/**
	 * @return {@link LivingEntity}'s name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set a new name for this {@link LivingEntity}.
	 * 
	 * @param name
	 *            New name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return @return {@link LivingEntity}'s rage value.
	 */
	public double getRage() {
		return rage;
	}
	
	public void setRage(double rage) {
		this.rage = rage;
	}
	
	/**
	 * @return @return {@link LivingEntity}'s experience.
	 */
	public double getExperience() {
		return experience;
	}
	
	/**
	 * Give some experience for this {@link LivingEntity}.
	 * 
	 * @param amount
	 *            Given experience amount.
	 */
	public void giveExperience(double amount) {
		this.experience += amount;
		
		checkLevel();
	}
	
	/**
	 * @return @return {@link LivingEntity}'s actual level.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Set a new level for this {@link LivingEntity}.<br>
	 * Will also call {@link #onLevelChanged()}.
	 * 
	 * @param level
	 *            New level value.
	 */
	public void setLevel(int level) {
		this.level = level;
		
		onLevelChanged();
	}
	
	/**
	 * @return @return {@link LivingEntity}'s mana value.
	 */
	public double getMana() {
		return mana;
	}
	
	/**
	 * Set a new mana value for this {@link LivingEntity}.
	 * 
	 * @param mana
	 *            New mana value.
	 */
	public void setMana(double mana) {
		this.mana = mana;
	}
	
	/**
	 * @return Max mana value, set when object is created or if internal modification changed it.
	 */
	public double getMaxMana() {
		return maxMana;
	}
	
	/**
	 * @return Max mana value, set when object is created.
	 */
	public double getMaxExperience() {
		return maxExperience;
	}
	
	/**
	 * @return If not overrided, the constant {@link #RAGE_MAX}, but an entity can extends this value.
	 */
	public static double getRageMax() {
		return RAGE_MAX;
	}
	
}