package com.rinkdaddy.flappyromeo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.p000v4.view.ViewCompat;

public class WallTop {
    private Bitmap bitmap;

    /* renamed from: dx */
    private int f43dx;
    private int dxa;
    private int height = 300;
    private int initialx;
    private int width = 63;

    /* renamed from: x */
    private int f44x;

    /* renamed from: y */
    private int f45y;

    public WallTop(Bitmap res, int x, int y) {
        this.f44x = x;
        this.f45y = y;
        this.bitmap = res;
        this.f43dx = -10;
        this.initialx = x;
    }

    public void update(int score, int offset) {
        this.f43dx = ((score / 4) + 10) * -1;
        this.f44x += this.f43dx;
        if (this.f44x < -50) {
            this.f44x = 900;
            this.f45y = offset;
        }
    }

    public void reset() {
        this.f44x = this.initialx;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, (float) this.f44x, (float) this.f45y, (Paint) null);
    }

    public Rect getRectangle() {
        return new Rect(this.f44x, this.f45y, this.f44x + this.width, this.height + this.f45y);
    }

    public int getX() {
        return this.f44x;
    }

    public int getHeight() {
        return this.height;
    }

    public int gety() {
        return this.f45y;
    }

    public void drawHitbox(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(getRectangle(), paint);
    }
}
