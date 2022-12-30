package CenturionAndMystic.patches;


import CenturionAndMystic.stances.CobraStance;
import CenturionAndMystic.stances.PythonStance;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.stances.AbstractStance;

@SpirePatch2(clz = AbstractStance.class, method = "getStanceFromName")
public class GetStanceFromIDPatch {

    @SpirePrefixPatch
    public static SpireReturn<AbstractStance> cobraStance(String name) {
        if (name.equals(CobraStance.STANCEID)) {
            return SpireReturn.Return(new CobraStance());
        } else if (name.equals(PythonStance.STANCEID)) {
            return SpireReturn.Return(new PythonStance());
        }
        return SpireReturn.Continue();
    }
}
