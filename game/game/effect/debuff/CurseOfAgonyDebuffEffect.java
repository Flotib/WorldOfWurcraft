package game.effect.debuff;

import engine.entity.LivingEntity;
import engine.game.content.effect.Effect;
import game.FlotibGame;
import game.action.spell.attack.CurseOfAgonySpell;
import game.effect.TimedDebuffEffect;

public class CurseOfAgonyDebuffEffect extends TimedDebuffEffect {
	
	/* Variables */
	private int curseStackDamage, curseStackMinDamage, curseStackMaxDamage;
	private int stackNumber;
	
	/* Constructor */
	public CurseOfAgonyDebuffEffect(CurseOfAgonySpell curseOfAgonySpell) {
		super(FlotibGame.TEXTURE_TEST, 24);
		
		this.curseStackDamage = curseOfAgonySpell.getDebuffMinDamage();
		this.curseStackMinDamage = curseStackDamage - 4;
		this.curseStackMaxDamage = curseStackDamage + 4;
		this.stackNumber = 0;
	}
	
	@Override
	public void onPreviousEffectAttached(Effect previousEffect) {
		int add = 1;
		if (stackNumber >= 12) {
			add = 0;
		}
		
		stackNumber = ((CurseOfAgonyDebuffEffect) previousEffect).stackNumber + add;
	}
	
	@Override
	public boolean execute(LivingEntity livingEntity) {
		if (remainingTime % 2 == 1) {
			if (stackNumber < 4) {
				stackNumber++;
				livingEntity.offsetHealth(-curseStackMinDamage);
			} else if (stackNumber >= 4 && stackNumber < 8) {
				stackNumber++;
				livingEntity.offsetHealth(-curseStackDamage);
			} else if (stackNumber >= 8) {
				livingEntity.offsetHealth(-curseStackMaxDamage);
			}
		}
		
		finishExecute();
		return true;
	}
	
}