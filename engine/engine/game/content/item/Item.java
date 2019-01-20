package engine.game.content.item;

import engine.render.Renderable;
import engine.texture.Texture;
import javafx.scene.canvas.GraphicsContext;

public abstract class Item implements Renderable {
	
	/* Variable */
	protected String name;
	protected Texture texture;
	
	/* Constructor */
	public Item(String name, Texture texture) {
		this.name = name;
		this.texture = texture;
	}
	
	@Override
	public void render(GraphicsContext graphics) {
		graphics.save();
		
		graphics.drawImage(texture.getImage(), 0, 0);
		
		graphics.restore();
	}
	
	/**
	 * @return Item's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return Item's texture.
	 */
	public Texture getTexture() {
		return texture;
	}
	
}