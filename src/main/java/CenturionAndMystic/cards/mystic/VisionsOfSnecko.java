package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.actions.CustomRandomizeHandCostAction;
import CenturionAndMystic.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class VisionsOfSnecko extends AbstractEasyCard {
    public final static String ID = makeID("VisionsOfSnecko");
    // intellij stuff , , , , , , , 4, 5

    public VisionsOfSnecko() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        setMysticCost(1);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new CustomRandomizeHandCostAction());
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}