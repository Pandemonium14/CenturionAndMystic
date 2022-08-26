package CenturionAndMystic.cards.centurion;

import CenturionAndMystic.actions.CallCardAction;
import CenturionAndMystic.cardmods.CenturionCost;
import CenturionAndMystic.cardmods.MysticCost;
import CenturionAndMystic.cards.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import static CenturionAndMystic.CentAndMysMod.makeID;

public class Distract extends AbstractEasyCard {
    public final static String ID = makeID("Distract");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Distract() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 8;
        baseMagicNumber = magicNumber = 1;
        CardModifierManager.addModifier(this, new CenturionCost(cost));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new CallCardAction(1,c -> CardModifierManager.hasModifier(c, MysticCost.ID)));
    }

    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public List<String> getCardDescriptors() {
        return centurionDescriptor();
    }
}
