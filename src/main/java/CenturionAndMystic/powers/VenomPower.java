package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class VenomPower extends AbstractEasyPower {

    public static final String ID = CentAndMysMod.makeID("VenomPower");
    public static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);

    private static final int DECREASE = 5;

    public VenomPower(AbstractCreature owner, int amount) {
        super(ID, strings.NAME, PowerType.DEBUFF,false,owner,amount);
    }

    @Override
    public void updateDescription() {
        description = strings.DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        if (amount <= DECREASE) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        } else {
            addToBot(new ReducePowerAction(owner, owner, ID,DECREASE));
        }
    }

}
