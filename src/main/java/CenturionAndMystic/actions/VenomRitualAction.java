package CenturionAndMystic.actions;

import CenturionAndMystic.cardmods.DrainDamageModifier;
import CenturionAndMystic.cards.mystic.VenomInfusion;
import CenturionAndMystic.powers.VenomPower;
import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VenomRitualAction extends AbstractGameAction {

    private boolean upgraded;
    private DamageModContainer container;

    public VenomRitualAction(boolean up) {
        upgraded = up;
        container = new DamageModContainer(this, new DrainDamageModifier(false));
    }

    @Override
    public void update() {
        if (upgraded) {
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (m.hasPower(VenomPower.ID)) {
                    addToTop(new DamageAction(m, BindingHelper.makeInfo(container, AbstractDungeon.player,
                                                                                  m.getPower(VenomPower.ID).amount,
                                                                                  DamageInfo.DamageType.THORNS),
                                                                                  AbstractGameAction.AttackEffect.POISON,
                                                                                  false));
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
                addToTop(new DamageAction(target, BindingHelper.makeInfo(container, AbstractDungeon.player,
                        target.getPower(VenomPower.ID).amount,
                        DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.POISON,
                        false));
            }
        }
        isDone = true;
    }
}
