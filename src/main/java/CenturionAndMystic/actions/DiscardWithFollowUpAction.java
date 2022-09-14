package CenturionAndMystic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

public class DiscardWithFollowUpAction extends AbstractGameAction {

    public static ArrayList<AbstractCard> discardedCards = new ArrayList();
    public int amount;
    public AbstractGameAction action;
    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    private boolean anyNumber;

    public DiscardWithFollowUpAction(int amount, AbstractGameAction action) {
        discardedCards.clear();
        this.amount = amount;
        this.action = action;
        duration = Settings.ACTION_DUR_XFAST;
        anyNumber = false;
    }

    public DiscardWithFollowUpAction(boolean anyNumber, AbstractGameAction action) {
        discardedCards.clear();
        this.anyNumber = anyNumber;
        this.action = action;
        duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_XFAST) {
            AbstractPlayer p = AbstractDungeon.player;
            if (!anyNumber) {
                if (p.hand.size() <= amount) {
                    for (AbstractCard c : p.hand.group) {
                        discardedCards.add(c);
                        addToTop(new DiscardSpecificCardAction(c, p.hand));
                    }
                    addToTop(action);
                    isDone = true;
                } else {
                    AbstractDungeon.handCardSelectScreen.open(strings.TEXT[0], amount, false);
                }
            } else {
                AbstractDungeon.handCardSelectScreen.open(strings.TEXT[0],99, true);
            }
            tickDuration();
        } else {
            ArrayList<AbstractCard> selectedCards = AbstractDungeon.handCardSelectScreen.selectedCards.group;
            for (AbstractCard c : selectedCards) {
                discardedCards.add(c);
                AbstractDungeon.player.hand.addToHand(c);
                AbstractDungeon.player.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
            }
            addToTop(action);
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            isDone = true;
        }
    }
}
