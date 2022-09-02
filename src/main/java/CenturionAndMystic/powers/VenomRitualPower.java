package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VenomRitualPower extends AbstractEasyPower {
    public static final String ID  = CentAndMysMod.makeID("VenomRitualPower");
    private boolean upgraded;
    public static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);
    private static int idOffset = 0;

    public VenomRitualPower(boolean upgraded) {
        super(ID + idOffset, strings.NAME, PowerType.BUFF, true, AbstractDungeon.player, 0);
        idOffset++;
    }

    @Override
    public void updateDescription() {
        if (upgraded) {
            description = strings.DESCRIPTIONS[1];
        } else {
            description = strings.DESCRIPTIONS[0];
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {// 34
            addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
            if (upgraded) {
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                    if (m.hasPower(VenomPower.ID)) {
                        addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, m.getPower(VenomPower.ID).amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON, false));
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
                    addToBot(new DamageAction(target, new DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON, false));
                }
            }
        }
    }
}
