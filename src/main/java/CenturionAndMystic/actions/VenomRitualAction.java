package CenturionAndMystic.actions;

import CenturionAndMystic.cards.mystic.VenomInfusion;
import CenturionAndMystic.powers.VenomPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VenomRitualAction extends AbstractGameAction {

    private boolean upgraded;

    public VenomRitualAction(boolean up) {
        upgraded = up;
    }

    @Override
    public void update() {
        if (upgraded) {
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (m.hasPower(VenomPower.ID)) {
                    addToTop(new DamageAction(m, new DamageInfo(AbstractDungeon.player, m.getPower(VenomPower.ID).amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON, false));
                }
            }
        } else {
            AbstractMonster target = null;
            int amount = -1;
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (m.hasPower(VenomPower.ID) && m.getPower(VenomPower.ID).amount > amount) {
                    target = m;
                    amount = m.getPower(VenomPower.ID).amount;
                }
            }
            if (target != null && amount > 0) {
                addToTop(new DamageAction(target, new DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON, false));
            }
        }
        isDone = true;
    }
}
