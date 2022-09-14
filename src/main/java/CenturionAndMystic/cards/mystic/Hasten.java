package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.actions.CallCardAction;
import CenturionAndMystic.cardmods.MysticCost;
import CenturionAndMystic.cards.AbstractEasyCard;
import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class Hasten extends AbstractEasyCard {
    public final static String ID = makeID("Hasten");

    public Hasten() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        setMysticCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CallCardAction(1,(c -> !CardModifierManager.hasModifier(c, MysticCost.ID))));
        addToBot(new GainEnergyAction(1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}