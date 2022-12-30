package CenturionAndMystic.stances;

import CenturionAndMystic.CentAndMysMod;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;

public class PythonStance extends AbstractStance {

    public static final String STANCEID = CentAndMysMod.makeID("PythonStance");
    private static final StanceStrings strings = CardCrawlGame.languagePack.getStanceString(STANCEID);


    public PythonStance() {
        ID = STANCEID;
        name = strings.NAME;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = strings.DESCRIPTION[0];
    }

    @Override
    public void onEnterStance() {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
    }
}
