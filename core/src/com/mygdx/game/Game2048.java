package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.stream.IntStream;

public class Game2048 extends Game {
	private static Game2048 instance;

	private Game2048() {

	}

	@Override

	public void create() {

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void dispose() {
	}

	public static Game2048 getInstance() {
		if (instance == null) {
			instance = new Game2048();
		}
		return instance;
	}
}
