package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.cards.other.YourTurnCenturion;
import CenturionAndMystic.cards.other.YourTurnMystic;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class YourTurn extends AbstractEasyCard {
    public final static String ID = makeID("YourTurn");
    // intellij stuff , , , , , , , , 

    public YourTurn() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        setMysticCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new YourTurnMystic());
        choices.add(new YourTurnCenturion());
        addToBot(new ChooseOneAction(choices));
    }

    public void upp() {
        selfRetain = true;
    }
}