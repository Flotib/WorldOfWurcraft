package engine.texture;

import javafx.scene.image.Image;

public class Texture {
	
	/* Variables */
	protected Image image;
	
	/* Constructor */
	protected Texture(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return image;
	}
	
}