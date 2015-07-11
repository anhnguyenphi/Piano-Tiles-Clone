package com.mygdx.BoxTouch.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.BoxTouch.Boxtouch;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "BoxTouch";
        config.width = 272;
        config.height = 408;
		new LwjglApplication(new Boxtouch(), config);
	}
}
