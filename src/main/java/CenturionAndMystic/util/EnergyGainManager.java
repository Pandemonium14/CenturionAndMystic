package CenturionAndMystic.util;

import CenturionAndMystic.cards.other.CenturionEnergyChoice;
import CenturionAndMystic.cards.other.MysticEnergyChoice;
import CenturionAndMystic.secondchar.CenturionEnergyPanel;
import CenturionAndMystic.secondchar.MysticEnergyPanel;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class EnergyGainManager {

    public int centurionEnergy;
    public int mysticEnergy;
    public int universalEnergy;
    public int screenQueue = 0;


    public EnergyGainManager() {
        universalEnergy = AbstractDungeon.player.energy.energyMaster;
        mysticEnergy = MysticEnergyPanel.baseEnergy;
        centurionEnergy = CenturionEnergyPanel.baseEnergy;
    }


    public void update() {
        if (universalEnergy < AbstractDungeon.player.energy.energyMaster) {
            openEnergyGainMenu(AbstractDungeon.player.energy.energyMaster - universalEnergy);
            AbstractDungeon.player.energy.energyMaster = universalEnergy;
        }
    }


    public void openEnergyGainMenu(int amount) {
        screenQueue = amount;
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new CenturionEnergyChoice());
        choices.add(new MysticEnergyChoice());
        AbstractDungeon.cardRewardScreen.chooseOneOpen(choices);
    }

    public void receiveInput(String input) {
        if (input.equals("mystic")) {
            mysticEnergy += 1;
            MysticEnergyPanel.baseEnergy = mysticEnergy;
        } else if (input.equals("centurion")){
            centurionEnergy += 1;
            CenturionEnergyPanel.baseEnergy = centurionEnergy;
        } else {
            universalEnergy += 1;
            AbstractDungeon.player.energy.energyMaster = universalEnergy;
        }
        screenQueue -=1;
        if (screenQueue > 0) {
            openEnergyGainMenu(screenQueue);
        }
    }
}
