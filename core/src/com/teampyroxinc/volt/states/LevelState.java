package com.teampyroxinc.volt.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.teampyroxinc.volt.Volt;


public class LevelState extends State {

    private BitmapFont font,font_title;
    private SpriteBatch sb;
    private String level;
    private Stage stage;
    private TextureAtlas buttonsAtlas;
    private Skin buttonSkin;
    private TextButton button_easy,button_medium,button_hard,button_level;

    public LevelState(final GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Volt.WIDTH / 2, Volt.HEIGHT / 2);
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font_title = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font.getData().setScale(5f);
        font_title.getData().setScale(12f);




        level = new String("LEVEL");

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
        style_title.font = font_title;

        button_level = new TextButton(level, style_title);
        button_level.setWidth(300);
        button_level.setHeight(100);
        button_level.setPosition(cam.position.x * 3,cam.position.y * 7 );
        stage.addActor(button_level);

        button_easy= new TextButton("EASY", style);
        button_easy.setWidth(300);
        button_easy.setHeight(100);
        button_easy.setPosition(cam.position.x * 3,cam.position.y * 4);
        stage.addActor(button_easy);
        button_easy.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                gsm.set(new EasyState(gsm));
                return true;
            }
        });

        button_medium= new TextButton("MEDIUM", style);
        button_medium.setWidth(300);
        button_medium.setHeight(100);
        button_medium.setPosition(cam.position.x * 3,cam.position.y * 3);
        stage.addActor(button_medium);
        button_medium.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                gsm.set(new MediumState(gsm));
                return true;
            }
        });

        button_hard= new TextButton("HARD", style);
        button_hard.setWidth(300);
        button_hard.setHeight(100);
        button_hard.setPosition(cam.position.x * 3,cam.position.y * 2);
        stage.addActor(button_hard);
        button_hard.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                gsm.set(new HardState(gsm));
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
        font_title.dispose();

        font.dispose();
        buttonsAtlas.dispose();
        buttonSkin.dispose();

    }
}
