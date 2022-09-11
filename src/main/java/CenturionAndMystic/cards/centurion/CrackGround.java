package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class CrackGround extends AbstractEasyCard {
    public final static String ID = makeID("CrackGround");
    // intellij stuff , , , 5, 2, , , 5, 2

    public CrackGround() {
        super(ID,1 , CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 5;
        baseMagicNumber = magicNumber = 5;
        setCenturionCost(1);
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.getMonsters().monsters.size() != 0) {
            AbstractMonster mon = AbstractDungeon.getMonsters().monsters.get(0);
            this.addToBot(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, mon.hb.cX), 0.5F));// 41
        }
        allDmg(AbstractGameAction.AttackEffect.NONE);
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

    @Override
    public void triggerOnGlowCheck() {
        if (isPlayerInfused()) {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }
}