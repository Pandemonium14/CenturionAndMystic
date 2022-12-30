package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.stances.CobraStance;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;

public class EnterCobraStance extends AbstractEasyCard {
    public final static String ID = makeID("EnterCobraStance");
    // intellij stuff , , , , , , , , 

    public EnterCobraStance() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChangeStanceAction(CobraStance.STANCEID));
    }

    public void upp() {
    }
}