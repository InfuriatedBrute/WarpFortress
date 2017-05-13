package com.infuriatedbrute.warpfortress.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.infuriatedbrute.warpfortress.Constants;
import com.infuriatedbrute.warpfortress.WarpFortress;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=Constants.SCREEN_WIDTH;
		config.height=Constants.SCREEN_HEIGHT;
		new LwjglApplication(new WarpFortress(), config);
	}
}
