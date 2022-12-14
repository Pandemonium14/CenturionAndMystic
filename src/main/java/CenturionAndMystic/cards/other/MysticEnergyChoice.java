package CenturionAndMystic.cards.other;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.cards.AbstractEasyCard;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

@NoCompendium
public class MysticEnergyChoice extends AbstractEasyCard {
    public final static String ID = makeID("MysticEnergyChoice");
    // intellij stuff , , , , , , , ,

    public MysticEnergyChoice() {
        super(ID, -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        setMysticCost(-2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void onChoseThisOption() {
        CentAndMysMod.energyGainManager.receiveInput("mystic");
    }

    public void upp() {
    }

    @Override
    public List<String> getCardDescriptors() {
        return mysticDescriptor();
    }
}