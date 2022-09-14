package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.cards.centurion.Strategize;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class ImprovedInfusionPower extends AbstractEasyPower {
    public static final String ID = CentAndMysMod.makeID("ImprovedInfusionPower");
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);
    private boolean activate = true;

    public ImprovedInfusionPower(AbstractCreature owner, int amount) {
        super(ID, strings.NAME,PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target instanceof AbstractPlayer && activate) {
            if ((power instanceof AbstractEasyPower && ((AbstractEasyPower) power).isInfusion) || power instanceof VigorPower) {
                activate = false;
                addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player,amount)));
            }
        } else if (!activate) {
            activate = true;
        }
    }

    @Override
    public void updateDescription() {
        description = strings.DESCRIPTIONS[0] + amount + strings.DESCRIPTIONS[1];
    }
}
