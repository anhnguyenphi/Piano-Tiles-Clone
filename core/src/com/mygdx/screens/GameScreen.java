package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.Control.AssetLoader;
import com.mygdx.Control.InputHandler;
import com.mygdx.gameworld.GameWorld;
import com.mygdx.gameworld.GameRender;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRender renderer;
    public GameScreen() {
    	float ScreenWidth = Gdx.graphics.getWidth();
    	float ScreenHeight = Gdx.graphics.getHeight();
    	float width = 136;
    	float height = ScreenHeight / (ScreenWidth / width);
    	AssetLoader.load();
    	world = new GameWorld(height);
    	renderer = new GameRender(world,height);  
    	Gdx.input.setInputProcessor(new InputHandler(world,ScreenWidth / width,ScreenHeight / height,renderer.getbutton()));
    }
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		world.update(delta);
		renderer.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
