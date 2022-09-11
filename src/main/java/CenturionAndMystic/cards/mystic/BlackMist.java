package CenturionAndMystic.cards.mystic;

import CenturionAndMystic.cards.AbstractEasyCard;
import CenturionAndMystic.powers.VenomPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

import static CenturionAndMystic.CentAndMysMod.makeID;
import static CenturionAndMystic.util.Wiz.*;

public class BlackMist extends AbstractEasyCard {
    public final static String ID = makeID("BlackMist");
    // intellij stuff , , , , , , , 1, 1

    public BlackMist() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 1;
        secondMagic = baseSecondMagic = 6;
        setMysticCost(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {// 36
            this.flash();// 37
            Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();// 38

            while(var3.hasNext()) {
                AbstractMonster monster = (AbstractMonster)var3.next();
                if (!monster.isDead && !monster.isDying) {// 39
                    this.addToBot(new ApplyPowerAction(monster, p, new VenomPower(monster, secondMagic)));// 40
                    this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, magicNumber, false), magicNumber));// 41
                }
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }
}