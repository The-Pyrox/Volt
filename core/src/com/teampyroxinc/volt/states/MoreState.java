package com.teampyroxinc.volt.states;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class MoreState extends State {

    private BitmapFont font;
    private SpriteBatch sb;
    private Stage stage;
    private TextureAtlas buttonsAtlas;
    private Skin buttonSkin;
    private TextButton button_rate,button_change;




    public MoreState(final GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        sb = new SpriteBatch();
        font.getData().setScale(2f);


        buttonsAtlas = new TextureAtlas("buttons.pack");
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas);
        stage = new Stage(new FitViewport(480,800));

        Gdx.input.setInputProcessor(stage);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = buttonSkin.getDrawable("buttons");
        style.down = buttonSkin.getDrawable("buttons");
        style.font = font;

        button_rate = new TextButton("Rate & Review", style);
        button_rate.setWidth(300);
        button_rate.setHeight(100);
        button_rate.setPosition(100,500);
        stage.addActor(button_rate);
        button_rate.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.net.openURI("https://play.google.com/store/apps/details?id=com.teampyroxinc.volt");
                return true;
            }
        });

        button_change = new TextButton("Change Log", style);
        button_change.setWidth(300);
        button_change.setHeight(100);
        button_change.setPosition(100,400);
        stage.addActor(button_change);
        button_change.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new ChangeState(gsm));
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
        sb.dispose();
        font.dispose();
        buttonSkin.dispose();
        buttonsAtlas.dispose();
    }

    public void resize (int width, int height) {
        stage.getViewport().update(288,512, true);
    }
}
