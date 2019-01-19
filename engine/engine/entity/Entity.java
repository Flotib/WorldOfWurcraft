package engine.entity;

import engine.game.GameObject;

public class Entity extends GameObject {
	
	/* Variables */
	protected double health, maxHealth;
	protected final double baseHealth;
	
	/* Constructor */
	public Entity() {
		this(0);
	}
	
	/* Constructor */
	public Entity(double health) {
		this(health, 0, 0);
	}
	
	/* Constructor */
	public Entity(double health, double x, double y) {
		this(health, x, y, 0, 0);
	}
	
	/* Constructor */
	public Entity(double health, double x, double y, double width, double height) {
		super(x, y, width, height);
		
		this.health = maxHealth = baseHealth = health;
	}
	
	/**
	 * Offset this {@link Entity}'s health value.
	 * 
	 * @param offset
	 *            Offset value.
	 */
	public void offsetHealth(double offset) {
		double newHealth = health + offset;
		
		if (newHealth > maxHealth) {
			newHealth = maxHealth;
		}
		
		setHealth(newHealth);
	}
	
	/**
	 * @return The inverse of {@link #isAlive()}.
	 */
	public boolean isDead() {
		return !isAlive();
	}
	
	/**
	 * @return True if the {@link Entity} has more than zero health.
	 */
	public boolean isAlive() {
		return health > 0;
	}
	
	/**
	 * If the {@link Entity} is alive, set his health to 0.
	 */
	public void kill() {
		if (isAlive()) {
			setHealth(0);
		}
	}
	
	/**
	 * @return {@link Entity}'s health.
	 */
	public double getHealth() {
		return health;
	}
	
	/**
	 * Set a new health value for this {@link Entity}.
	 * 
	 * @param health
	 *            New health value.
	 */
	public void setHealth(double health) {
		this.health = health;
	}
	
	/**
	 * @return Max health value, set when object is created or if internal modification changed it.
	 */
	public double getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * @return Base health value, set when object is created.
	 */
	public double getBaseHealth() {
		return baseHealth;
	}
	
}