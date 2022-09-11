package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.CohesionPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class Cohesion extends AbstractEasyCard {
    public final static String ID = makeID("Cohesion");
    // intellij stuff , , , , , , , 5, 

    public Cohesion() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        setCenturionCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new CohesionPower(p,magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}