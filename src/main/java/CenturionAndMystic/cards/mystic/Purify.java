package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cardmods.MysticCost;
import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.PurifyPower;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class Purify extends AbstractEasyCard {
    public final static String ID = makeID("Purify");
    // intellij stuff POWER, , UNCOMMON, , , , , 1, 

    public Purify() {
        super(ID, 0, CardType.POWER, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        isInnate = true;
        setMysticCost(cost);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new ArtifactPower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p,p,new PurifyPower(p)));
    }

    public void upp() {
    }
}