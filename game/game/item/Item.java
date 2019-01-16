package game.item;

import engine.render.Renderable;
import engine.texture.Texture;
import javafx.scene.canvas.GraphicsContext;

public class Item implements Renderable {
	
	protected String name;
	protected Texture texture;
	
	public Item(String name, Texture texture) {
		this.name = name;
		this.texture = texture;
	}
	
	@Override
	public void render(GraphicsContext graphics) {
		
	}
	
}