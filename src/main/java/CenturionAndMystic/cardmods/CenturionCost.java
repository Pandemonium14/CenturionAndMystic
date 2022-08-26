package CenturionAndMystic.cardmods;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.patches.SecondCharFields;
import CenturionAndMystic.secondchar.CenturionEnergyPanel;
import CenturionAndMystic.secondchar.MysticEnergyPanel;
import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.interfaces.AlternateCardCostModifier;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.Level;

public class CenturionCost extends AbstractCardModifier implements AlternateCardCostModifier {

    public static final String ID = CentAndMysMod.makeID("CenturionCost");

    private static final TextureRegion energyTexture = new TextureRegion(ImageMaster.loadImage("centandmysResources/images/512/energy.png"));

    public int cost;

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    public CenturionCost(int cost) {
        this.cost = cost;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new CenturionCost(cost);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        CardModifierManager.removeModifiersById(card, MysticCost.ID, true);
    }

    @Override
    public int getAlternateResource(AbstractCard abstractCard) {
        CenturionEnergyPanel panel = SecondCharFields.centurionEnergyPanel.get(AbstractDungeon.player);
        if (panel != null) {
            return panel.energy;
        } else {
            return 0;
        }
    }

    @Override
    public int spendAlternateCost(AbstractCard abstractCard, int i) {
        CenturionEnergyPanel panel = SecondCharFields.centurionEnergyPanel.get(AbstractDungeon.player);
        if (panel != null) {
            if (panel.energy >= i) {
                panel.energy -= i;
                return 0;
            } else {
                int leftover = i - panel.energy;
                panel.energy = 0;
                return leftover;
            }
        } else {
            return i;
        }
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return true;
    }


    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        float drawX = card.current_x - 256.0F;
        float drawY = card.current_y - 256.0F;
        if (card.cost != -2) {
            sb.draw(energyTexture, drawX, drawY, energyTexture.getRegionWidth()/2f, energyTexture.getRegionHeight()/2f, energyTexture.getRegionWidth(), energyTexture.getRegionHeight(), card.drawScale, card.drawScale, card.angle);

            Color textColor = null;
            if (cost <= resource()) {
                textColor = Color.WHITE;
            } else {
                textColor = Color.RED;
            }
            FontHelper.renderRotatedText(sb,  getFont(card), cost +"", card.current_x,
                    card.current_y, -132.0F * card.drawScale * Settings.scale,
                    192.0F * card.drawScale * Settings.scale, card.angle,
                    true, textColor);
        }
    }

    private static BitmapFont getFont(AbstractCard card) {
        FontHelper.cardEnergyFont_L.getData().setScale(card.drawScale);
        return FontHelper.cardEnergyFont_L;
    }

    @Override
    public void onUpdate(AbstractCard card) {
        this.cost = card.cost;
    }

    @Override
    public boolean canSplitCost(AbstractCard card) {
        return true;
    }

    private int resource() {
        if (AbstractDungeon.player == null) return 5;
        CenturionEnergyPanel panel = SecondCharFields.centurionEnergyPanel.get(AbstractDungeon.player);
        if (panel != null) {
            return panel.energy + EnergyPanel.totalCount;
        } else {
            return 0;
        }
    }
}
