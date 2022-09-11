package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class AssimilationPower extends AbstractEasyPower {

    public static final String ID = CentAndMysMod.makeID("AssimilationPower");
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public AssimilationPower(AbstractCreature owner, int amount) {
        super(ID, strings.NAME,PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (CentAndMysMod.isPlayerInfused()) {
            addToBot(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, amount));
        }
    }
    @Override
    public void updateDescription() {
        description = strings.DESCRIPTIONS[0] + amount + strings.DESCRIPTIONS[1];
    }
}
