package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.actions.ConnectionAction;
import CenturionAndMystic.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class Connection extends AbstractEasyCard {
    public final static String ID = makeID("Connection");
    // intellij stuff , , , , , , , , 

    public Connection() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        setMysticCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ConnectionAction());
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}