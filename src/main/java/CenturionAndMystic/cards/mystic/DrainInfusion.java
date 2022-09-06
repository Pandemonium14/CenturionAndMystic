package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cardmods.DrainDamageModifier;
import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.DrainInfusionPower;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.DamageModApplyingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.ArrayList;
import java.util.List;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class DrainInfusion extends AbstractEasyCard {
    public final static String ID = makeID("DrainInfusion");
    // intellij stuff SKILL, SELF, COMMON, , , , , 4, 2

    public DrainInfusion() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        setMysticCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(adp(),adp(), new VigorPower(adp(),magicNumber)));
        addToBot(new ApplyPowerAction(adp(),adp(),new DrainInfusionPower(1)));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }

}