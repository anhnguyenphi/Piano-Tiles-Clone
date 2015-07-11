package com.mygdx.gameworld;

import com.mygdx.Gameobjects.Box;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import java.util.*;

public class GameWorld {
	private Array<Box> box;
	private GameState current;
	private float gameHeight;
	private Array<Integer> queue;
	private Array<Integer> queueisClick;
	private int Score;

	public GameWorld(float Height) {
		box = new Array<Box>();
		this.gameHeight = Height;
		box.add(new Box(1, (int) gameHeight));
		box.add(new Box(2, (int) gameHeight));
		box.add(new Box(3, (int) gameHeight));
		box.add(new Box(4, (int) gameHeight));
		box.add(new Box(5, (int) gameHeight));
		current = GameState.MENU;
		int i = 0;
		queue = new Array<Integer>();
		queueisClick = new Array<Integer>();
		while (i < 5) {
			Integer a = new Integer(i + 1);
			queue.add(a);
			i++;
		}
		Score = 0;
	}

	public Array<Integer> getQueue() {
		return queue;
	}

	public enum GameState {
		READY, RUNNING, GAMEOVER, MENU
	}

	public void update(float delta) {
		switch (current) {
		case MENU:
			updateMenu(delta);
			break;
		case READY:
			updateReady(delta);
			break;
		case RUNNING:
			updateRunning(delta);
			break;
		case GAMEOVER:
			updateGameOver(delta);
		default:
			break;
		}
	}
	void updateMenu(float delta) {
		
	}
	void updateReady(float delta) {

	}

	void updateGameOver(float delta) {

	}

	void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}
		int i;
		if (queueisClick.size > 0) {
			// Kiem tra trong hang doi QueueClick co cai nao duoc click chua

			if (box.get(queueisClick.get(0).intValue() - 1).getPosY() > gameHeight) {
				Box tmp;
				if (queue.size == 0) {
					tmp = box.get(queueisClick.get(4) - 1);
				} else {
					tmp = box.get(queue.get(queue.size - 1).intValue() - 1);

				}
				box.get(queueisClick.get(0) - 1).reset(
						tmp.getPosY() - tmp.getHeight());
				queue.add(queueisClick.removeIndex(0));
			}

		}

		if (queue.size > 0) {
			if (box.get(queue.get(0) - 1).getPosY() > gameHeight) {
				this.GameOver();
			}
		}
		i = 0;
		while (i < 5) {
			if (Score > 10) {
				box.get(i).plusVelocity();
			}
			box.get(i).update(delta);
			i++;
		}
	}

	public Box getBox(int index) {
		return box.get(index);
	}

	public boolean isReady() {
		return current == GameState.READY;
	}

	public boolean isRunning() {
		return current == GameState.RUNNING;
	}

	public boolean isGameOver() {
		return current == GameState.GAMEOVER;
	}
	
	public boolean isMenu() {
		return current == GameState.MENU;
	}
	public void ready() {
		current = GameState.READY;
	}
	public void meunu() {
		current = GameState.MENU;
	}
	public void start() {
		current = GameState.RUNNING;
	}
	
	public void GameOver() {
		current = GameState.GAMEOVER;
	}

	public void resetGame() {
		int i = 0;
		Score = 0;
		while (i < 5) {
			box.get(i).resetGame();
			i++;
		}
		i = 0;
		while (queue.size > 0) {
			queue.removeIndex(0);
		}
		while (queueisClick.size > 0) {
			queueisClick.removeIndex(0);
		}
		while (i < 5) {
			Integer a = new Integer(i + 1);
			queue.add(a);
			i++;
		}
		current = GameState.READY;
	}

	public int addQueueClick(int x) {
		queueisClick.add(new Integer(x));
		return x;
	}

	public Array<Integer> getQueueisClick() {
		return queueisClick;
	}

	public Array<Box> getBoxList() {
		return box;
	}

	public void plusScore() {
		Score++;
	}
	public int getScore() {
		return Score;
	}
}
