package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class FrailHexPower extends AbstractEasyPower {

    public static final String ID = CentAndMysMod.makeID("FrailHexPower");
    public static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public FrailHexPower(AbstractCreature owner) {
        super(ID,strings.NAME,PowerType.DEBUFF,false, owner, -1);
    }

    @Override
    public void updateDescription() {
        description = strings.DESCRIPTIONS[0];
    }
}
