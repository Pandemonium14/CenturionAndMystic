package CenturionAndMystic.cards;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.cardmods.CenturionCost;
import CenturionAndMystic.cardmods.MysticCost;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CenturionAndMystic.CenturionAndMystic;
import CenturionAndMystic.util.CardArtRoller;

import java.util.*;

import static CenturionAndMystic.CentAndMysMod.*;
import static CenturionAndMystic.util.Wiz.atb;
import static CenturionAndMystic.util.Wiz.att;

public abstract class AbstractEasyCard extends CustomCard {

    protected final CardStrings cardStrings;

    public static final String mysticDescriptor = "Mystic";
    public static final String centurionDescriptor = "Centurion";

    public int secondMagic;
    public int baseSecondMagic;
    public boolean upgradedSecondMagic;
    public boolean isSecondMagicModified;

    public int secondDamage;
    public int baseSecondDamage;
    public boolean upgradedSecondDamage;
    public boolean isSecondDamageModified;

    private boolean needsArtRefresh = false;

    public AbstractEasyCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, CenturionAndMystic.Enums.CENTURION_COLOR);
    }

    public AbstractEasyCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, "", getCardTextureString(cardID.replace(modID + ":", ""), type),
                cost, "", type, color, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        rawDescription = cardStrings.DESCRIPTION;
        name = originalName = cardStrings.NAME;
        initializeTitle();
        initializeDescription();

        if (textureImg.contains("ui/missing.png")) {
            if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty()) {
                CardArtRoller.computeCard(this);
            } else
                needsArtRefresh = true;
        }
    }

    @Override
    protected Texture getPortraitImage() {
        if (textureImg.contains("ui/missing.png")) {
            return CardArtRoller.getPortraitTexture(this);
        }
        else {
            return super.getPortraitImage();
        }
    }

    public static String getCardTextureString(final String cardName, final AbstractCard.CardType cardType) {
        String textureString;

        switch (cardType) {
            case ATTACK:
            case POWER:
            case SKILL:
                textureString = makeImagePath("cards/" + cardName + ".png");
                break;
            default:
                textureString = makeImagePath("ui/missing.png");
                break;
        }

        FileHandle h = Gdx.files.internal(textureString);
        if (!h.exists()) {
            textureString = makeImagePath("ui/missing.png");
        }
        return textureString;
    }

    @Override
    public void applyPowers() {
        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.applyPowers();

            secondDamage = damage;
            baseDamage = tmp;

            super.applyPowers();

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        } else super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.calculateCardDamage(mo);

            secondDamage = damage;
            baseDamage = tmp;

            super.calculateCardDamage(mo);

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        } else super.calculateCardDamage(mo);
    }

    public void resetAttributes() {
        super.resetAttributes();
        secondMagic = baseSecondMagic;
        isSecondMagicModified = false;
        secondDamage = baseSecondDamage;
        isSecondDamageModified = false;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedSecondMagic) {
            secondMagic = baseSecondMagic;
            isSecondMagicModified = true;
        }
        if (upgradedSecondDamage) {
            secondDamage = baseSecondDamage;
            isSecondDamageModified = true;
        }
    }

    protected void upgradeSecondMagic(int amount) {
        baseSecondMagic += amount;
        secondMagic = baseSecondMagic;
        upgradedSecondMagic = true;
    }

    protected void upgradeSecondDamage(int amount) {
        baseSecondDamage += amount;
        secondDamage = baseSecondDamage;
        upgradedSecondDamage = true;
    }

    protected void uDesc() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
        }
    }

    public abstract void upp();

    public void update() {
        super.update();
        if (needsArtRefresh) {
            CardArtRoller.computeCard(this);
        }
    }

    // These shortcuts are specifically for cards. All other shortcuts that aren't specifically for cards can go in Wiz.
    protected void dmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    protected void dmgTop(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        att(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    protected void allDmg(AbstractGameAction.AttackEffect fx) {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    protected void altDmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, secondDamage, damageTypeForTurn), fx));
    }

    protected void blck() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    public String cardArtCopy() {
        return null;
    }

    protected void upMagic(int x) {
        upgradeMagicNumber(x);
    }

    protected void upSecondMagic(int x) {
        upgradeSecondMagic(x);
    }

    protected void upSecondDamage(int x) {
        upgradeSecondDamage(x);
    }

    protected void setMysticCost(int cost) {
        CardModifierManager.addModifier(this, new MysticCost(cost));
        switch (type) {
            case ATTACK:
                setBackgroundTexture(CentAndMysMod.makeImagePath("512/attackM.png"),CentAndMysMod.makeImagePath("1024/attackM.png"));
                break;
            case SKILL:
                setBackgroundTexture(CentAndMysMod.makeImagePath("512/skillM.png"),CentAndMysMod.makeImagePath("1024/skillM.png"));
                break;
            case POWER:
                setBackgroundTexture(CentAndMysMod.makeImagePath("512/powerM.png"),CentAndMysMod.makeImagePath("1024/powerM.png"));
                break;
        }
    }

    protected void setCenturionCost(int cost) {
        CardModifierManager.addModifier(this, new CenturionCost(cost));
        switch (type) {
            case ATTACK:
                setBackgroundTexture(CentAndMysMod.makeImagePath("512/attackC.png"),CentAndMysMod.makeImagePath("1024/attackC.png"));
                break;
            case SKILL:
                setBackgroundTexture(CentAndMysMod.makeImagePath("512/skillC.png"),CentAndMysMod.makeImagePath("1024/skillC.png"));
                break;
            case POWER:
                setBackgroundTexture(CentAndMysMod.makeImagePath("512/powerC.png"),CentAndMysMod.makeImagePath("1024/powerC.png"));
                break;
        }
    }

    protected List<String> mysticDescriptor() {
        List<String> list = new ArrayList<>();
        list.add(mysticDescriptor);
        return list;
    }

    protected List<String> centurionDescriptor() {
        List<String> list = new ArrayList<>();
        list.add(centurionDescriptor);
        return list;
    }

    @Override
    public List<String> getCardDescriptors() {
        if (CardModifierManager.hasModifier(this, MysticCost.ID)) {
            return mysticDescriptor();
        } else if (CardModifierManager.hasModifier(this, CenturionCost.ID)) {
            return centurionDescriptor();
        } else {
            return new ArrayList<>();
        }
    }

    protected static boolean isPlayerEmpowered() {
        return CentAndMysMod.isPlayerInfused();
    }
}

