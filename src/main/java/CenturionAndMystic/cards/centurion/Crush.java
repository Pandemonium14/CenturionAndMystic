package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;

public class Crush extends AbstractEasyCard {


    public final static String ID = makeID("Crush");
    // intellij stuff , , , 12, , , , 8, 4

    public Crush() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 12;
        baseMagicNumber = magicNumber = 8;
        setCenturionCost(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage + magicNumber), AbstractGameAction.AttackEffect.BLUNT_HEAVY, false));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (isPlayerInfused()) {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;// 70
        if (!isPlayerInfused()) this.baseDamage += this.magicNumber;// 71
        super.calculateCardDamage(mo);// 73
        this.baseDamage = realBaseDamage;// 75
        this.isDamageModified = this.damage != this.baseDamage;// 78
    }// 79

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;// 85
        if (!isPlayerInfused()) this.baseDamage += this.magicNumber;// 86
        super.applyPowers();// 88
        this.baseDamage = realBaseDamage;// 90
        this.isDamageModified = this.damage != this.baseDamage;// 93
    }

    public void upp() {
        upgradeMagicNumber(4);
    }
}