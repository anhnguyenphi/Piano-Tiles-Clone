package com.mygdx.Control;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.mygdx.UI.ImageButton;
import com.mygdx.gameworld.GameWorld;

public class InputHandler implements InputProcessor {
	private GameWorld world;
	private float scaleFactorX;
	private float scaleFactorY;
	private Array<Integer> queue;
	private Array<Integer> queueisClick;
	private Sound touch;
	private HashMap<String,ImageButton> listbutton;
	public InputHandler(GameWorld world, float scaleFactorX, float scaleFactorY, HashMap<String,ImageButton> listbutton) {
		this.world = world;
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
		this.queue = world.getQueue();
		this.queueisClick = world.getQueueisClick();
		touch = Gdx.audio.newSound(Gdx.files.internal("data/beep.mp3"));
		this.listbutton = listbutton;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.BACK) {
            if (world.isMenu()) {
            	Gdx.app.exit();
            }
            else if (world.isGameOver() || world.isReady() || world.isRunning()) {
            	world.meunu();
            }
        }
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		if (world.isGameOver()) {
			if(listbutton.get("Again").isTouchDown(screenX, screenY)) {
				
			}
			
			return false;
		} else if (world.isMenu()) {
			if(listbutton.get("Play").isTouchDown(screenX, screenY)) {
				
			}
			return false;
		}
		if (world.isReady() && world.getBox(0).Touch(screenX, screenY)) {
			world.start();
			world.addQueueClick(1);
			world.getQueue().removeIndex(0);
			Gdx.app.log("input", "touch 1");
			world.plusScore();
			touch.play();
			return false;
		} else if (world.isRunning()) {
			if (queue.size > 0) {
				if (world.getBox(queue.get(0) - 1).Touch(screenX, screenY)) {
					int x = world.addQueueClick(world.getBox(queue.get(0) - 1)
							.getOrder());
					world.getQueue().removeIndex(0);
					Gdx.app.log("input", "touch" + Integer.toString(x));
					world.plusScore();
					touch.play();
				} else {
					if (queueisClick.size > 0) {
						if (world.getBox(
								queueisClick.get(queueisClick.size - 1) - 1)
								.getPosY() > (float) screenY) {
							world.GameOver();
							return false;
						}
					}
				}
			} else if (queueisClick.size > 0) {
				if (world.getBox(queueisClick.get(queueisClick.size - 1) - 1)
						.getPosY() > (float) screenY) {
					world.GameOver();
					return false;
				}

			} else {
				world.GameOver();
				return false;
			}
		} else if (screenY > world.getBox(0).getPosY() && world.isReady()) {
			world.GameOver();
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		if (world.isMenu()) {
			if (listbutton.get("Play").isTouchUp(screenX, screenY)) {
				world.ready();
			}
		}
		if (world.isGameOver()) {
			if (listbutton.get("Again").isTouchUp(screenX, screenY)) {
				world.resetGame();
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	private int scaleX(int screenX) {
		return (int) (screenX / scaleFactorX);
	}

	private int scaleY(int screenY) {
		return (int) (screenY / scaleFactorY);
	}

}
