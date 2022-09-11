package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.cardmods.DrainCardModifier;
import CenturionAndMystic.cardmods.DrainDamageModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.DamageModApplyingPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.List;

public class DrainInfusionPower extends AbstractEasyPower implements DamageModApplyingPower {

    public static final String ID = CentAndMysMod.makeID("DrainInfusion");
    public static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);

    private AbstractCard lastCardPlayed = null;

    public DrainInfusionPower(int amount) {
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
        if (o instanceof AbstractCard && damageInfo != null) {
            return ((AbstractCard) o).type == AbstractCard.CardType.ATTACK && damageInfo.type != DamageInfo.DamageType.HP_LOSS;
        } else {
            return false;
        }
    }

    @Override
    public List<AbstractDamageModifier> modsToPush(DamageInfo damageInfo, Object o, List<AbstractDamageModifier> list) {
        ArrayList<AbstractDamageModifier> mods = new ArrayList<>();
        mods.add(new DrainDamageModifier(false));
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
