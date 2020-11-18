package com.rinkdaddy.flappyromeo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Background {

    /* renamed from: dx */
    private int f23dx = -5;
    private Bitmap image;

    /* renamed from: x */
    private int f24x;

    /* renamed from: y */
    private int f25y;

    public Background(Bitmap res) {
        this.image = res;
    }

    public void update() {
        this.f24x += this.f23dx;
        if (this.f24x < -856) {
            this.f24x = 0;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, (float) this.f24x, (float) this.f25y, (Paint) null);
        if (this.f24x < 0) {
            canvas.drawBitmap(this.image, (float) (this.f24x + GamePanel.WIDTH), (float) this.f25y, (Paint) null);
        }
    }
}
