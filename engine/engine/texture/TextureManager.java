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
		
		return CACHE.put(path, new Texture(new Image(path)));
	}
	
	public static void push(String path, Texture texture) {
		if (CACHE.containsKey(path)) {
			throw new IllegalStateException("Can't push already loaded texture.");
		}
		
		CACHE.put(path, texture);
	}
	
}