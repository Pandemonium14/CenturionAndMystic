package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.VenomInfusionPower;
import CenturionAndMystic.powers.VenomRitualPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class BlackVenomRitual extends AbstractEasyCard {
    public final static String ID = makeID("BlackVenomRitual");
    // intellij stuff , SELF, , , , , , , 

    public BlackVenomRitual() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        setMysticCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new VenomInfusionPower(1)));
        addToBot(new ApplyPowerAction(p,p,new VenomRitualPower(upgraded)));
    }

    public void upp() {
    }
}