package CenturionAndMystic.cardmods;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.powers.VenomPower;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class VenomDamageModifier extends AbstractDamageModifier {
    public static final String ID = CentAndMysMod.makeID("VenomDamageModifier");


    private final boolean inherent;

    public VenomDamageModifier(boolean inherent) {
        this.inherent = inherent;
    }

    @Override
    public void onLastDamageTakenUpdate(DamageInfo info, int lastDamageTaken, int overkillAmount, AbstractCreature targetHit) {
        if (lastDamageTaken > 0) {
            this.addToBot(new ApplyPowerAction(targetHit, AbstractDungeon.player,new VenomPower(targetHit, lastDamageTaken)));
        }
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new DrainDamageModifier(inherent);
    }

    @Override
    public boolean isInherent() {
        return inherent;
    }
}
