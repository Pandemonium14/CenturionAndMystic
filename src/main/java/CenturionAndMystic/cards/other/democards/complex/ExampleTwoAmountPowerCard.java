package CenturionAndMystic.cards.other.democards.complex;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.ExampleTwoAmountPower;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.applyToSelf;

public class ExampleTwoAmountPowerCard extends AbstractEasyCard {
    public final static String ID = makeID(ExampleTwoAmountPowerCard.class.getSimpleName());

    public ExampleTwoAmountPowerCard() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ExampleTwoAmountPower(p, magicNumber, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}