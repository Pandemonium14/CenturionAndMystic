package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cardmods.MysticCost;
import CenturionAndMystic.cards.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.*;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class MysticStrike extends AbstractEasyCard {
    public final static String ID = makeID("MysticStrike");
    // intellij stuff ATTACK, ENEMY, BASIC, 6, 3, , , , 

    public MysticStrike() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        CardModifierManager.addModifier(this, new MysticCost(1));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p,new VigorPower(p,magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }

    @Override
    public List<String> getCardDescriptors() {
        return mysticDescriptor();
    }
}