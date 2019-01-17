package engine.entity;

public class LivingEntity extends Entity {
	
	/* Constants */
	public static final double RAGE_MAX = 100D;
	
	/* Variables */
	protected String name;
	protected double mana, maxMana;
	protected final double baseMana;
	protected double rage;
	protected double experience, maxExperience;
	protected int level;
	
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
	}
	
	/**
	 * Will call {@link #levelUp(double)} with a increase of the max experience value of 20% (1.20).
	 * 
	 * @see #levelUp(double) Leveling up with custom multiplier.
	 */
	public void levelUp() {
		levelUp(1.20);
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
		experience = 0;
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
	 * Set a new experience value for this {@link LivingEntity}.
	 * 
	 * @param experience
	 *            New mana experience.
	 */
	public void setExperience(double experience) {
		this.experience = experience;
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