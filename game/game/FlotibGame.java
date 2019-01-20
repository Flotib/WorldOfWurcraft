package game;

import caceresenzo.libs.logger.Logger;
import engine.GameEngine;
import engine.texture.DifferedTextureLoader;
import engine.texture.Texture;
import engine.ui.UILayer;
import engine.ui.components.UIComponent;
import engine.ui.components.implementations.TextComponent;
import game.inventory.Inventory;
import game.inventory.spell.SpellInventory;
import game.ui.inventory.InventoryComponent;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FlotibGame extends GameEngine {
	
	public static final DifferedTextureLoader TEXTURE_LOADER = new DifferedTextureLoader();
	
	public static final Texture TEXTURE_TEST = TEXTURE_LOADER.queue("test/test-image.jpg");
	
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
	
	public static final Texture TEXTURE_INVENTORY_DISABLED_ITEM_MASK = TEXTURE_LOADER.queue("assets/inventory/mask/disabled_mask.png");
	
	public static final Texture TEXTURE_ICON_EFFECT_BUFF_RENEW = TEXTURE_LOADER.queue("assets/icons/effects/effect-buff-renew.png");
	public static final Texture TEXTURE_ICON_EFFECT_BUFF_BLOODRAGE = TEXTURE_LOADER.queue("assets/icons/effects/effect-buff-blood-rage.png");
	
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_BURNING = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-burning.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_REND = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-rend.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_CORRUPTION = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-corruption.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_BLEEDING = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-bleeding.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_IMMOLATION = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-immolation.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_CURSEOFAGONY = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-curse-of-agony.png");
	public static final Texture TEXTURE_ICON_EFFECT_DEBUFF_TWILIGHTIMMOLATION = TEXTURE_LOADER.queue("assets/icons/effects/effect-debuff-twilight-immolation.png");
	
	private TextComponent mouseTextComponent;
	private Point2D savedPosition;
	
	@Override
	protected void initialize() {
		super.initialize();
		
		TEXTURE_LOADER.load(null);
		
		UILayer mainLayer = uiManager.createLayer(0);
		
		Inventory inventory = new SpellInventory();
		InventoryComponent inventoryComponent = new InventoryComponent(inventory, 500, 500);
		inventoryComponent.setRenderMode(UIComponent.RENDER_DEBUG);
		
		mouseTextComponent = new TextComponent(null);
		mouseTextComponent.setFont(Font.font("Arial", 18));
		
		mainLayer.add(mouseTextComponent);
		mainLayer.add(inventoryComponent);
	}
	
	@Override
	public void onMouseMovedEvent(MouseEvent mouseEvent) {
		super.onMouseMovedEvent(mouseEvent);
		
		mouseTextComponent.setColor(Color.BLACK);
		mouseTextComponent.setText(String.format("x:%s,y:%s", mouseEvent.getX(), mouseEvent.getY()));
		mouseTextComponent.setPosition(savedPosition = new Point2D(mouseEvent.getX(), -mouseEvent.getY()));
		Logger.info(String.format("x:%s,y:%s", mouseEvent.getX(), mouseEvent.getY()));
	}
	
	@Override
	public void onMouseDraggedEvent(MouseEvent mouseEvent) {
		super.onMouseDraggedEvent(mouseEvent);
		
		mouseTextComponent.setColor(Color.RED);
		mouseTextComponent.setText(String.format("-> x:%s,y:%s", mouseEvent.getX() - savedPosition.getX(), mouseEvent.getY() - -savedPosition.getY()));
		mouseTextComponent.setPosition(new Point2D(mouseEvent.getX(), -mouseEvent.getY()));
	}
	
	@Override
	public void render(double delta) {
		double scale = world.getViewport().getScale();
		double translateX = world.getViewport().getTranslateX();
		double translateY = world.getViewport().getTranslateY();
		
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		
		graphics.setFill(Color.rgb(255, 255, 255));
		graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		graphics.save();
		graphics.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
		graphics.scale(1, -1);
		
		graphics.scale(scale, scale);
		graphics.translate(translateX, translateY);
		
		// renderGrid();
		
		graphics.restore();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}