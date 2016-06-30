package com.teampyroxinc.volt.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teampyroxinc.volt.Volt;

public class GameOverState extends State {
    private BitmapFont font,font_title;
    private SpriteBatch sb;
    private GlyphLayout glytitle,glytag;
    private float wtitle,wtag;
    public int FINAL_SCORE ;




    public GameOverState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Volt.WIDTH / 2, Volt.HEIGHT / 2);
        sb = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font_title = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font_title.getData().setScale(2f);



        glytitle = new GlyphLayout();
        glytag = new GlyphLayout();
        glytitle.setText(font_title,"GAME OVER");
        glytag.setText(font,"Your Score Is");

        wtitle = glytitle.width;
        wtag = glytag.width;


    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
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
        font_title.draw(sb,"GAME OVER",cam.position.x - wtitle / 2,(cam.position.y * 3)/ 2 );
        font.draw(sb,"Your Score Is",cam.position.x - wtag / 2,cam.position.y );
        font.draw(sb,Integer.toString(getFINAL_SCORE()),cam.position.x,cam.position.y /2);
        sb.end();

    }

    @Override
    public void dispose() {
        sb.dispose();


    }


    public Integer getFINAL_SCORE() {
        return FINAL_SCORE;
    }

    public void setFINAL_SCORE(Integer FINAL_SCORE) {
        this.FINAL_SCORE = FINAL_SCORE;
    }
}
