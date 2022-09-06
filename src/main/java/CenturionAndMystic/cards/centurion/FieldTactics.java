package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.actions.CallCardAction;
import CenturionAndMystic.cardmods.CenturionCost;
import CenturionAndMystic.cardmods.MysticCost;
import CenturionAndMystic.cards.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class FieldTactics extends AbstractEasyCard {
    public final static String ID = makeID("FieldTactics");
    // intellij stuff , , , , , , , 1, 1

    public FieldTactics() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        setCenturionCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CallCardAction(1,c -> CardModifierManager.hasModifier(c, MysticCost.ID)));
        addToBot(new CallCardAction(1,c -> CardModifierManager.hasModifier(c, CenturionCost.ID)));
        addToBot(new ApplyPowerAction(p,p,new DrawCardNextTurnPower(p, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}