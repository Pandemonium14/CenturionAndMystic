package CenturionAndMystic.cards.other;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.actions.CallCardAction;
import CenturionAndMystic.actions.ChangeDualCharEnergyAction;
import CenturionAndMystic.cardmods.CenturionCost;
import CenturionAndMystic.cardmods.MysticCost;
import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.patches.SecondCharFields;
import CenturionAndMystic.secondchar.CenturionEnergyPanel;
import CenturionAndMystic.secondchar.MysticEnergyPanel;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import static CenturionAndMystic.CentAndMysMod.makeID;

public class YourTurnMystic extends AbstractEasyCard {

    public final static String ID = makeID("YourTurnMystic");
    // intellij stuff , , , , , , , ,

    public YourTurnMystic() {
        super(ID, -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        setMysticCost(-2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void onChoseThisOption() {
        MysticEnergyPanel mPanel = SecondCharFields.mysticEnergyPanel.get(AbstractDungeon.player);
        CenturionEnergyPanel cPanel = SecondCharFields.centurionEnergyPanel.get(AbstractDungeon.player);
        if (mPanel != null && cPanel != null) {
            int e = cPanel.energy;
            addToBot(new ChangeDualCharEnergyAction("centurion", -e));
            addToBot(new ChangeDualCharEnergyAction("mystic", e));
        }
        addToBot(new CallCardAction(1,c -> CardModifierManager.hasModifier(c, MysticCost.ID)));
    }

    public void upp() {
    }

    @Override
    public List<String> getCardDescriptors() {
        return mysticDescriptor();
    }
}
