package CenturionAndMystic.cards.other;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class CenturionEnergyChoice extends AbstractEasyCard {
    public final static String ID = makeID("CenturionEnergyChoice");
    // intellij stuff , , , , , , , , 

    public CenturionEnergyChoice() {
        super(ID, -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void onChoseThisOption() {
        CentAndMysMod.energyGainManager.receiveInput("centurion");
    }

    public void upp() {
    }
}