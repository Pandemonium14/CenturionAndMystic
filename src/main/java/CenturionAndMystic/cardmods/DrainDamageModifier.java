package CenturionAndMystic.cardmods;

import CenturionAndMystic.CentAndMysMod;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DrainDamageModifier extends AbstractDamageModifier {
    public static final String ID = CentAndMysMod.makeID("DrainDamageModifier");

    private final boolean inherent;

    public DrainDamageModifier(boolean inherent) {
        this.inherent = inherent;
    }

    @Override
    public void onLastDamageTakenUpdate(DamageInfo info, int lastDamageTaken, int overkillAmount, AbstractCreature targetHit) {
        if (lastDamageTaken > 0) {
            this.addToBot(new AddTemporaryHPAction(info.owner, info.owner, lastDamageTaken));
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
