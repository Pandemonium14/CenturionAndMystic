package CenturionAndMystic.patches;


import CenturionAndMystic.powers.FrailHexPower;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

@SpirePatch2(clz = GainBlockAction.class, method = "update")
public class FrailHexPatch {

    @SpirePrefixPatch
    public static void checkHexFrail(GainBlockAction __instance) {
        AbstractCreature creature = __instance.target;
        if (creature.hasPower(FrailHexPower.ID)) {
            __instance.amount = 0;
        }
    }
}
