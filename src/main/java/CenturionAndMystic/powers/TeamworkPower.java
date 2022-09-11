package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.actions.CallCardAction;
import CenturionAndMystic.cardmods.CenturionCost;
import CenturionAndMystic.cardmods.MysticCost;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class TeamworkPower extends AbstractEasyPower {

    public static final String ID = CentAndMysMod.makeID("TeamworkPower");
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public TeamworkPower(AbstractCreature c, int amount) {
        super(ID,strings.NAME,PowerType.BUFF,true,c,amount);
    }

    @Override
    public void updateDescription() {
        description = strings.DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if ((card.costForTurn >= 1 && !card.freeToPlayOnce || card.cost == -1 && card.energyOnUse >= 1)) {
            if (CardModifierManager.hasModifier(card, CenturionCost.ID)) {
                addToBot(new CallCardAction(amount, c -> CardModifierManager.hasModifier(c, MysticCost.ID)));
            } else if (CardModifierManager.hasModifier(card, MysticCost.ID)) {
                addToBot(new CallCardAction(amount, c -> CardModifierManager.hasModifier(c, CenturionCost.ID)));
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }
}