package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.cardmods.CenturionCost;
import CenturionAndMystic.cardmods.DrainDamageModifier;
import CenturionAndMystic.cardmods.VenomDamageModifier;
import CenturionAndMystic.cards.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CenturionAndMystic.CentAndMysMod.makeID;

public class SnakeBite extends AbstractEasyCard {
    public final static String ID = makeID("SnakeBite");

    public SnakeBite() {
        super(ID,1 , CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        DamageModifierManager.addModifier(this,new VenomDamageModifier(true));
        setCenturionCost(cost);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void upp() {
        upgradeDamage(2);
    }
}
