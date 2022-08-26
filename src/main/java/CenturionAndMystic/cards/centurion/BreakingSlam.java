package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.cardmods.CenturionCost;
import CenturionAndMystic.cards.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static CenturionAndMystic.CentAndMysMod.makeID;

public class BreakingSlam extends AbstractEasyCard {
    public final static String ID = makeID("BreakingSlam");
    // intellij stuff , , , 10, 2, , , 2, 1

    public BreakingSlam() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 2;
        CardModifierManager.addModifier(this, new CenturionCost(cost));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (isPlayerEmpowered()) {
            addToBot(new ApplyPowerAction(m,p, new VulnerablePower(m, magicNumber, false)));
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isPlayerEmpowered()) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
}