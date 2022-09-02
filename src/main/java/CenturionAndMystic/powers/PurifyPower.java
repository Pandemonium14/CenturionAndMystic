package CenturionAndMystic.powers;

import CenturionAndMystic.CentAndMysMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PurifyPower extends AbstractEasyPower {

    public static final String ID = CentAndMysMod.makeID("PurifyPower");
    public static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public PurifyPower(AbstractCreature owner) {
        super(ID, strings.NAME, PowerType.BUFF,false,owner,0);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE) || card.hasTag(AbstractCard.CardTags.STARTER_DEFEND)) {
            card.exhaust = true;
        }
    }
}
