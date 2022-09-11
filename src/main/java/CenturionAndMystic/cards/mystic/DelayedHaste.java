package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.cards.other.Haste;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class DelayedHaste extends AbstractEasyCard {
    public final static String ID = makeID("DelayedHaste");
    // intellij stuff , , , , , , , 2, 

    public DelayedHaste() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
        cardsToPreview= new Haste();
        setMysticCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(new Haste(),magicNumber));
    }

    public void upp() {
        exhaust = false;
    }
}