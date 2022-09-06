package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.FrailHexPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class HexOfTheFrail extends AbstractEasyCard {
    public final static String ID = makeID("HexOfTheFrail");
    // intellij stuff SKILL, , , , , , , 2, 

    public HexOfTheFrail() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        setMysticCost(1);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m,p, new VulnerablePower(m,magicNumber,false)));
        addToBot(new ApplyPowerAction(m,p, new FrailHexPower(m)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}