package CenturionAndMystic.stances;

import CenturionAndMystic.CentAndMysMod;
import CenturionAndMystic.effects.CobraAuraEffect;
import CenturionAndMystic.effects.CobraParticleEffect;
import CenturionAndMystic.powers.VenomPower;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;

public class CobraStance extends AbstractStance {

    public static final String STANCEID = CentAndMysMod.makeID("CobraStance");
    private static final StanceStrings strings = CardCrawlGame.languagePack.getStanceString(STANCEID);

    public CobraStance() {
        ID = STANCEID;
        name = strings.NAME;
        updateDescription();
    }

    @Override
    public void onEnterStance() {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,AbstractDungeon.player, new VenomPower(m, 4)));
        }
    }

    @Override
    public void updateDescription() {
        description = strings.DESCRIPTION[0];
    }

    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {// 46
            this.particleTimer -= Gdx.graphics.getDeltaTime();// 48
            if (this.particleTimer < 0.0F) {// 49
                this.particleTimer = 0.03F;// 50
                AbstractDungeon.effectsQueue.add(new CobraParticleEffect());// 51
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();// 56
        if (this.particleTimer2 < 0.0F) {// 57
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);// 58
            AbstractDungeon.effectsQueue.add(new CobraAuraEffect());// 59
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
    }
}
