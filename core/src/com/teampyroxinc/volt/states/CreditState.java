package com.teampyroxinc.volt.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CreditState extends State {
    private BitmapFont font_title,font;
    private SpriteBatch sb;
    private GlyphLayout glyph_title,glystr1,glystr2,glystr3,glystr4,glystr5;
    private String title,str1,str2,str3,str4,str5;
    private float wid1,wid2,wid3,wid4,wid5,wid6;

    public CreditState(final GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false,480/2,800/2 );
        sb = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font_title = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font_title.getData().setScale(2f);

        title = new String("CREDITS");
        str1 = new String("TEAM PYROX INC.");
        str2 = new String("Version 1.0");
        str3 = new String("Developer Details:");
        str4 = new String("POOJAN RATHOD");
        str5 = new String("Tap To Start");

        glyph_title = new GlyphLayout();
        glystr1 = new GlyphLayout();
        glystr2 = new GlyphLayout();
        glystr3 = new GlyphLayout();
        glystr4 = new GlyphLayout();
        glystr5 = new GlyphLayout();

        glyph_title.setText(font_title,title);
        glystr1.setText(font,str1);
        glystr2.setText(font,str2);
        glystr3.setText(font,str3);
        glystr4.setText(font,str4);
        glystr5.setText(font,str5);

        wid1 = glyph_title.width;
        wid2 = glystr1.width;
        wid3 = glystr2.width;
        wid4 = glystr3.width;
        wid5 = glystr4.width;
        wid6 = glystr5.width;

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
        font_title.draw(sb,title,cam.position.x - wid1 / 2,cam.position.y  * 2);
        font.draw(sb,str1 ,cam.position.x - wid2 /2,(cam.position.y * 3) / 2);
        font.draw(sb,str2,cam.position.x - wid3 / 2,(cam.position.y* 5) / 4);
        font.draw(sb,str3,cam.position.x - wid4 / 2,cam.position.y );
        font.draw(sb,str4,cam.position.x - wid5 /2,(cam.position.y * 3)/ 4);
        font.draw(sb,str5,cam.position.x - wid6 /2,cam.position.y / 4);
        sb.end();

    }

    @Override
    public void dispose() {
        font.dispose();
        font_title.dispose();
        sb.dispose();
    }
}
