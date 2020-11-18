package com.rinkdaddy.flappyromeo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.p000v4.view.ViewCompat;

public class Grass extends GameObject {

    /* renamed from: dx */
    private int f31dx = -5;
    private int height;
    private Bitmap image;
    private int width;

    /* renamed from: x */
    private int f32x;

    /* renamed from: y */
    private int f33y;

    public Grass(Bitmap res) {
        this.image = res;
        this.height = 50;
        this.width = GamePanel.WIDTH;
    }

    public void update() {
        this.f32x += this.f31dx;
        this.f33y = 430;
        if (this.f32x < -856) {
            this.f32x = 0;
            this.f33y = 430;
        }
    }

    public void drawHitbox(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(getRectangle(), paint);
    }

    public Rect getRectangle() {
        return new Rect(0, GamePanel.HEIGHT, GamePanel.WIDTH, 475);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, (float) this.f32x, 430.0f, (Paint) null);
        if (this.f32x < 0) {
            canvas.drawBitmap(this.image, (float) (this.f32x + GamePanel.WIDTH), 430.0f, (Paint) null);
        }
    }
}
