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
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
        baseDamage = 2;
        setMysticCost(cost);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
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