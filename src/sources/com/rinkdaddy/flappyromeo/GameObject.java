package com.rinkdaddy.flappyromeo;

public abstract class GameObject {

    /* renamed from: dx */
    protected int f26dx;

    /* renamed from: dy */
    protected int f27dy;
    protected int height;
    protected int width;

    /* renamed from: x */
    protected int f28x;

    /* renamed from: y */
    protected int f29y;

    public void setX(int x) {
        this.f28x = x;
    }

    public void setY(int y) {
        this.f29y = y;
    }

    public int getX() {
        return this.f28x;
    }

    public int getY() {
        return this.f29y;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}
