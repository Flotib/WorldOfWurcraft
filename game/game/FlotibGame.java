package game;

import engine.GameEngine;
import engine.texture.DifferedTextureLoader;
import engine.texture.Texture;

public class FlotibGame extends GameEngine {
	
	public static final DifferedTextureLoader TEXTURE_LOADER = new DifferedTextureLoader();

	public static final Texture TEXTURE_TEST = TEXTURE_LOADER.queue("test/test-image.jpg");
	public static final Texture TEXTURE_TEST2 = TEXTURE_LOADER.queue("test/MainMenuBar.PNG");
	
	@Override
	protected void initialize() {
		super.initialize();
		
		TEXTURE_LOADER.load(null);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}