package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.ImprovedInfusionPower;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

@AutoAdd.Ignore
public class ImprovedInfusion extends AbstractEasyCard {
    public final static String ID = makeID("ImprovedInfusion");
    // intellij stuff , , , , , , , 3, 1

    public ImprovedInfusion() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        setMysticCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new ImprovedInfusionPower(p,magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}