package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.actions.DiscardWithFollowUpAction;
import CenturionAndMystic.cardmods.CenturionCost;
import CenturionAndMystic.cardmods.MysticCost;
import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.util.Wiz;
import basemod.ReflectionHacks;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class StandStrong extends AbstractEasyCard {
    public final static String ID = makeID("StandStrong");
    // intellij stuff , SELF, , , , , , 2, -1

    public StandStrong() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        setCenturionCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardWithFollowUpAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                boolean b = true;
                for (AbstractCard c : DiscardWithFollowUpAction.discardedCards) {
                    if (!CardModifierManager.hasModifier(c, MysticCost.ID)) {
                        b = false;
                    }
                }
                if (b) {
                    int incomingDamage = 0;
                    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                        if ((m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND) && m.getIntentDmg()>0) {
                            int multiAmt = ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt");
                            if (multiAmt > 0) {
                                incomingDamage += m.getIntentDmg() * multiAmt;
                            } else {
                                incomingDamage += m.getIntentDmg();
                            }
                        }
                    }
                    int block = incomingDamage;
                    if (AbstractDungeon.player.hasPower(DexterityPower.POWER_ID)) {
                        block += AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount;
                    }
                    if (incomingDamage >0) {
                        addToTop(new GainBlockAction(AbstractDungeon.player, block));
                    }
                }
                isDone = true;
            }
        }));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}