package engine.texture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import caceresenzo.libs.logger.Logger;
import javafx.scene.image.Image;

/**
 * Allow differed texture loading with progress callback.
 * 
 * @author Enzo CACERES
 */
public class DifferedTextureLoader {
	
	/* Variables */
	private List<DifferedTexture> differeds;
	
	/* Constructor */
	public DifferedTextureLoader() {
		this.differeds = new ArrayList<>();
	}
	
	/**
	 * Add to the loading queue a path.<br>
	 * This queue will be loaded when you will call {@link #load(DifferedLoadingCallback)}.
	 * 
	 * @param path
	 *            Target path of the texture.
	 * @return A texture instance that has not been loaded yet.
	 */
	public DifferedTexture queue(String path) {
		DifferedTexture differedTexture = new DifferedTexture(path);
		
		differeds.add(differedTexture);
		
		return differedTexture;
	}
	
	/**
	 * Extended class of the {@link Texture} class using a null {@link Image} first to load it later.
	 * 
	 * @author Enzo CACERES
	 */
	class DifferedTexture extends Texture {
		
		/* Variables */
		private String path;
		
		/* Constructor */
		protected DifferedTexture(String path) {
			super(null);
			
			this.path = path;
		}
		
		/**
		 * Start the loading of the file.
		 * 
		 * @throws Exception
		 *             If anything went wrong.
		 */
		public void load() {
			image = new Image(path);
			TextureManager.push(path, this);
		}
		
		/**
		 * @return Texture target path.
		 */
		public String getPath() {
			return path;
		}
		
	}
	
	/**
	 * Start the loading of all enqueued texture.
	 * 
	 * @param callback
	 *            Callback to follow progress.
	 */
	public void load(DifferedLoadingCallback callback) {
		if (callback == null) {
			callback = LOGGED_CALLBACK;
		}
		
		int index = 0;
		
		Iterator<DifferedTexture> iterator = differeds.iterator();
		while (iterator.hasNext()) {
			DifferedTexture differedTexture = iterator.next();
			iterator.remove();
			
			int textureIndex = index++;
			String texturePath = differedTexture.getPath();
			
			callback.onLoadTexture(textureIndex, texturePath);
			try {
				differedTexture.load();
				
				callback.onSuccess(textureIndex, texturePath);
			} catch (Exception exception) {
				callback.onError(textureIndex, texturePath, exception);
			}
		}
	}
	
	/**
	 * Progress callback used with {@link DifferedTextureLoader#load(DifferedLoadingCallback)}.
	 * 
	 * @author Enzo CACERES
	 */
	public interface DifferedLoadingCallback {
		
		/**
		 * Called when a texture will begin to be loaded.
		 * 
		 * @param index
		 *            Target texture index.
		 * @param path
		 *            Target texture path.
		 */
		void onLoadTexture(int index, String path);
		
		/**
		 * Called if the texture a successfully loaded.
		 * 
		 * @param index
		 *            Target texture index.
		 * @param path
		 *            Target texture path.
		 */
		void onSuccess(int index, String path);
		
		/**
		 * Called if the texture a failed to load.
		 * 
		 * @param index
		 *            Target texture index.
		 * @param path
		 *            Target texture path.
		 * @param exception
		 *            Exception thrown.
		 */
		void onError(int index, String path, Exception exception);
		
	}
	
	public static final DifferedLoadingCallback LOGGED_CALLBACK = new DifferedLoadingCallback() {
		@Override
		public void onLoadTexture(int index, String path) {
			;
		}

		@Override
		public void onSuccess(int index, String path) {
			Logger.info("Loaded texture #%s with path: %s", index, path);
		}

		@Override
		public void onError(int index, String path, Exception exception) {
			Logger.info("Failed to load texture #%s with path: %s, cause: ", index, path, exception.getLocalizedMessage());
		}
	};
	
}