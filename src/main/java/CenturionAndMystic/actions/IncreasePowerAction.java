package CenturionAndMystic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class IncreasePowerAction extends AbstractGameAction {

    private int amount;
    private AbstractPower power;

    public IncreasePowerAction(AbstractPower power, int amount) {
        this.amount = amount;
        this.power = power;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasPower(power.ID)) {
            power.amount += amount;
            power.flash();
        } else {
            power.amount += amount;
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
        }
        isDone = true;
    }
}
