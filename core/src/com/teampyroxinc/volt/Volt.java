package com.teampyroxinc.volt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.teampyroxinc.volt.states.CreditState;
import com.teampyroxinc.volt.states.GameStateManager;
import com.teampyroxinc.volt.states.MenuState;

public class Volt extends Game {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Bolt";
	private GameStateManager gsm;

	private Music music;

	private SpriteBatch batch;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = (Gdx.audio.newMusic(Gdx.files.internal("music.mp3")));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new CreditState(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}
