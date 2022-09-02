package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.AbstractEasyPower;
import CenturionAndMystic.powers.DrainInfusionPower;
import CenturionAndMystic.powers.VenomInfusionPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.adp;

public class VenomInfusion extends AbstractEasyCard {

    public final static String ID = makeID("VenomInfusion");
    // intellij stuff SKILL, SELF, COMMON, , , , , 4, 2

    public VenomInfusion() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        setMysticCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(adp(),adp(), new VigorPower(adp(),magicNumber)));
        addToBot(new ApplyPowerAction(adp(),adp(),new VenomInfusionPower(1)));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}
