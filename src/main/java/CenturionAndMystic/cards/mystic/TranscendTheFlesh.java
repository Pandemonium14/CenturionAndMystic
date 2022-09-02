package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cards.AbstractEasyCard;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.RemoveAllTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class TranscendTheFlesh extends AbstractEasyCard {
    public final static String ID = makeID("TranscendTheFlesh");
    // intellij stuff , , , , , , , 1, 

    public TranscendTheFlesh() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
        setMysticCost(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int tmpHpAmount = TempHPField.tempHp.get(AbstractDungeon.player);
        addToBot(new RemoveAllTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player));
        addToBot(new ApplyPowerAction(adp(),adp(), new StrengthPower(adp(), tmpHpAmount/3)));
    }

    public void upp() {
    }
}