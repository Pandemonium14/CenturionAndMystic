package CenturionAndMystic.cardmods;

import CenturionAndMystic.CentAndMysMod;
import basemod.abstracts.AbstractCardModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DrainCardModifier extends AbstractCardModifier {
    public static final String ID = CentAndMysMod.makeID("DrainCardModifier");

    private boolean removeAfterPlayed = false;

    public DrainCardModifier() {}

    public DrainCardModifier(boolean removeWhenPlayed) {
        removeAfterPlayed = removeWhenPlayed;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DrainCardModifier(removeAfterPlayed);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        DamageModifierManager.addModifier(card, new DrainDamageModifier(!removeAfterPlayed));
    }

    @Override
    public void onRemove(AbstractCard card) {
        ArrayList<AbstractDamageModifier> modsToRemove = new ArrayList<>();
        for (AbstractDamageModifier DMod : DamageModifierManager.modifiers(card)) {
            if (DMod instanceof DrainDamageModifier) {
                modsToRemove.add(DMod);
            }
        }
        for (AbstractDamageModifier DMod : modsToRemove) {
            DamageModifierManager.removeModifier(card, DMod);
        }
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return removeAfterPlayed;
    }
}
