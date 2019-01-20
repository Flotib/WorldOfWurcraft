package game.inventory.spell;

import game.action.attack.HandAttack;
import game.action.attack.attack.BloodRageAttack;
import game.action.attack.attack.BloodyHitAttack;
import game.action.attack.attack.HeroicStrikeAttack;
import game.action.attack.attack.RendAttack;
import game.action.attack.attack.RitualOfSacrificeAttack;
import game.action.attack.other.NothingAttack;
import game.action.attack.test.EnemyLevelUpDebugAttack;
import game.action.attack.test.LevelUpDebugAttack;
import game.action.attack.test.OneShotDebugAttack;
import game.action.spell.Spell;
import game.action.spell.attack.BloodBlastSpell;
import game.action.spell.attack.CorruptionSpell;
import game.action.spell.attack.CurseOfAgonySpell;
import game.action.spell.attack.FireballSpell;
import game.action.spell.attack.ImmolationSpell;
import game.action.spell.attack.TwilightImmolationSpell;
import game.action.spell.convert.LifeTapSpell;
import game.action.spell.drain.HealDrainSpell;
import game.action.spell.drain.ManaDrainSpell;
import game.action.spell.healing.FlashHealHealingSpell;
import game.action.spell.healing.RenewHealingSpell;
import game.inventory.Inventory;
import game.item.AttackItem;
import game.item.SpellItem;

public class SpellInventory extends Inventory {
	
	public SpellInventory() {
		super(16, 4);
		
		insertDebug();
	}
	
	private void insertDebug() {
		items[0] = new SpellItem(new BloodBlastSpell());
		items[15] = new AttackItem(new OneShotDebugAttack());
		items[48] = new AttackItem(new HandAttack());
		items[49] = new AttackItem(new HeroicStrikeAttack());
		items[50] = new AttackItem(new RendAttack());
		items[51] = new AttackItem(new BloodyHitAttack());
		items[51] = new AttackItem(new RitualOfSacrificeAttack());
		items[54] = new AttackItem(new BloodRageAttack());
		items[61] = new AttackItem(new EnemyLevelUpDebugAttack());
		items[62] = new AttackItem(new LevelUpDebugAttack());
		items[63] = new AttackItem(new NothingAttack());
		
		Spell[] spells = { //
				new FireballSpell(), //
				new ImmolationSpell(), //
				new CorruptionSpell(), //
				new CurseOfAgonySpell(), //
				new HealDrainSpell(), //
				new ManaDrainSpell(), //
				new TwilightImmolationSpell(), //
				new LifeTapSpell(), //
				new RenewHealingSpell(), //
				new FlashHealHealingSpell() //
		};
		
		int offset = 0;
		for (int i = 0; i < Math.min(column, spells.length); i++) {
			items[offset + 16 + i] = new SpellItem(spells[i]);
		}
	}
	
}