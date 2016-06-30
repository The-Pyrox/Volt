package com.teampyroxinc.volt.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;


public class Ball {
    private static final int GRAVITY = -15;
    private Rectangle bounds;
    private Vector3 position;
    private Vector3 velocity;
    private static final int MOVEMENT = 100;
    private Texture ball;
    private Sound bounce;
    private long score;

    public Ball(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        ball = new Texture("ball.png");
        bounds = new Rectangle(x,y,ball.getWidth(),ball.getHeight());
        bounce = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        score = 0;

    }
    public void update(float dt){
        if (position.y > 0)
            velocity.add(0, GRAVITY ,0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y , 0);
        if (position.y <= 36)
            position.y = 36;
        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }

    public Texture getBall() {
        return ball;
    }

    public Vector3 getPosition() {
        return position;
    }
    public void jump(){
        velocity.y = 250;
        bounce.play(0.5f);

    }

    public Rectangle getBounds(){
        return bounds;
    }

    public long getScore() {
        return score;
    }

    public void dispose(){
        ball.dispose();

    }
}

