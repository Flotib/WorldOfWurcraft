package engine.texture;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class TextureManager {
	
	public static final Map<String, Texture> CACHE;
	
	static {
		CACHE = new HashMap<>();
	}
	
	private TextureManager() {
		;
	}
	
	public static Texture load(String path) {
		if (CACHE.containsKey(path)) {
			return CACHE.get(path);
		}
		
		Texture texture = new Texture(new Image(path));
		CACHE.put(path, texture);
		
		return texture;
	}
	
	public static void push(String path, Texture texture) {
		if (CACHE.containsKey(path)) {
			throw new IllegalStateException("Can't push already loaded texture.");
		}
		
		CACHE.put(path, texture);
	}
	
}