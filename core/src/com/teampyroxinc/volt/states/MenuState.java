package com.teampyroxinc.volt.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.teampyroxinc.volt.Volt;

public class MenuState extends State{

    private Texture background;
    private BitmapFont font_bolt,font;
    private SpriteBatch sb;
    private GlyphLayout glyphtitle;
    private String title;
    private float title_width;
    private Stage stage;
    private TextureAtlas buttonsAtlas;
    private Skin buttonSkin;
    private TextButton button_play,button_high,button_exit,button_credits,button_tag;

    public MenuState(final GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Volt.WIDTH / 2, Volt.HEIGHT / 2);
        background = new Texture("background.png");
        font_bolt = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false );
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"), false);
        font.getData().setScale(5f);
        font_bolt.getData().setScale(12f);


        glyphtitle = new GlyphLayout();
        title = new String("VOLT");
        glyphtitle.setText(font_bolt,title);
        title_width = glyphtitle.width;
        buttonsAtlas = new TextureAtlas("buttons.pack");
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas);
        stage = new Stage();


        Gdx.input.setInputProcessor(stage);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = buttonSkin.getDrawable("buttons");
        style.down = buttonSkin.getDrawable("buttons");
        style.font = font;

        TextButton.TextButtonStyle style_title = new TextButton.TextButtonStyle();
        style_title.up = buttonSkin.getDrawable("buttons");
        style_title.down = buttonSkin.getDrawable("buttons");
        style_title.font = font_bolt;

        button_high = new TextButton(title, style_title);
        button_high.setWidth(300);
        button_high.setHeight(100);
        button_high.setPosition(cam.position.x * 3,cam.position.y * 7);
        stage.addActor(button_high);

        button_tag = new TextButton("TEAM PYROX INC.", style);
        button_tag.setWidth(300);
        button_tag.setHeight(100);
        button_tag.setPosition(cam.position.x * 3,(cam.position.y * 1) / 2 );
        stage.addActor(button_tag);

        button_play = new TextButton("PLAY", style);
        button_play.setWidth(300);
        button_play.setHeight(100);
        button_play.setPosition(cam.position.x * 3,cam.position.y * 4);
        stage.addActor(button_play);
        button_play.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new LevelState(gsm));
                return true;
            }
        });

        button_credits = new TextButton("CREDITS", style);
        button_credits.setWidth(300);
        button_credits.setHeight(100);
        button_credits.setPosition(cam.position.x * 3,cam.position.y * 3);
        stage.addActor(button_credits);
        button_credits.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new CreditState(gsm));
                return true;
            }
        });



        button_exit = new TextButton("EXIT", style);
        button_exit.setWidth(300);
        button_exit.setHeight(100);
        button_exit.setPosition(cam.position.x * 3,cam.position.y * 2);
        stage.addActor(button_exit);
        button_exit.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });

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
        stage.act();
        sb.begin();
        stage.draw();
        sb.end();
    }
    @Override
    public void dispose() {
        stage.dispose();

        font_bolt.dispose();
        font.dispose();
        buttonSkin.dispose();
        buttonsAtlas.dispose();
    }
}

