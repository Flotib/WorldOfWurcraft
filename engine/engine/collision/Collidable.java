package engine.collision;

public interface Collidable<T> {
	
	public boolean collide(T other);
	
}