package com.rinkdaddy.flappyromeo;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    public static Canvas canvas;
    private int FPS = 30;
    private double averageFPS;
    private GamePanel gamePanel;
    private boolean running;
    private SurfaceHolder surfaceHolder;

    public MainThread(SurfaceHolder surfaceHolder2, GamePanel gamePanel2) {
        this.surfaceHolder = surfaceHolder2;
        this.gamePanel = gamePanel2;
    }

    public void run() {
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = (long) (1000 / this.FPS);
        while (this.running) {
            long startTime = System.nanoTime();
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (this.surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
                if (canvas != null) {
                    try {
                        this.surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    sleep(targetTime - ((System.nanoTime() - startTime) / 1000000));
                } catch (Exception e2) {
                }
                totalTime += System.nanoTime() - startTime;
                frameCount++;
                if (frameCount == this.FPS) {
                    this.averageFPS = (double) (1000 / ((totalTime / ((long) frameCount)) / 1000000));
                    frameCount = 0;
                    totalTime = 0;
                }
            } catch (Exception e3) {
                if (canvas != null) {
                    try {
                        this.surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                try {
                    sleep(targetTime - ((System.nanoTime() - startTime) / 1000000));
                } catch (Exception e5) {
                }
                totalTime += System.nanoTime() - startTime;
                frameCount++;
                if (frameCount == this.FPS) {
                    this.averageFPS = (double) (1000 / ((totalTime / ((long) frameCount)) / 1000000));
                    frameCount = 0;
                    totalTime = 0;
                }
            } catch (Throwable th) {
                if (canvas != null) {
                    try {
                        this.surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                }
                try {
                    sleep(targetTime - ((System.nanoTime() - startTime) / 1000000));
                } catch (Exception e7) {
                }
                long totalTime2 = totalTime + (System.nanoTime() - startTime);
                int frameCount2 = frameCount + 1;
                if (frameCount2 == this.FPS) {
                    this.averageFPS = (double) (1000 / ((totalTime2 / ((long) frameCount2)) / 1000000));
                }
                throw th;
            }
        }
    }

    public void setRunning(boolean bool) {
        this.running = bool;
    }
}
