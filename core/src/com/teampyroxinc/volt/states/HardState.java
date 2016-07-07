package com.teampyroxinc.volt.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.teampyroxinc.volt.Volt;
import com.teampyroxinc.volt.sprites.Ball;
import com.teampyroxinc.volt.sprites.Tube_Hard;


public class HardState extends State{    private static final int TUBE_SPACING = 200;
    private static final int GROUND_Y_OFFSET = -70;
    private static final int TUBE_COUNT = 2;
    private Array<Tube_Hard> tubes;
    private Ball ball;
    private SpriteBatch sb;
    private Texture background;
    private Texture ground;
    private Vector2 groundPos1,groundPos2;
    private BitmapFont font,font_gameover;
    private GlyphLayout glyph_gameover,glyph_score;
    private String gtag,score;
    private float gwidth,swidth;
    private int points;
    public boolean gameover;

    public HardState(final GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false,480,800);
        background = new Texture("background.png");
        ball = new Ball(50,120);
        sb = new SpriteBatch();
        ground = new Texture("ground.png");

        tubes = new Array<Tube_Hard>();
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font_gameover = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);
        font_gameover.getData().setScale(2f,2f);
        gtag = new String("GAME OVER");
        score = new String("Your Score Is");
        glyph_gameover = new GlyphLayout();
        glyph_score = new GlyphLayout();
        glyph_gameover.setText(font_gameover,gtag);
        glyph_score.setText(font,score);
        gwidth = glyph_gameover.width;
        swidth = glyph_score.width;

        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2(cam.position.x - cam.viewportWidth / 2 + ground.getWidth(),GROUND_Y_OFFSET);

        gameover = new Boolean(false);

        for (int i = 1;i < TUBE_COUNT; i++ ){
            tubes.add(new Tube_Hard(i * (TUBE_SPACING)));
        }

    }
    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            ball.jump();
        }
    }
    @Override
    public void update(float dt) {
        handleInput();
        ball.update(dt);
        updateGround();
        cam.position.x = ball.getPosition().x + 80;

        for (Tube_Hard tubeEasy : tubes) {

            if (cam.position.x - cam.viewportWidth / 2 > tubeEasy.getPosBotTube().x + tubeEasy.getBottomTube().getWidth()) {
                tubeEasy.reposition(tubeEasy.getPosTopTube().x);
                tubeEasy.reposition(tubeEasy.getPosHosTube().x + 80);
                tubeEasy.reposition(tubeEasy.getPosBotTube().x + 120 + 320);
                points = points + 3;
            }
            if (tubeEasy.collides(ball.getBounds())) {
                gameover = true;
            }


        }

        if(ball.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gameover = true;

        }
        Gdx.graphics.setContinuousRendering(true);

        cam.update();
    }
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x - cam.viewportWidth / 2,0);
        sb.draw(ball.getBall(),ball.getPosition().x,ball.getPosition().y);
        for (Tube_Hard tubeEasy : tubes) {
            sb.draw(tubeEasy.getTopTube(), tubeEasy.getPosTopTube().x, tubeEasy.getPosTopTube().y);
            sb.draw(tubeEasy.getBottomTube(), tubeEasy.getPosBotTube().x, tubeEasy.getPosBotTube().y);
            sb.draw(tubeEasy.getHosTube(), tubeEasy.getPosHosTube().x, tubeEasy.getPosHosTube().y);
        }


        font.draw(sb,Long.toString(getPoints()),cam.position.x ,cam.position.y * 2 );

        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);
        if (gameover){
            font_gameover.draw(sb,gtag,cam.position.x - gwidth / 2,(cam.position.y * 3 ) / 2);
            font.draw(sb,"YOUR SCORE IS",cam.position.x - swidth / 2, cam.position.y );
            font.draw(sb,Integer.toString(getPoints()),cam.position.x,(cam.position.y * 3) / 4);
            Gdx.graphics.setContinuousRendering(false);
            newgame();
        }

        sb.end();
    }
    public void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
    public  void newgame(){
        if (Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }

    }
    @Override
    public void dispose() {
        sb.dispose();
    }
    public int getPoints() {
        return points;
    }


}


