package com.mygdx.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ImageButton {
	private float width, height;
	private Sprite buttonUp, buttonDown;
	private Rectangle bound;
	private boolean isPressed = false;

	public ImageButton(float x, float y, Texture buttonUp, Texture buttonDown,
			float scale) {
		this.buttonUp = new Sprite(buttonUp, 0, 0, buttonUp.getWidth(),
				buttonUp.getHeight());
		this.buttonDown = new Sprite(buttonDown, 0, 0, buttonDown.getWidth(),
				buttonDown.getHeight());
		this.buttonDown.flip(false, true);
		this.buttonUp.flip(false, true);
		float a = (float) 136 / buttonUp.getWidth() * scale;
		this.buttonUp.setSize((float) a * buttonUp.getWidth(), (float) a
				* buttonUp.getHeight());
		this.buttonDown.setSize((float) a * buttonUp.getWidth(), (float) a
				* buttonUp.getHeight());
		this.buttonDown.setCenter(x, y);
		this.buttonUp.setCenter(x, y);
		width = this.buttonUp.getWidth();
		height = this.buttonDown.getHeight();
		bound = new Rectangle(x - width / 2, y - height / 2, width, height);
	}

	public boolean isClicked(int screenX, int screenY) {
		return bound.contains(screenX, screenY);
	}

	public void draw(SpriteBatch batcher) {
		if (!isPressed) {
			buttonUp.draw(batcher);
			
		} else {
			buttonDown.draw(batcher);

		}
	}

	public boolean isTouchDown(int screenX, int screenY) {

		if (bound.contains(screenX, screenY)) {
			isPressed = true;
			return true;
		}

		return false;
	}

	public boolean isTouchUp(int screenX, int screenY) {

		// It only counts as a touchUp if the button is in a pressed state.
		if (bound.contains(screenX, screenY) && isPressed) {
			isPressed = false;
			return true;
		}

		// Whenever a finger is released, we will cancel any presses.
		isPressed = false;
		return false;
	}
}
