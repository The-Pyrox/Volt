package com.teampyroxinc.volt.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.teampyroxinc.volt.Volt;
import com.teampyroxinc.volt.sprites.Ball;
import com.teampyroxinc.volt.sprites.Tube;


public class HardState extends State{    private static final int TUBE_SPACING = 200;
    private static final int GROUND_Y_OFFSET = -70;
    private static final int TUBE_COUNT = 2;
    private Array<Tube> tubes;
    private Ball ball;
    private SpriteBatch sb;
    private Texture background;
    private Texture ground;
    private Vector2 groundPos1,groundPos2;
    private BitmapFont font;
    private int points;
    public boolean gameover;

    public HardState(final GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Volt.WIDTH /2,Volt.HEIGHT /2);

        background = new Texture("background.png");
        ball = new Ball(50,120);
        sb = new SpriteBatch();
        ground = new Texture("ground.png");

        tubes = new Array<Tube>();
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"),Gdx.files.internal("myfont.png"),false);

        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2(cam.position.x - cam.viewportWidth / 2 + ground.getWidth(),GROUND_Y_OFFSET);

        gameover = new Boolean(false);

        for (int i = 1;i < TUBE_COUNT; i++ ){
            tubes.add(new Tube(i * (TUBE_SPACING)));
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

        for (Tube tube : tubes) {

            if (cam.position.x - cam.viewportWidth / 2 > tube.getPosHosTube().x + tube.getHosTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x);
                tube.reposition(tube.getPosBotTube().x + 150);
                tube.reposition(tube.getPosHosTube().x + 300);
                points = points + 3;
            }
            if (tube.collides(ball.getBounds())) {
                gameover = true;
            }


        }

        if(ball.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gameover = true;

        }

        cam.update();
    }
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x - cam.viewportWidth / 2,0);
        sb.draw(ball.getBall(),ball.getPosition().x,ball.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
            sb.draw(tube.getHosTube(), tube.getPosHosTube().x, tube.getPosHosTube().y);
        }


        font.draw(sb,Long.toString(getPoints()),cam.position.x ,cam.position.y * 2 );

        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);
        if (gameover){
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
        gsm.set(new MenuState(gsm));
    }
    @Override
    public void dispose() {
        sb.dispose();



    }
    public int getPoints() {
        return points;
    }


}

