package CenturionAndMystic.patches;


import CenturionAndMystic.secondchar.CenturionEnergyPanel;
import CenturionAndMystic.secondchar.MysticEnergyPanel;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch2(clz = AbstractPlayer.class, method = SpirePatch.CLASS)
public class SecondCharFields {

    public static SpireField<MysticEnergyPanel> mysticEnergyPanel = new SpireField<>(() -> null);

    public static SpireField<CenturionEnergyPanel> centurionEnergyPanel = new SpireField<>(() -> null);


}
