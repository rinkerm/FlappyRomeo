package com.rinkdaddy.flappyromeo;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static final int HEIGHT = 480;
    public static final int MOVE_SPEED = -5;
    public static final int WIDTH = 856;

    /* renamed from: bg */
    private Background f30bg;
    WallBot bot1;
    WallBot bot2;
    WallBot bot3;
    private int deathposx;
    private int deathposy;
    private boolean ended;
    private Grass grass;
    private ArrayList<Hearts> heart;
    private long heartStartTime;
    private int highestscore;
    private int lastscore;
    private Player player;
    private ArrayList<Player> players;
    private boolean playing;
    private Background quote;
    public boolean readytoplay;
    public int score;
    private long scoreStartTime;
    public boolean starting;
    public boolean starting1;
    public boolean starting2;
    public boolean starting3;
    private Background thinking;
    private Background thoughts;
    private MainThread thread;
    private Background title;
    WallTop top1;
    WallTop top2;
    WallTop top3;
    private long wallStartTime;
    private ArrayList<WallBot> wallbottoms;
    private ArrayList<WallTop> walltops;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        int counter = 0;
        while (retry && counter < 1000) {
            counter++;
            try {
                this.thread.setRunning(false);
                this.thread.join();
                retry = false;
                this.thread = null;
            } catch (Exception e) {
            }
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        this.thread = new MainThread(getHolder(), this);
        this.starting = true;
        this.starting1 = false;
        this.starting2 = false;
        this.starting3 = false;
        this.readytoplay = false;
        this.f30bg = new Background(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.sky));
        this.quote = new Background(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.quote));
        this.thinking = new Background(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.thinking));
        this.thoughts = new Background(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.thoughts));
        this.title = new Background(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.title));
        this.grass = new Grass(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.grass));
        this.player = new Player(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.leo), 128, 78, 3);
        this.heart = new ArrayList<>();
        this.players = new ArrayList<>();
        ArrayList<Player> arrayList = this.players;
        Player player2 = new Player(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.leo), 128, 78, 3);
        this.player = player2;
        arrayList.add(player2);
        this.heartStartTime = System.nanoTime();
        this.playing = false;
        this.thread.setRunning(true);
        this.thread.start();
        this.score = 0;
        this.highestscore = 0;
        this.top1 = new WallTop(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.wall), 900, 0);
        this.bot1 = new WallBot(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.wall), this.top1.getX(), this.top1.getHeight() + this.top1.gety() + 150);
        this.top2 = new WallTop(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.wall), 1500, -90);
        this.bot2 = new WallBot(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.wall), this.top2.getX(), this.top2.getHeight() + this.top2.gety() + 150);
        this.top3 = new WallTop(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.wall), 2100, -50);
        this.bot3 = new WallBot(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.wall), this.top3.getX(), this.top3.getHeight() + this.top3.gety() + 150);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            if (this.playing) {
                this.player.setUp();
            }
            if (!this.starting && !this.starting1 && !this.starting2 && !this.starting3 && this.readytoplay) {
                this.playing = true;
                this.ended = false;
                this.player.setUp();
            }
            if (!this.starting && !this.starting1 && !this.starting2 && this.starting3 && !this.readytoplay) {
                this.starting3 = false;
                this.readytoplay = true;
            }
            if (!this.starting && !this.starting1 && this.starting2 && !this.starting3 && !this.readytoplay) {
                this.starting3 = true;
                this.starting2 = false;
            }
            if (!this.starting && this.starting1 && !this.starting2 && !this.starting3 && !this.readytoplay) {
                this.starting2 = true;
                this.starting1 = false;
            }
            if (!this.starting || this.starting1 || this.starting2 || this.starting3 || this.readytoplay) {
                return true;
            }
            this.starting1 = true;
            this.starting = false;
            return true;
        } else if (event.getAction() != 1) {
            return super.onTouchEvent(event);
        } else {
            return true;
        }
    }

    public void update() {
        int offset1 = (int) (Math.random() * 200.0d);
        int offset2 = (int) (Math.random() * 200.0d);
        int offset3 = (int) (Math.random() * 200.0d);
        if (this.playing) {
            collide(this.player.getRectangle(), this.grass.getRectangle());
            collide(this.player.getRectangle(), this.top1.getRectangle());
            collide(this.player.getRectangle(), this.top2.getRectangle());
            collide(this.player.getRectangle(), this.top3.getRectangle());
            collide(this.player.getRectangle(), this.bot1.getRectangle());
            collide(this.player.getRectangle(), this.bot2.getRectangle());
            collide(this.player.getRectangle(), this.bot3.getRectangle());
            this.f30bg.update();
            this.grass.update();
            this.player.update();
            if ((System.nanoTime() - this.heartStartTime) / 1000000 > 30) {
                this.heart.add(new Hearts(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.heart), this.player.getX(), (int) ((((double) this.player.getY()) - (50.0d / 2.0d)) + (Math.random() * 50.0d)), 12, 12));
                this.heartStartTime = System.nanoTime();
            }
            this.top1.update(this.score, offset1 * -1);
            this.bot1.update(this.score, offset1 * -1);
            this.top2.update(this.score, offset2 * -1);
            this.bot2.update(this.score, offset2 * -1);
            this.top3.update(this.score, offset3 * -1);
            this.bot3.update(this.score, offset3 * -1);
            if ((System.nanoTime() - this.scoreStartTime) / 1000000 > 1000) {
                this.score++;
                this.scoreStartTime = System.nanoTime();
            }
            for (int i = 0; i < this.heart.size(); i++) {
                this.heart.get(i).update();
                if (this.heart.get(i).getX() < -10) {
                    this.heart.remove(i);
                }
            }
        }
    }

    public void collide(Rect a, Rect b) {
        int ax = a.left;
        int aw = a.right;
        int ay = a.top;
        int ah = a.bottom;
        int bx = b.left;
        int bw = b.right;
        int by = b.bottom;
        int bh = b.top;
        boolean left = false;
        boolean right = false;
        boolean top = false;
        boolean bottom = false;
        if (ay < 0) {
            ay = 0;
        }
        if (by > 480) {
            by = HEIGHT;
        }
        if (bh < 0) {
            bh = 0;
        }
        if (ah > 480) {
            ah = HEIGHT;
        }
        if (ax > bx && ax < bw) {
            left = true;
        }
        if (ay <= by && ay >= bh) {
            top = true;
        }
        if (aw < bw && aw > bx) {
            right = true;
        }
        if (ah <= bh && ah >= by) {
            bottom = true;
        }
        if ((left && top) || ((bottom && right) || ((bottom && left) || (top && right)))) {
            this.deathposx = this.players.get(0).f28x;
            this.deathposy = this.players.get(0).f29y;
            this.ended = true;
            restart();
        }
    }

    public void restart() {
        this.top1.reset();
        this.top2.reset();
        this.top3.reset();
        this.bot1.reset();
        this.bot2.reset();
        this.bot3.reset();
        this.players.get(0).resetY();
        this.playing = false;
        this.lastscore = this.score;
        if (this.score > this.highestscore) {
            this.highestscore = this.score;
        }
        this.score = 0;
        for (int i = 0; i < this.walltops.size(); i++) {
            this.walltops.remove(i);
            this.wallbottoms.remove(i);
        }
    }

    public void draw(Canvas canvas) {
        if (canvas != null) {
            int savedState = canvas.save();
            canvas.scale(((float) getWidth()) / 856.0f, ((float) getHeight()) / 480.0f);
            if (this.starting) {
                this.quote.draw(canvas);
            }
            if (this.starting1) {
                this.thinking.draw(canvas);
            }
            if (this.starting2) {
                this.thoughts.draw(canvas);
            }
            if (this.starting3) {
                this.title.draw(canvas);
            }
            Paint paint = new Paint();
            paint.setColor(-1);
            paint.setTextSize(20.0f);
            if (this.readytoplay) {
                this.f30bg.draw(canvas);
                this.grass.draw(canvas);
                canvas.drawText("Score: " + this.score, 720.0f, 25.0f, paint);
                canvas.drawText("Best Score: " + this.highestscore, 20.0f, 25.0f, paint);
                for (int i = 0; i < this.heart.size(); i++) {
                    this.heart.get(i).draw(canvas);
                }
                this.player.draw(canvas);
                this.top1.drawHitbox(canvas);
                this.bot1.drawHitbox(canvas);
                this.top2.drawHitbox(canvas);
                this.bot2.drawHitbox(canvas);
                this.top3.drawHitbox(canvas);
                this.bot3.drawHitbox(canvas);
                this.top1.draw(canvas);
                this.bot1.draw(canvas);
                this.top2.draw(canvas);
                this.bot2.draw(canvas);
                this.top3.draw(canvas);
                this.bot3.draw(canvas);
                canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), C0321R.C0322drawable.juliet), 756.0f, 0.0f, (Paint) null);
            }
            if (!this.playing && !this.ended) {
                canvas.drawText("Tap to play", 400.0f, 470.0f, paint);
            }
            if (this.ended) {
                canvas.drawText("Your score was: " + this.lastscore, 400.0f, 440.0f, paint);
                canvas.drawText("Tap to play again", 400.0f, 470.0f, paint);
            }
            canvas.restoreToCount(savedState);
        }
    }
}
