package CenturionAndMystic.patches;


import CenturionAndMystic.CenturionAndMystic;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

@SpirePatch2(clz = EnergyPanel.class, method = "render")
public class EnergyPanelRenderPatch {


    @SpirePrefixPatch
    public static SpireReturn checkIfRender(EnergyPanel __instance, SpriteBatch sb) {
        if (AbstractDungeon.player.chosenClass == CenturionAndMystic.Enums.CENT_AND_MYS) {
            ((CenturionAndMystic)AbstractDungeon.player).renderClassOrbs(sb, __instance.current_x, __instance.current_y);
            __instance.current_y = __instance.current_y +(135*1.15F * Settings.scale*2);
            if (EnergyPanel.totalCount == 0) {
                return SpireReturn.Return();
            }
        }
        return SpireReturn.Continue();
    }

    @SpirePostfixPatch
    public static void resetCurrentY(EnergyPanel __instance) {
        if (AbstractDungeon.player.chosenClass == CenturionAndMystic.Enums.CENT_AND_MYS) {
            __instance.current_y = __instance.current_y - (135*1.15F * Settings.scale*2);
        }
    }
}
