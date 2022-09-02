package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cards.AbstractEasyCard;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class MysticDefend extends AbstractEasyCard {
    public final static String ID = makeID("MysticDefend");
    // intellij stuff SKILL, SELF, BASIC, , , 5, 3, , 

    public MysticDefend() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        setMysticCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddTemporaryHPAction(p,p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public List<String> getCardDescriptors() {
        return mysticDescriptor();
    }
}