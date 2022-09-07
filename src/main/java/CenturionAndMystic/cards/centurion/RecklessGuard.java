package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class RecklessGuard extends AbstractEasyCard {
    public final static String ID = makeID("RecklessGuard");
    // intellij stuff , , , , , 10, 2, 3, -1

    public RecklessGuard() {
    super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 13;
        baseMagicNumber = magicNumber = 3;
        setCenturionCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new LoseHPAction(p,p,magicNumber));
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(-1);
    }
}