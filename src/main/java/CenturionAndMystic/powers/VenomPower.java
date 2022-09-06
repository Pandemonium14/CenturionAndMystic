package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static java.lang.Math.floor;

public class VenomPower extends AbstractEasyPower {

    public static final String ID = CentAndMysMod.makeID("VenomPower");
    public static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);

    private static final int DECREASE = 5;

    public VenomPower(AbstractCreature owner, int amount) {
        super(ID, strings.NAME, PowerType.DEBUFF,false,owner,amount);
        priority = -2;
    }

    @Override
    public void updateDescription() {
        description = strings.DESCRIPTIONS[0];
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL) {
            if (damageAmount >= amount) {
                addToTop(new DamageAction(owner, new DamageInfo(owner,amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
                addToTop(new RemoveSpecificPowerAction(owner, owner, this));
            } else {
                addToTop(new DamageAction(owner, new DamageInfo(owner, damageAmount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
                addToTop(new ReducePowerAction(owner, owner, ID, damageAmount));
            }
        }
        return damageAmount;
    }
}
