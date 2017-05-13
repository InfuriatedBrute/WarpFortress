package com.infuriatedbrute.warpfortress;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Handles everything for the Skirmish gamemode, including gamelogic, rendering,
 * saving, and other screens (although this screen will always be saved while
 * the gamemode is running). Does not handle the main menu or other gamemodes.
 */
public class SkirmishScreen extends InputAdapter implements Screen {
	WarpFortress game;
	BlockRegion region = new BlockRegion();
	int cameraX = 0;
	int cameraY = 0;
	Player player = new Player();
	ArrayList<Player> aiActors = new ArrayList<>();

	public SkirmishScreen(final WarpFortress w) {
		game = w;
		Gdx.input.setInputProcessor(this);
		region.blockList.add(new Armor());
		Armor a = new Armor();
		a.x=4;
		region.blockList.add(a);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(100 / 255.0F, 100 / 255.0F, 100 / 255.0F, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		LinkedHashSet<Block> toRender = region.blocksNear(cameraX, cameraY, Constants.NON_UI_SCREEN_WIDTH_IN_BLOCKS*2,
				Constants.NON_UI_SCREEN_HEIGHT_IN_BLOCKS); //it can render some extras, just don't render everything.
		game.batch.begin();
		if (toRender != null) {
			for (Block b : toRender) {
				b.draw(game.batch, cameraX, cameraY);
			}
		}
		game.batch.end(); //NOTE in order to optimize performance it may be worth pursuing batch reuse someday
		//NOTE put overlay code here
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean keyDown(int key) {
		switch (key) {
		case Keys.RIGHT:
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
				cameraX += Constants.SHIFT_CAMERA_MOVEMENT;
			} else {
				cameraX++;
			}
			break;
		case Keys.LEFT:
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
				cameraX -= Constants.SHIFT_CAMERA_MOVEMENT;
			} else {
				cameraX--;
			}
			break;
		case Keys.UP:
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
				cameraY += Constants.SHIFT_CAMERA_MOVEMENT;
			} else {
				cameraY++;
			}
			break;
		case Keys.DOWN:
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
				cameraY -= Constants.SHIFT_CAMERA_MOVEMENT;
			} else {
				cameraY--;
			}
			break;
		case Keys.TAB:
			cameraX = player.cockpit.x;
			cameraY = player.cockpit.y;
			break;
		case Keys.CONTROL_LEFT:
		case Keys.CONTROL_RIGHT:
		case Keys.ALT_LEFT:
		case Keys.ALT_RIGHT:
			doTurn();
		}
		return true;
	}
	
	@Override
	public boolean keyTyped(char key){
		if(player.keyToComputerBlock.containsKey(key)){
			player.keyToComputerBlock.get(key).giveOrders();
			doTurn();
		}
		return true;
	}
	
	private void doTurn(){
		for(Player p : aiActors){
			p.pressRandomValidKey();
		}
	}
}
