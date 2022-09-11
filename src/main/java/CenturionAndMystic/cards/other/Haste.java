package CenturionAndMystic.cards.other;

import CenturionAndMystic.cards.AbstractEasyCard;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

@NoCompendium
public class Haste extends AbstractEasyCard {
    public final static String ID = makeID("Haste");
    // intellij stuff , , , , , , , , 

    public Haste() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        exhaust = true;
        selfRetain = true;
        setMysticCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new CenturionHaste());
        choices.add(new MysticHaste());
        addToBot(new ChooseOneAction(choices));
    }

    public void upp() {
        exhaust = false;
    }
}