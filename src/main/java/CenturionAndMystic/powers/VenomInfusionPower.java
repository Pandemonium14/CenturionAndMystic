package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.cardmods.DrainDamageModifier;
import CenturionAndMystic.cardmods.VenomDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.DamageModApplyingPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.List;

public class VenomInfusionPower extends AbstractEasyPower implements DamageModApplyingPower {

    public static final String ID = CentAndMysMod.makeID("VenomInfusion");
    public static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);

    private AbstractCard lastCardPlayed = null;

    public VenomInfusionPower(int amount) {
        super(ID, strings.NAME, PowerType.BUFF, true, AbstractDungeon.player, amount);
        isInfusion = true;
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = strings.DESCRIPTIONS[0];
        } else {
            description = strings.DESCRIPTIONS[1] + amount + strings.DESCRIPTIONS[2];
        }
    }


    @Override
    public boolean shouldPushMods(DamageInfo damageInfo, Object o, List<AbstractDamageModifier> list) {
        return true;
    }

    @Override
    public List<AbstractDamageModifier> modsToPush(DamageInfo damageInfo, Object o, List<AbstractDamageModifier> list) {
        ArrayList<AbstractDamageModifier> mods = new ArrayList<>();
        mods.add(new VenomDamageModifier(false));
        return mods;
    }

    @Override
    public void onAddedDamageModsToDamageInfo(DamageInfo info, Object instigator) {
        if (amount > 0) {
            amount --;
            if (amount == 0) {
                addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player,AbstractDungeon.player, this));
            }
        }
    }
}
