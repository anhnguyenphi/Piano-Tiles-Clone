package com.mygdx.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetLoader {
	public static Texture logo, Playbtdown, Playbtup, Againbtup, Againbtdown;
	public static BitmapFont font;
	public static void load() {
		 logo = new Texture(Gdx.files.internal("data/logo.png"));
		 logo.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	     Playbtup = new Texture(Gdx.files.internal("data/bt_start.png"));
	     Playbtup.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	     Playbtdown = new Texture(Gdx.files.internal("data/bt_start_pressed.png"));
	     Playbtdown.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	     Againbtup = new Texture(Gdx.files.internal("data/bt_again.png"));
	     Againbtup.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	     Againbtdown = new Texture(Gdx.files.internal("data/bt_again_pressed.png"));
	     Againbtdown.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	     font = new BitmapFont(Gdx.files.internal("data/text.fnt"),Gdx.files.internal("data/text.png"),true);
	}
}
