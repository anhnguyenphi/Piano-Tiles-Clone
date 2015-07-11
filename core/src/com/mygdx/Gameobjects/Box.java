package com.mygdx.Gameobjects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Box {
	private int r;
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 a;
	private float width;
	private float height;
	private Rectangle rect;
	private int order;
	private float gameHeight;
	private boolean isClick;
	public Box(int order,float gameheight) {
		r = randInt(0, 3);
		this.order = order;
		width = 136/4;
		height = gameheight/4;
		this.gameHeight = gameheight;
		velocity = new Vector2();
		position = new Vector2();
		velocity.x = 0;
		velocity.y = 175f;	
		position.x = r * 136 / 4;
		if (order == 5) {
			position.y = - gameheight/4;
		} else {
			position.y = (4 - order) * gameheight/4;
		}	
		rect = new Rectangle(position.x, position.y, width, height);
		isClick = false;
		a = new Vector2(0,5f);
	}

	public void update(float delta) {
		if (velocity.y > 300) {
			velocity.y = 300;
		} else {
			velocity.add(a.cpy().scl(delta));
		}
		Gdx.app.log("V", "" + velocity.y);
		position.add(velocity.cpy().scl(delta));
		rect.set(position.x, position.y, width, height);
		
	}
	public void plusVelocity() {
		
	}
	public boolean reset(float height) {
		if (isClick == true) {
			r = randInt(0, 3);
			position.x = r * 136 / 4;
			position.y = height;
			isClick = false;
			return true;
		}
		return false;
	}
	public void resetGame() {
		isClick = false;
		r = randInt(0, 3);
		position.x = r * 136 / 4;
		velocity.y = 175f;
		if (order == 5) {
			position.y = - gameHeight/4;
		} else {
			position.y = (4 - order) * gameHeight/4;
		}
		rect.set(position.x, position.y, width, height);
	}
	public int getcell() {
		return r;
	}
	
	public boolean Touch(int screenX, int screenY) {
		if (rect.contains(screenX, screenY)) {
			isClick = true;
			return true;
		}
		return false;
	}
	public boolean isTouch() {
		return isClick;
	}
	
	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}
	public float getPosX() {
		return position.x;
	}
	public float getPosY() {
		return position.y;
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	public int getOrder() {
		return order;
	}
	int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}
