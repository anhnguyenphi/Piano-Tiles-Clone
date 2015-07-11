package com.mygdx.gameworld;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Control.AssetLoader;
import com.mygdx.Gameobjects.Box;
import com.mygdx.UI.ImageButton;
public class GameRender {
	private OrthographicCamera cam;
	private float gameHeight;
	private GameWorld world;
	private ShapeRenderer shapeRenderer;
	private Array<Box> box;
	private SpriteBatch batch;
	private Texture logoTexture, Playtexup, Playtexdown;
	private Sprite logo;
	private ImageButton Playbt, Againbt;
	private HashMap<String,ImageButton> listbutton;
	private BitmapFont font;
	public GameRender(GameWorld world, float gameHeight) {
		this.world = world;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);
		this.gameHeight = gameHeight;
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		this.box = world.getBoxList();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		listbutton = new HashMap<String,ImageButton>();
		init();
	}
	public void init() {
		logoTexture = AssetLoader.logo;
		logo = new Sprite(logoTexture,0,0,logoTexture.getWidth(),logoTexture.getHeight());
		logo.flip(false, true);
		logo.setScale(136/logo.getWidth() * 0.5f);
		logo.setCenter(136/2, 1.5f*gameHeight/6);
		Playbt = new ImageButton(136/2, 4*gameHeight/5,AssetLoader.Playbtup,AssetLoader.Playbtdown,0.5f);
		listbutton.put("Play", Playbt);
		Againbt = new ImageButton(136/2, 4*gameHeight/5,AssetLoader.Againbtup,AssetLoader.Againbtdown,0.5f);
		listbutton.put("Again", Againbt);
		// Font
		font = AssetLoader.font;
		font.setScale(136/font.getSpaceWidth() * 0.045f);
	}
	public HashMap<String,ImageButton> getbutton() {
		return listbutton;
	}

	public void render(float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (world.isReady() || world.isRunning()) {
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(255 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1);
			shapeRenderer.rect(0, 0, 136, gameHeight);
			shapeRenderer.end();
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(215 / 255.0f, 215 / 255.0f, 215 / 255.0f, 1);
			shapeRenderer.line(136 / 4, 0, 136 / 4, gameHeight);
			shapeRenderer.line(2 * 136 / 4, 0, 2 * 136 / 4, gameHeight);
			shapeRenderer.line(3 * 136 / 4, 0, 3 * 136 / 4, gameHeight);
			shapeRenderer.end();
			shapeRenderer.begin(ShapeType.Filled);
			int i = 0;
			while (i < 5) {
				if (box.get(i).isTouch()) {
					shapeRenderer.setColor(194 / 255.0f, 194 / 255.0f,
							194 / 255.0f, 1);
				} else {
					shapeRenderer.setColor(0 / 255.0f, 0 / 255.0f, 0 / 255.0f,
							1);
				}
				shapeRenderer.rect(box.get(i).getPosX(), box.get(i).getPosY(),
						box.get(i).getWidth(), box.get(i).getHeight());
				i++;
			}
			shapeRenderer.end();
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(215 / 255.0f, 215 / 255.0f, 215 / 255.0f, 1);
			i = 0;
			while (i < 5) {
				shapeRenderer.line(0, box.get(i).getPosY(), 136, box.get(i)
						.getPosY());
				shapeRenderer.line(0, box.get(i).getPosY()
						+ box.get(i).getHeight(), 136, box.get(i).getPosY()
						+ box.get(i).getHeight());
				i++;
			}
			shapeRenderer.end();
		} else if (world.isGameOver()) {
			int lenght = ("Your score:"+Integer.toString(world.getScore())).length();
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(255 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1);
			shapeRenderer.rect(0, 0, 136, gameHeight);
			shapeRenderer.end();
			batch.begin();
			batch.enableBlending();
			font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			float score = font.getSpaceWidth();
			font.draw(batch, "Your score:" + Integer.toString(world.getScore()), 136/2 - (lenght+1)*font.getSpaceWidth()/2, 3*gameHeight/6);
			logo.draw(batch);
			Againbt.draw(batch);
			batch.end();
		} else if(world.isMenu()) {
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(255 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1);
			shapeRenderer.rect(0, 0, 136, gameHeight);
			shapeRenderer.end();
			batch.begin();
			batch.enableBlending();
			logo.draw(batch);
			Playbt.draw(batch);
			batch.end();
		}

	}
}
