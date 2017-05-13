package com.infuriatedbrute.warpfortress;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WarpFortress extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public AssetManager manager;

	public void create() {
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.
		font = new BitmapFont();
		manager = new AssetManager();
		SkirmishScreen loadedGame = new SkirmishScreen(this);
		this.setScreen(loadedGame);
	}

	/*
	 * the two methods below make manager effectively static but without
	 * compromising android performance. See coffeetablesculpture's comment at
	 * https://www.reddit.com/r/libgdx/comments/4smfr1/
	 * can_i_make_my_assetmanager_static/
	 */

	public static WarpFortress getInstance() {
		return (WarpFortress) Gdx.app.getApplicationListener();
	}

	public AssetManager getAssetManager() {
		return manager;
	}


	public void render() {
		super.render(); // important! according to the tutorial
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		manager.dispose();
	}

}