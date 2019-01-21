package game.item;

import caceresenzo.libs.logger.Logger;
import engine.entity.LivingEntity;
import engine.game.content.item.GameItem;
import game.action.Action;
import game.action.spell.Spell;
import game.entity.Player;

public class SpellItem extends GameItem {
	
	/* Variables */
	private Spell spell;
	private int roundPlay;
	
	/* Constructor */
	public SpellItem(Spell spell) {
		super(spell.getClass().getSimpleName(), spell.getIcon());
		
		this.spell = spell;
	}
	
	@Override
	public void use(LivingEntity livingEntity) {
		if (!spell.canLivingEntityUseIt(livingEntity)) {
			return;
		}
		
		if (livingEntity instanceof Player) {
			Player player = (Player) livingEntity;
			
			if (player.canPlay() && player.getTarget() != null) {
				int actionResult = spell.use(player, player.getTarget());
				
				switch (actionResult) {
					case Action.ACTION_SUCCESS: {
						roundPlay = 0;
						player.setHasPlay(true);
						break;
					}
					
					case Action.ACTION_REPLAY: {
						if (++roundPlay < 2) {
							player.setHasPlay(false);
						} else {
							player.setHasPlay(true);
							roundPlay = 0;
						}
						
						player.setHasPlay(true);
						break;
					}
					
					default: {
						Logger.warning("Unhandled result code: " + actionResult);
						player.setHasPlay(true);
						break;
					}
				}
			}
		}
	}
	
	public Spell getSpell() {
		return spell;
	}
	
}