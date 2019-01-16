package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import caceresenzo.libs.logger.Logger;
import engine.GameEngine;
import engine.texture.Texture;
import engine.texture.TextureManager;
import javafx.scene.image.Image;

public class FlotibGame extends GameEngine {
	
	private static List<TextureDelegate> textureToLoads = new ArrayList<>();

	public static final Texture TEXTURE_TEST = new TextureDelegate("assets/test.png");
	public static final Texture TEXTURE_TEST2 = new TextureDelegate("assets/test.png2");
	
	@Override
	protected void initialize() {
		super.initialize();
		
		int number = 0;
		Iterator<TextureDelegate> iterator = textureToLoads.iterator();
		while (iterator.hasNext()) {
			TextureDelegate textureDelegate = iterator.next();
			
			Logger.info("Loading texture #%s with path: %s", number++, textureDelegate.path);
		}
	}
	
	
	static class TextureDelegate extends Texture {
		
		private String path;

		protected TextureDelegate(String path) {
			super(null);
			
			this.path = path;
			
			textureToLoads.add(this);
		}
		
		public void load() {
			image = new Image(path);
			TextureManager.push(path, this);
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}