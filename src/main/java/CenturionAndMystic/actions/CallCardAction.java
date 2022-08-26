package CenturionAndMystic.actions;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class CallCardAction extends AbstractGameAction {

    private int amount;
    private Function<AbstractCard,Boolean> filter;

    public CallCardAction(int amount, Function<AbstractCard,Boolean> filter) {
        this.amount = amount;
        this.filter = filter;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> validCards = new ArrayList<>();
        boolean fromDiscardPile = false;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (filter.apply(c)) {
                validCards.add(c);
            }
        }
        if (validCards.size() == 0) {
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (filter.apply(c)) {
                    validCards.add(c);
                }
            }
            fromDiscardPile = true;
        }
        int cardsGot = 0;
        while (validCards.size() != 0 && AbstractDungeon.player.hand.group.size() < BaseMod.MAX_HAND_SIZE && cardsGot < amount) {
            AbstractCard card = validCards.get(AbstractDungeon.cardRng.random(validCards.size()-1));
            if (fromDiscardPile) {
                AbstractDungeon.player.hand.moveToHand(card, AbstractDungeon.player.discardPile);
            } else {
                AbstractDungeon.player.hand.moveToHand(card, AbstractDungeon.player.drawPile);
            }
            validCards.remove(card);
            cardsGot++;
        }
        isDone = true;
    }
}
