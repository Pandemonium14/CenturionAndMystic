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

public class CobraAuraEffect extends AbstractGameEffect {

    private float x;
    private float y;
    private float vY;
    private TextureAtlas.AtlasRegion img;
    public static boolean switcher = true;

    public CobraAuraEffect() {
        this.img = ImageMaster.EXHAUST_L;// 23
        this.duration = 3.0F;// 24
        this.scale = MathUtils.random(3F, 2.8F) * Settings.scale;// 26
        color = new Color(0.15f,0f,0.15f,0.0f);

        this.x = AbstractDungeon.player.hb.cX + MathUtils.random(-AbstractDungeon.player.hb.width / 16.0F, AbstractDungeon.player.hb.width / 16.0F);// 44
        this.y = AbstractDungeon.player.hb.cY + MathUtils.random(-AbstractDungeon.player.hb.height / 16.0F, AbstractDungeon.player.hb.height / 12.0F);// 48
        this.x -= (float)this.img.packedWidth / 2.0F;// 52
        this.y -= (float)this.img.packedHeight / 2.0F;// 53
        switcher = !switcher;// 55
        this.renderBehind = true;// 57
        this.rotation = MathUtils.random(360.0F);// 58
        if (switcher) {// 59
            this.renderBehind = true;// 60
            this.vY = MathUtils.random(0.0F, 40.0F);// 61
        } else {
            this.renderBehind = false;// 63
            this.vY = MathUtils.random(0.0F, -40.0F);// 64
        }

    }// 66

    public void update() {
        if (this.duration > 1.0F) {// 70
            this.color.a = Interpolation.fade.apply(0.5F, 0.0F, this.duration - 1.0F);// 71
        } else {
            this.color.a = Interpolation.fade.apply(0.0F, 0.5F, this.duration);// 73
        }

        this.rotation += Gdx.graphics.getDeltaTime() * this.vY;// 75
        this.duration -= Gdx.graphics.getDeltaTime();// 76
        if (this.duration < 0.0F) {// 77
            this.isDone = true;// 78
        }

    }// 80

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);// 84
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);// 86
        sb.setBlendFunction(770, 771);
    }// 98

    public void dispose() {
    }// 102
}
