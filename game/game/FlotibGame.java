package game;

import engine.GameEngine;
import engine.texture.DifferedTextureLoader;
import engine.texture.Texture;
import engine.ui.UILayer;
import engine.ui.components.implementations.TextComponent;
import game.entity.Player;
import game.inventory.Inventory;
import game.inventory.spell.SpellInventory;
import game.ui.inventory.InventoryComponent;
import javafx.scene.canvas.GraphicsContext;

public class FlotibGame extends GameEngine {
	
	/* Textures */
	public static final DifferedTextureLoader TEXTURE_LOADER = new DifferedTextureLoader();
	
	public static final Texture TEXTURE_TEST = TEXTURE_LOADER.queue("test/test-image.jpg");
	public static final Texture TEXTURE_ICON_TEST = TEXTURE_LOADER.queue("assets/icons/icon-test.png");
	
	public static final Texture TEXTURE_ICON_ABILITY_BASEATTACK = TEXTURE_LOADER.queue("assets/icons/abilities/ability-base-attack.png");
	public static final Texture TEXTURE_ICON_ABILITY_REND = TEXTURE_LOADER.queue("assets/icons/abilities/ability-rend.png");
	public static final Texture TEXTURE_ICON_ABILITY_HEROICSTRIKE = TEXTURE_LOADER.queue("assets/icons/abilities/ability-heroic-strike.png");
	public static final Texture TEXTURE_ICON_ABILITY_BLOODRAGE = TEXTURE_LOADER.queue("assets/icons/abilities/ability-blood-rage.png");
	public static final Texture TEXTURE_ICON_ABILITY_BLOODYHIT = TEXTURE_LOADER.queue("assets/icons/abilities/ability-bloody-hit.png");
	
	public static final Texture TEXTURE_ICON_SPELL_NOTHING = TEXTURE_LOADER.queue("assets/icons/spells/spell-nothing.png");
	public static final Texture TEXTURE_ICON_SPELL_LIFEDRAIN = TEXTURE_LOADER.queue("assets/icons/spells/spell-life-drain.png");
	public static final Texture TEXTURE_ICON_SPELL_FIREBALL = TEXTURE_LOADER.queue("assets/icons/spells/spell-fireball.png");
	public static final Texture TEXTURE_ICON_SPELL_CORRUPTION = TEXTURE_LOADER.queue("assets/icons/spells/spell-corruption.png");
	public static final Texture TEXTURE_ICON_SPELL_MANADRAIN = TEXTURE_LOADER.queue("assets/icons/spells/spell-mana-drain.png");
	public static final Texture TEXTURE_ICON_SPELL_LIFETAP = TEXTURE_LOADER.queue("assets/icons/spells/spell-life-tap.png");
	public static final Texture TEXTURE_ICON_SPELL_RENEW = TEXTURE_LOADER.queue("assets/icons/spells/spell-renew.png");
	public static final Texture TEXTURE_ICON_SPELL_IMMOLATION = TEXTURE_LOADER.queue("assets/icons/spells/spell-immolation.png");
	public static final Texture TEXTURE_ICON_SPELL_CURSEOFAGONY = TEXTURE_LOADER.queue("assets/icons/spells/spell-curse-of-agony.png");
	public static final Texture TEXTURE_ICON_SPELL_FLASHHEAL = TEXTURE_LOADER.queue("assets/icons/spells/spell-flash-heal.png");
	public static final Texture TEXTURE_ICON_SPELL_TWILIGHTIMMOLATION = TEXTURE_LOADER.queue("assets/icons/spells/spell-twilight-immolation.png");
	public static final Texture TEXTURE_ICON_SPELL_RITUALOFSACRIFICE = TEXTURE_LOADER.queue("assets/icons/spells/spell-ritual-of-sacrifice.png");
	public static final Texture TEXTURE_ICON_SPELL_BLOODBLAST = TEXTURE_LOADER.queue("assets/icons/spells/spell-blood-blast.png");
	
	public static final Texture TEXTURE_ICON_EFFECT_BUFF_RENEW = TEXTURE_LOADER.queue("assets/icons/effects/effect-buff-renew.png");
	public static final Texture TEXTURE_ICON_EFFECT_BUFF_BLOODRAGE = TEXTURE_LOADER.queue("assets/icons/effects/effect-buff-blood-rage.png");
	
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_BURNING = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-burning.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_REND = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-rend.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_CORRUPTION = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-corruption.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_BLEEDING = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-bleeding.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_IMMOLATION = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-immolation.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_CURSEOFAGONY = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-curse-of-agony.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_TWILIGHTIMMOLATION = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-twilight-immolation.png");
	
	public static final Texture TEXTURE_INVENTORY_ITEM_HIGHLIGHT = TEXTURE_LOADER.queue("assets/inventory/highlight/item-highlight.png");
	public static final Texture TEXTURE_INVENTORY_ITEM_HIGHLIGHT_TRANSPARENCY = TEXTURE_LOADER.queue("assets/inventory/highlight/item-highlight-transparency.png");
	
	public static final Texture TEXTURE_INVENTORY_MASK_DISABLED = TEXTURE_LOADER.queue("assets/inventory/mask/mask-disabled.png");
	public static final Texture TEXTURE_INVENTORY_MASK_HOLD_CLICK = TEXTURE_LOADER.queue("assets/inventory/mask/mask-hold-click.png");
	
	public static final Texture TEXTURE_INVENTORY_HOLDER = TEXTURE_LOADER.queue("assets/inventory/holder/holder.png");
	public static final Texture TEXTURE_INVENTORY_HOLDER_BORDER = TEXTURE_LOADER.queue("assets/inventory/holder/holder-border.png");
	
	/* Instances */
	private static FlotibGame GAME;
	
	/* Game */
	private Player player;
	
	/* Views */
	private TextComponent playerStatisticsTextComponent;
	
	@Override
	protected void initialize() {
		super.initialize();
		debugging(true);
		
		/* Engine */
		GAME = this;
		
		/* Texture */
		TEXTURE_LOADER.load(null);
		
		/* UI */
		UILayer mainLayer = uiManager.createLayer(0);
		
		Inventory inventory = new SpellInventory();
		InventoryComponent inventoryComponent = new InventoryComponent(inventory, 500, 500);
		// inventoryComponent.setRenderMode(UIComponent.RENDER_DEBUG);
		
		mainLayer.add(playerStatisticsTextComponent = new TextComponent(null, 50, 50));
		mainLayer.add(inventoryComponent);
		
		/* Game */
		player = new Player("Hello", 100, 100, 0, 1, TEXTURE_TEST, 0, 0, 0, 0);
	}
	
	@Override
	public void tick(double delta) {
		super.tick(delta);
		
		playerStatisticsTextComponent.setText(String.format("player - health: %s - mana: %s - rage: %s", player.getHealth(), player.getMana(), player.getRage()));
		
		if (isDebugging()) {
			player.setTarget(player);
			player.setCanPlay(true);
		}
	}
	
	@Override
	public void render(double delta) {
		super.render(delta);
		
		double scale = world.getViewport().getScale();
		double translateX = world.getViewport().getTranslateX();
		double translateY = world.getViewport().getTranslateY();
		
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		
		graphics.save();
		graphics.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
		graphics.scale(1, -1);
		
		graphics.scale(scale, scale);
		graphics.translate(translateX, translateY);
		
		// renderGrid();
		
		graphics.restore();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public static FlotibGame getGame() {
		return GAME;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}