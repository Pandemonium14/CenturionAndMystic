package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class MaceCrash extends AbstractEasyCard {
    public final static String ID = makeID("MaceCrash");
    // intellij stuff , , , 8, 2, , , 3, 1

    public MaceCrash() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 4;
        setCenturionCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void triggerOnGlowCheck() {
        if (!isPlayerInfused()) {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;// 70
        if (isPlayerInfused()) this.baseDamage += this.magicNumber;// 71
        super.calculateCardDamage(mo);// 73
        this.baseDamage = realBaseDamage;// 75
        this.isDamageModified = this.damage != this.baseDamage;// 78
    }// 79

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;// 85
        if (isPlayerInfused()) this.baseDamage += this.magicNumber;// 86
        super.applyPowers();// 88
        this.baseDamage = realBaseDamage;// 90
        this.isDamageModified = this.damage != this.baseDamage;// 93
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}