package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.TeamworkPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class Teamwork extends AbstractEasyCard {
    public final static String ID = makeID("Teamwork");
    // intellij stuff , , , , , , , , 

    public Teamwork() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        setCenturionCost(1);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new TeamworkPower(p,1)));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}