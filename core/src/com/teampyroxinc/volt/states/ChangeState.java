package com.teampyroxinc.volt.states;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;


public class ChangeState extends State {
    private BitmapFont font;
    private SpriteBatch sb;
    private int i;
    public ChangeState(GameStateManager gsm) {
        super(gsm);
        sb = new SpriteBatch();
        i=0;
        cam.setToOrtho(false,480/2,800/2 );
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font.getData().setScale(0.8f);


    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        font.draw(sb,"Change Log" +
                "\n" +
                "\nVersion 1.1:" +
                "\n-Added More Options" +
                "\n-Enhanced Gameplay" +
                "\n-Bugs solved" +
                "\n" +
                "\nVersion 1.0:" +
                "\nBasic Initial Game" +
                "\n" +
                "\n TAP TO RETURN",cam.position.x - cam.viewportWidth/2 ,(cam.position.y * 7)/4 );

        if (i>80){
            changestate();
        }
        sb.end();
        i++;

    }

    @Override
    public void dispose() {

    }
    public void changestate(){
        if (Gdx.input.isTouched()){
            gsm.set(new MenuState(gsm));
        }
    }
}

