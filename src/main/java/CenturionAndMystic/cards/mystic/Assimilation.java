package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.AssimilationPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class Assimilation extends AbstractEasyCard {
    public final static String ID = makeID("Assimilation");
    // intellij stuff , , , , , , , 3, 1

    public Assimilation() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        setMysticCost(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new AssimilationPower(p, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}