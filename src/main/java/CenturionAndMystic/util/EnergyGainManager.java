package CenturionAndMystic.util;

import CenturionAndMystic.cards.other.CenturionEnergyChoice;
import CenturionAndMystic.cards.other.MysticEnergyChoice;
import CenturionAndMystic.patches.SecondCharFields;
import CenturionAndMystic.secondchar.CenturionEnergyPanel;
import CenturionAndMystic.secondchar.MysticEnergyPanel;
import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.Level;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;

public class EnergyGainManager implements CustomSavable<ArrayList<String>> {

    public int centurionEnergy;
    public int mysticEnergy;
    public int universalEnergy;
    public int screenQueue = 0;
    public static ArrayList<String> history = new ArrayList<>();


    public EnergyGainManager() {
        universalEnergy = AbstractDungeon.player.energy.energyMaster;
        mysticEnergy = MysticEnergyPanel.baseEnergy;
        centurionEnergy = CenturionEnergyPanel.baseEnergy;
    }

    public EnergyGainManager(boolean initializing) {
        if (!initializing) {
            universalEnergy = AbstractDungeon.player.energy.energyMaster;
            mysticEnergy = MysticEnergyPanel.baseEnergy;
            centurionEnergy = CenturionEnergyPanel.baseEnergy;
        }
    }


    public void update() {
        if (universalEnergy < AbstractDungeon.player.energy.energyMaster) {
            BaseMod.logger.log(Level.INFO, "Max E increase detected: " + universalEnergy + " -> " + AbstractDungeon.player.energy.energyMaster);
            int amountGained = AbstractDungeon.player.energy.energyMaster - universalEnergy;
            AbstractDungeon.player.energy.energyMaster = 0;
            openEnergyGainMenu(amountGained);
            BaseMod.logger.log(Level.INFO, "exiting if loop of E increase, max energy is " + AbstractDungeon.player.energy.energyMaster + ", saved energy max is " + universalEnergy);
        }
        if (universalEnergy > AbstractDungeon.player.energy.energyMaster) {
            int amountLost = universalEnergy - AbstractDungeon.player.energy.energyMaster;
            AbstractDungeon.player.energy.energyMaster = 0;
            while (history.size() != 0 && amountLost > 0) {
                String lastGain = history.get(history.size() -1);
                history.remove(history.size()-1);
                if (lastGain.equals("mystic")) {
                    mysticEnergy -= 1;
                    MysticEnergyPanel.baseEnergy = mysticEnergy;
                    amountLost--;
                } else if (lastGain.equals("centurion")) {
                    centurionEnergy -=1;
                    CenturionEnergyPanel.baseEnergy = centurionEnergy;
                } else {
                    BaseMod.logger.log(Level.INFO, "Issue with reducing max energy in Centurion and Mystic mod : wrong string in history");
                    amountLost--;
                }
            }
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
        BaseMod.logger.log(Level.INFO, "Receiving input for energy gain "+input+ ", factual max E is " + AbstractDungeon.player.energy.energyMaster);
        if (input.equals("mystic")) {
            mysticEnergy += 1;
            MysticEnergyPanel.baseEnergy = mysticEnergy;
            history.add(input);
        } else if (input.equals("centurion")){
            centurionEnergy += 1;
            CenturionEnergyPanel.baseEnergy = centurionEnergy;
            history.add(input);
        } else {
            BaseMod.logger.log(Level.INFO, "Wrong key input, increasing universal energy");
            universalEnergy += 1;
            AbstractDungeon.player.energy.energyMaster = universalEnergy;
        }
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractDungeon.player.energy.energy = universalEnergy;
            SecondCharFields.centurionEnergyPanel.get(AbstractDungeon.player).trueResetEnergy();
            SecondCharFields.mysticEnergyPanel.get(AbstractDungeon.player).trueResetEnergy();
        }
        screenQueue -=1;
        if (screenQueue > 0) {
            openEnergyGainMenu(screenQueue);
        }
        BaseMod.logger.log(Level.INFO, "Exiting receiveInput method, factual energy is "+AbstractDungeon.player.energy.energyMaster+", saved energy is "+universalEnergy);
    }

    @Override
    public ArrayList<String> onSave() {
        return history;
    }

    @Override
    public void onLoad(ArrayList<String> strings) {
        if (strings!=null) history = strings;
    }
}
