package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class SheerPowerPower extends AbstractEasyPower {

    public static final String ID = CentAndMysMod.makeID("SheerPowerPower");
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public SheerPowerPower(AbstractCreature c, int amount) {
        super(ID,strings.NAME,PowerType.BUFF,false,c,amount);
    }

    @Override
    public void updateDescription() {
        description = strings.DESCRIPTIONS[0] + amount + strings.DESCRIPTIONS[1];
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (!CentAndMysMod.isPlayerInfused() && type == DamageInfo.DamageType.NORMAL) {
            return damage + amount;
        } else {
            return damage;
        }
    }
}
