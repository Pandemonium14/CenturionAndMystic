package CenturionAndMystic.patches;


import CenturionAndMystic.CenturionAndMystic;
import CenturionAndMystic.cardmods.MysticCost;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

@SpirePatch2(clz = CardGroup.class, method = "refreshHandLayout")
public class SortHandPatch {

    @SpirePrefixPatch
    public static void SortCentAndMysCards(CardGroup __instance) {
        if (AbstractDungeon.player.chosenClass == CenturionAndMystic.Enums.CENT_AND_MYS) {
            ArrayList<AbstractCard> hand = __instance.group;
            ArrayList<AbstractCard> sortedHand = new ArrayList<>();
            ArrayList<AbstractCard> leftToSort = new ArrayList<>();

            for (AbstractCard c : hand) {
                if (CardModifierManager.hasModifier(c, MysticCost.ID)) {
                    sortedHand.add(c);
                } else {
                    leftToSort.add(c);
                }
            }
            sortedHand.addAll(leftToSort);
            __instance.group = sortedHand;
        }
    }
}
