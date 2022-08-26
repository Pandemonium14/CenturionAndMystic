package CenturionAndMystic;

import CenturionAndMystic.cards.centurion.Defend;
import CenturionAndMystic.cards.centurion.Distract;
import CenturionAndMystic.cards.centurion.Strike;
import CenturionAndMystic.cards.mystic.Hasten;
import CenturionAndMystic.cards.mystic.MysticDefend;
import CenturionAndMystic.cards.mystic.MysticStrike;
import CenturionAndMystic.patches.SecondCharFields;
import CenturionAndMystic.secondchar.CenturionEnergyPanel;
import CenturionAndMystic.secondchar.MysticEnergyPanel;
import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import CenturionAndMystic.relics.TodoItem;

import java.util.ArrayList;

import static CenturionAndMystic.CenturionAndMystic.Enums.CENTURION_COLOR;
import static CenturionAndMystic.CentAndMysMod.*;

public class CenturionAndMystic extends CustomPlayer {
    private static final String[] orbTextures = {
            modID + "Resources/images/char/orb/layer1.png",
            modID + "Resources/images/char/orb/layer2.png",
            modID + "Resources/images/char/orb/layer3.png",
            modID + "Resources/images/char/orb/layer4.png",
            modID + "Resources/images/char/orb/layer5.png",
            modID + "Resources/images/char/orb/layer6.png",
            modID + "Resources/images/char/orb/layer1d.png",
            modID + "Resources/images/char/orb/layer2d.png",
            modID + "Resources/images/char/orb/layer3d.png",
            modID + "Resources/images/char/orb/layer4d.png",
            modID + "Resources/images/char/orb/layer5d.png",};
    static final String ID = makeID("TheTodo"); //TODO: Change this
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;


    public CenturionAndMystic(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, modID + "Resources/images/char/orb/vfx.png", makeSpinArray()), new SpineAnimation("centandmysResources/images/char/mainChar/tank/skeleton.atlas","centandmysResources/images/char/mainChar/tank/skeleton.json",1f));
        initializeClass(null,
                SHOULDER1,
                SHOULDER2,
                CORPSE,
                getLoadout(), 20.0F, -10.0F, 166.0F, 327.0F, new EnergyManager(0));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);

        SecondCharFields.mysticEnergyPanel.set(this, new MysticEnergyPanel(2));
        SecondCharFields.centurionEnergyPanel.set(this, new CenturionEnergyPanel(2));
    }

    private static float[] makeSpinArray() {
        return new float[]{-20.0F, 20.0F, -40.0F, 40.0F, 0.0F};
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                80, 80, 0, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            retVal.add(Strike.ID);
        }
        for (int i = 0; i < 2; i++) {
            retVal.add(Defend.ID);
        }
        retVal.add(MysticStrike.ID);
        retVal.add(MysticStrike.ID);
        retVal.add(MysticDefend.ID);
        retVal.add(MysticDefend.ID);
        retVal.add(Distract.ID);
        retVal.add(Hasten.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(TodoItem.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 8;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return CENTURION_COLOR;
    }

    @Override
    public Color getCardTrailColor() {
        return characterColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        System.out.println("YOU NEED TO SET getStartCardForEvent() in your " + getClass().getSimpleName() + " file!");
        return null;
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new CenturionAndMystic(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return characterColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return characterColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    @Override
    public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
        super.renderOrb(sb, enabled, current_x, current_y);
    }

    public void renderClassOrbs(SpriteBatch sb, float current_x, float current_y) {
        SecondCharFields.centurionEnergyPanel.get(this).render(sb, current_x, current_y);
        SecondCharFields.mysticEnergyPanel.get(this).render(sb, current_x, current_y);
    }

    @Override
    public void render(SpriteBatch sb) {
        flipHorizontal = !flipHorizontal;
        super.render(sb);
        flipHorizontal = ! flipHorizontal;
    }



    public static class Enums {
        //TODO: Change these.
        @SpireEnum
        public static AbstractPlayer.PlayerClass CENT_AND_MYS;
        @SpireEnum(name = "CENTURION_COLOR")
        public static AbstractCard.CardColor CENTURION_COLOR;
        @SpireEnum(name = "CENTURION_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    public static class OtherEnum {
        @SpireEnum(name = "CENTURION_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType CENTURION_COLOR;
    }
}
