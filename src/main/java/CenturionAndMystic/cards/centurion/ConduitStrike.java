package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.actions.IncreasePowerAction;
import CenturionAndMystic.cardmods.CenturionCost;
import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.AbstractEasyPower;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.List;

import static CenturionAndMystic.CentAndMysMod.makeID;

public class ConduitStrike extends AbstractEasyCard {
    public final static String ID = makeID("ConduitStrike");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ConduitStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
        setCenturionCost(cost);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        for (AbstractPower pow : AbstractDungeon.player.powers) {
            if (pow instanceof AbstractEasyPower && ((AbstractEasyPower) pow).isInfusion) {
                addToBot(new IncreasePowerAction(pow,1));
            }
        }
        if (AbstractDungeon.player.hasPower(VigorPower.POWER_ID)) {
            AbstractPower v = AbstractDungeon.player.getPower(VigorPower.POWER_ID);
            addToBot(new ApplyPowerAction(p,p, new VigorPower(p,v.amount)));
        }
    }

    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public List<String> getCardDescriptors() {
        return centurionDescriptor();
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isPlayerEmpowered()) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
}