package com.rinkdaddy.flappyromeo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Hearts extends GameObject {
    private int height;
    private Bitmap image;
    Matrix matrix = new Matrix();

    /* renamed from: r */
    public int f34r;
    private int width;

    /* renamed from: x */
    private int f35x;

    /* renamed from: y */
    private int f36y;

    public Hearts(Bitmap res, int ex, int ey, int w, int h) {
        this.f35x = ex;
        this.f36y = ey;
        this.image = res;
        this.width = w;
        this.height = h;
    }

    public int getX() {
        return this.f35x;
    }

    public void update() {
        this.f35x -= 10;
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = Bitmap.createScaledBitmap(this.image, this.width, this.height, true);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawBitmap(bitmap, (float) ((int) (((double) this.f35x) - (((double) this.width) / 2.0d))), (float) ((int) (((double) this.f36y) - (((double) this.height) / 2.0d))), paint);
    }
}
