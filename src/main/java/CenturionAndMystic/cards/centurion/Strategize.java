package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.actions.CallCardAction;
import CenturionAndMystic.actions.DiscardWithFollowUpAction;
import CenturionAndMystic.cardmods.CenturionCost;
import CenturionAndMystic.cardmods.MysticCost;
import CenturionAndMystic.cards.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class Strategize extends AbstractEasyCard {
    public final static String ID = makeID("Strategize");
    // intellij stuff , , , , , , , , 

    public Strategize() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        setCenturionCost(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardWithFollowUpAction(true, new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : DiscardWithFollowUpAction.discardedCards) {
                    if (CardModifierManager.hasModifier(c, CenturionCost.ID)) {
                        addToTop(new CallCardAction(1, card -> CardModifierManager.hasModifier(card, MysticCost.ID)));
                    } else if (CardModifierManager.hasModifier(c, MysticCost.ID)) {
                        addToTop(new CallCardAction(1, card -> CardModifierManager.hasModifier(card, CenturionCost.ID)));
                    }
                }
                isDone = true;
            }
        }));
    }

    public void upp() {
        exhaust = true;
    }
}