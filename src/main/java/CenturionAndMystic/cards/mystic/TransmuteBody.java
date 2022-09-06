package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cards.AbstractEasyCard;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class TransmuteBody extends AbstractEasyCard {
    public final static String ID = makeID("TransmuteBody");
    private final static int COST = 3;
    // intellij stuff , , , , , , , 6, 3

    public TransmuteBody() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 8;
        setMysticCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p,p,COST));
        addToBot(new AddTemporaryHPAction(p,p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}