package com.mygdx.BoxTouch;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Control.*;
import com.mygdx.screens.GameScreen;

public class Boxtouch extends Game {

	@Override
	public void create () {
		AssetLoader.load();
		setScreen(new GameScreen());
	}
}
