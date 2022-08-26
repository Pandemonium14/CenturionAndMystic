package CenturionAndMystic.patches;

import CenturionAndMystic.CenturionAndMystic;
import CenturionAndMystic.cardmods.MysticCost;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch2(clz = AbstractCard.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {String.class, String.class, String.class, int.class, String.class, AbstractCard.CardType.class, AbstractCard.CardColor.class, AbstractCard.CardRarity.class,AbstractCard.CardTarget.class , DamageInfo.DamageType.class})
public class AddMysticCostPatch {

    @SpirePostfixPatch
    public static void addMysticCost(AbstractCard __instance) {
        if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass == CenturionAndMystic.Enums.CENT_AND_MYS) {
            if (!(__instance.type == AbstractCard.CardType.ATTACK || __instance.baseBlock >= 0)) {
             //   CardModifierManager.addModifier(__instance, new MysticCost(__instance.cost));
            } else {

            }
        }
    }
}
