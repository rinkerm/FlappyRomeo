package com.rinkdaddy.flappyromeo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.p000v4.view.ViewCompat;

public class Player extends GameObject {
    private Animation animation = new Animation();
    private double dya;
    private Bitmap spritesheet;
    private long startTime;

    /* renamed from: up */
    private boolean f37up;

    public Player(Bitmap res, int w, int h, int numFrames) {
        this.f28x = 100;
        this.f29y = 240;
        this.f27dy = 0;
        this.dya = 1.1d;
        this.height = h;
        this.width = w;
        Bitmap[] image = new Bitmap[numFrames];
        this.spritesheet = res;
        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(this.spritesheet, this.width * i, 0, this.width, this.height);
        }
        this.animation.setFrames(image);
        this.animation.setDelay(30);
        this.startTime = System.nanoTime();
    }

    public Rect getRectangle() {
        return new Rect(this.f28x, this.f29y - 24, this.f28x + 60, this.f29y + 39);
    }

    public void setUp() {
        this.f27dy = -10;
    }

    public void sety(int y) {
        this.f29y = y;
    }

    public void update() {
        this.animation.update();
        this.f27dy = (int) (((double) this.f27dy) + this.dya);
        if (this.f27dy > 14) {
            this.f27dy = 14;
        }
        if (this.f27dy < -14) {
            this.f27dy = -14;
        }
        this.f29y += this.f27dy * 2;
        if (this.f29y > 440) {
            this.f29y = 440;
        }
        if (this.f29y < 0) {
            this.f29y = 0;
        }
    }

    public void drawHitbox(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(getRectangle(), paint);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.animation.getImage(), (float) ((int) (((double) this.f28x) - (((double) this.width) / 2.0d))), (float) ((int) (((double) this.f29y) - (((double) this.height) / 2.0d))), (Paint) null);
    }

    public void resetY() {
        this.f29y = 240;
    }

    public void resetDYA() {
        this.dya = 0.0d;
    }
}
