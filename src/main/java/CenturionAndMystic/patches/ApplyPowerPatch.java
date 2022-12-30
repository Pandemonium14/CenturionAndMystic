package CenturionAndMystic.patches;


import CenturionAndMystic.stances.CobraStance;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ApplyPowerPatch {
    @SpirePatch2(clz = ApplyPowerAction.class, method = "update")
    public static class UpdatePrefixPatch {

        /*@SpirePrefixPatch
        public static void checkForStance(ApplyPowerAction __instance, AbstractPower ___powerToApply, float ___duration, float ___startDuration) {
            if (___duration == ___startDuration && __instance.target != null && !__instance.target.isDeadOrEscaped()) {
                if (__instance.source instanceof AbstractPlayer && AbstractDungeon.player.stance instanceof CobraStance) {
                    if (___powerToApply.type == AbstractPower.PowerType.DEBUFF) {
                        __instance.amount++;
                        ___powerToApply.amount++;
                    }
                }
            }
        }*/

    }

    @SpirePatch2(clz = ApplyPowerAction.class, method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCreature.class, AbstractCreature.class, AbstractPower.class, int.class, boolean.class, AbstractGameAction.AttackEffect.class})
    public static class ConstructorPrefixPatch {


        @SpirePrefixPatch
        public static void checkForStance(ApplyPowerAction __instance, AbstractCreature source, AbstractPower powerToApply, @ByRef int[] stackAmount) {
            if (source instanceof AbstractPlayer && powerToApply.type == AbstractPower.PowerType.DEBUFF && AbstractDungeon.player.stance instanceof CobraStance) {
                powerToApply.amount++;
                stackAmount[0]++;
            }
        }
    }
}
