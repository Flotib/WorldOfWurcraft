package engine.texture;

public class DifferedTextureLoader {
	
	private DifferedTextureLoader() {
		;
	}
	
	
	interface DifferedLoadingCallback {
		
		void onLoadTexture(String path, int index);
		
	}
	
}