package CenturionAndMystic.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.Set;

public class CobraParticleEffect extends AbstractGameEffect {

    private float x;
    private float y;
    private float vY;
    private float dur_div4;
    private TextureAtlas.AtlasRegion img;

    public CobraParticleEffect() {
        this.img = ImageMaster.WOBBLY_LINE;// 20
        this.duration = MathUtils.random(1.3F, 1.8F);// 21
        this.scale = MathUtils.random(0.3F, 0.6F) * Settings.scale;// 22
        this.dur_div4 = this.duration / 2.0F;// 23
        float rColor = MathUtils.random(0.1f,0.2f);
        this.color = new Color(rColor, 0.0F, rColor, 0.0F);// 24
        this.x = AbstractDungeon.player.hb.cX + MathUtils.random(-AbstractDungeon.player.hb.width / 2.0F - 30.0F * Settings.scale, AbstractDungeon.player.hb.width / 2.0F + 30.0F * Settings.scale);// 26
        this.y = AbstractDungeon.player.hb.cY + MathUtils.random(-AbstractDungeon.player.hb.height / 2.0F - -10.0F * Settings.scale, AbstractDungeon.player.hb.height / 2.0F - 10.0F * Settings.scale);// 30
        this.x -= (float)this.img.packedWidth / 2.0F;// 34
        this.y -= (float)this.img.packedHeight / 2.0F;// 35
        this.renderBehind = MathUtils.randomBoolean(0.2F + (this.scale - 0.5F));// 36
        this.rotation = MathUtils.random(-8.0F, 8.0F);// 37
    }// 38

    public void update() {
        if (this.duration > this.dur_div4) {// 42
            this.color.a = Interpolation.fade.apply(0.3F, 0.0F, (this.duration - this.dur_div4) / this.dur_div4);// 43
        } else {
            this.color.a = Interpolation.fade.apply(0.0F, 0.3F, this.duration / this.dur_div4);// 45
        }
        this.vY += Gdx.graphics.getDeltaTime() * 25.0F * Settings.scale;// 47
        this.duration -= Gdx.graphics.getDeltaTime();// 48
        if (this.duration < 0.0F) {// 49
            this.isDone = true;// 50
        }

    }// 52

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);// 56
        sb.draw(this.img, this.x, this.y + this.vY, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 0.8F, (0.1F + (this.dur_div4 * 2.0F - this.duration) * this.scale * 0.7f) * Settings.scale, this.rotation);// 58

    }// 70

    public void dispose() {
    }// 74
}
