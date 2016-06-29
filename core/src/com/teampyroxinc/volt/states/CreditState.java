package com.teampyroxinc.volt.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class CreditState extends State {
    private Texture background;
    private BitmapFont font_title,font;
    private GlyphLayout glyph_title;
    private String title,str1,str2;
    private float wid1,wid2;

    public CreditState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font_title = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font_title.getData().setScale(2f);
        title = new String("CREDITS");
        str1 = new String("TEAM PYROX INC.");
        str2 = new String("Version 1.0");

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        font_title.draw(sb,title,cam.position.x,cam.position.y * 2);
        font.draw(sb,str1,cam.position.x,cam.position.y);
        font.draw(sb,str2,cam.position.x,cam.position.y / 2);

        sb.end();

    }

    @Override
    public void dispose() {

    }
}
