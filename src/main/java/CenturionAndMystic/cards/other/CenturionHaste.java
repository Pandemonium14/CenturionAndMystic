package CenturionAndMystic.cards.other;

import CenturionAndMystic.actions.CallCardAction;
import CenturionAndMystic.cardmods.CenturionCost;
import CenturionAndMystic.cards.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;


@NoCompendium
public class CenturionHaste extends AbstractEasyCard {
    public final static String ID = makeID("CenturionHaste");
    // intellij stuff , , , , , , , , 

    public CenturionHaste() {
        super(ID, -2 , CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        setCenturionCost(-2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void onChoseThisOption() {
        addToBot(new CallCardAction(1, c -> CardModifierManager.hasModifier(c, CenturionCost.ID)));
    }

    public void upp() {
    }
}