package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.cards.centurion.Cohesion;
import CenturionAndMystic.patches.SecondCharFields;
import CenturionAndMystic.secondchar.CenturionEnergyPanel;
import CenturionAndMystic.secondchar.MysticEnergyPanel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class CohesionPower extends AbstractEasyPower{

    public static final String ID = CentAndMysMod.makeID("CohesionPower");
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public CohesionPower(AbstractCreature owner, int amount) {
        super(ID, strings.NAME,PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        MysticEnergyPanel mPanel = SecondCharFields.mysticEnergyPanel.get(AbstractDungeon.player);
        CenturionEnergyPanel cPanel = SecondCharFields.centurionEnergyPanel.get(AbstractDungeon.player);
        if (AbstractDungeon.player.energy.energy == 0) {
            if (mPanel != null && cPanel != null) {
                if ( cPanel.energy == 0 && mPanel.energy == 0) {
                    addToBot(new GainBlockAction(AbstractDungeon.player, amount));
                }
            } else {
                addToBot(new GainBlockAction(AbstractDungeon.player, amount));
            }
        }
    }
}
