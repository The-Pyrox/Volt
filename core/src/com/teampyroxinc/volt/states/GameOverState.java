package com.teampyroxinc.volt.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState extends State {

    private Texture background;
    private BitmapFont font_title,font;
    private GlyphLayout glyph,glyph_title;
    private float title_width,width;
    private SpriteBatch sb;
    private String title,tag;
    public PlayState ps;
    private int score;

    public GameOverState(GameStateManager gsm) {

        super(gsm);
        background = new Texture("background.png");
        sb = new SpriteBatch();
        ps = new PlayState(gsm);
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"),false);
        font_title = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font_title.getData().setScale(2f);
        title = new String("GAME OVER");
        tag = new String("Your Score Is");
        glyph = new GlyphLayout();
        glyph_title = new GlyphLayout();
        glyph_title.setText(font_title,title);
        glyph.setText(font,tag);
        title_width = glyph_title.width;
        width = glyph.width;




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
        sb.begin();
        sb.draw(background,0,0);
        font_title.draw(sb,title,cam.position.x - title_width / 2,cam.position.y * 3);
        font.draw(sb,tag,cam.position.x - width / 2,cam.position.y);
        font.draw(sb,Integer.toString(getScore()),cam.position.x,(cam.position.y * 3) / 4 );
        sb.end();
    }

    @Override
    public void dispose() {
        sb.dispose();
        background.dispose();
        ps.dispose();
        font.dispose();
        font_title.dispose();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
