package com.rinkdaddy.flappyromeo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.p000v4.view.ViewCompat;

public class WallBot {
    private Bitmap bitmap;

    /* renamed from: dx */
    private int f40dx;
    private int dxa;
    private int height = 300;
    private int initialx;
    private int width = 63;

    /* renamed from: x */
    private int f41x;

    /* renamed from: y */
    private int f42y;

    public WallBot(Bitmap res, int x, int y) {
        this.f41x = x;
        this.f42y = y;
        this.bitmap = res;
        this.f40dx = -10;
        this.initialx = x;
    }

    public void update(int score, int offset) {
        this.f40dx = ((score / 4) + 10) * -1;
        this.f41x += this.f40dx;
        if (this.f41x < -50) {
            this.f41x = 900;
            this.f42y = this.height + offset + 150;
        }
    }

    public void reset() {
        this.f41x = this.initialx;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, (float) this.f41x, (float) this.f42y, (Paint) null);
    }

    public int getX() {
        return this.f41x;
    }

    public Rect getRectangle() {
        return new Rect(this.f41x, this.f42y, this.f41x + this.width, GamePanel.HEIGHT);
    }

    public void drawHitbox(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(getRectangle(), paint);
    }
}
