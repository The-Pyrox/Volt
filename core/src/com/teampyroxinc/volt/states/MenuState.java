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
    private TextButton button_play,button_high,button_exit,button_credits;

    public MenuState(final GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Volt.WIDTH / 2, Volt.HEIGHT / 2);
        background = new Texture("background.png");
        font_bolt = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false );
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"), false);
        font.getData().setScale(5f);

        font_bolt.getData().setScale(3f,3f);
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

        button_play = new TextButton("PLAY", style);
        button_play.setWidth(300);
        button_play.setHeight(100);
        button_play.setPosition(cam.position.x * 3,cam.position.y * 5);
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
        button_credits.setPosition(cam.position.x * 3,cam.position.y * 4);
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
        button_exit.setPosition(cam.position.x * 3,cam.position.y * 3);
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
        font_bolt.draw(sb,title, cam.position.x - title_width / 2, (cam.position.y  * 7) / 4);

        stage.draw();
        sb.end();
    }
    @Override
    public void dispose() {
        stage.dispose();






    }


}

