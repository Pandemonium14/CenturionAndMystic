package CenturionAndMystic.actions;

import CenturionAndMystic.patches.SecondCharFields;
import CenturionAndMystic.secondchar.CenturionEnergyPanel;
import CenturionAndMystic.secondchar.MysticEnergyPanel;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ConnectionAction extends AbstractGameAction {

    public ConnectionAction() {
    }

    @Override
    public void update() {
        CenturionEnergyPanel cPanel = SecondCharFields.centurionEnergyPanel.get(AbstractDungeon.player);
        MysticEnergyPanel mPanel = SecondCharFields.mysticEnergyPanel.get(AbstractDungeon.player);
        if (cPanel != null && mPanel != null) {
            int energyGain = 0;
            energyGain += cPanel.energy;
            energyGain += mPanel.energy;
            cPanel.gainEnergy( -cPanel.energy);
            mPanel.gainEnergy( -mPanel.energy);
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction(energyGain));
            isDone = true;
        } else {
            isDone = true;
        }
    }
}
