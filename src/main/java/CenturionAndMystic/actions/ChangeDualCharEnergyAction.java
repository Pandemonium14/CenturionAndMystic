package CenturionAndMystic.actions;

import CenturionAndMystic.patches.SecondCharFields;
import CenturionAndMystic.secondchar.CenturionEnergyPanel;
import CenturionAndMystic.secondchar.MysticEnergyPanel;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ChangeDualCharEnergyAction extends AbstractGameAction {

    private String character;
    private MysticEnergyPanel mPanel = SecondCharFields.mysticEnergyPanel.get(AbstractDungeon.player);
    private CenturionEnergyPanel cPanel = SecondCharFields.centurionEnergyPanel.get(AbstractDungeon.player);

    public ChangeDualCharEnergyAction(String charKey, int amt) {
        character = charKey;
        amount = amt;
    }

    @Override
    public void update() {
        if (!(mPanel == null || cPanel == null)) {
            if (character.equals("mystic")) {
                mPanel.gainEnergy(amount);
            } else if (character.equals("centurion")) {
                cPanel.gainEnergy(amount);
            }
        }
        isDone = true;
    }
}
