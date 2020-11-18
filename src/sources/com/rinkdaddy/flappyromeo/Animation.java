package com.rinkdaddy.flappyromeo;

import android.graphics.Bitmap;

public class Animation {
    private int currentFrame;
    private long delay;
    private Bitmap[] frames;
    private boolean playedOnce;
    private long startTime;

    public void setFrames(Bitmap[] frames2) {
        this.frames = frames2;
        this.currentFrame = 0;
        this.startTime = System.nanoTime();
    }

    public void setDelay(long d) {
        this.delay = d;
    }

    public void setFrame(int i) {
        this.currentFrame = i;
    }

    public void update() {
        if ((System.nanoTime() - this.startTime) / 1000000 > this.delay) {
            this.currentFrame++;
            this.startTime = System.nanoTime();
        }
        if (this.currentFrame == this.frames.length) {
            this.currentFrame = 0;
            this.playedOnce = true;
        }
    }

    public Bitmap getImage() {
        return this.frames[this.currentFrame];
    }

    public int getFrame() {
        return this.currentFrame;
    }

    public boolean playedOnce() {
        return this.playedOnce;
    }
}
