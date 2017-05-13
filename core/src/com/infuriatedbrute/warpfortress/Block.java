package com.infuriatedbrute.warpfortress;

import java.util.ArrayList;
import java.util.Comparator;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Block implements Comparable<Block>, Comparator<Block> {
	/**
	 * Primarily used to determine which direction the sprite should face, where
	 * 0 is north, 90 west, 180 south, 270 east. It will look pretty bad as
	 * other values.
	 */
	public int rotation = 0;
	public int x = 0;
	public int y = 0;
	public double health = com.infuriatedbrute.warpfortress.Constants.BASE_DEFAULT_BLOCK_HEALTH;
	public Body body = new Body(this);
	public boolean sticky = true;



	public abstract void activate();

	/**
	 * Apply effects of current health (basically delete if health <= 0)
	 */
	public void updateHealth() {
		if (health == 0) {
			this.body.region.blockList.remove(this);
			this.body.blockList.remove(this);
			this.body = null;
		}
	}

	/**
	 * Applies the given orders. For a simple Block, this means stickiness at
	 * position 0 and selfDestruct at position 1. For an OutputBlock, this also
	 * means inputMultiplier at position 2. For a TargettingBlock, this also
	 * means direction at position 3, range at position 4, and pierce at
	 * position 5. For more information, visit the wiki. NOTE there is no wiki
	 * lmao, just PM me
	 * 
	 * @param orders
	 *            a list of orders to perform, as described above
	 */
	public void parseOrder(ArrayList<String> orders) {
		for (int i = 0; i <= 1 && i <= orders.size(); i++) {
			String order = orders.get(i).toLowerCase();
			switch (i) {
			case 0:
				if (order.equals("true") || order.equals("false")) {
					sticky = Boolean.parseBoolean(order);
				} else {
					com.infuriatedbrute.warpfortress.StaticMethods.blankOrderMessage(this.toString());
				}
				break;
			case 1:
				if ((order.equals("true") || order.equals("false"))) {
					if (Boolean.parseBoolean(order)) {
						health = 0;
						updateHealth();
					}
					break;
				} else {
					com.infuriatedbrute.warpfortress.StaticMethods.blankOrderMessage(this.toString());
				}
			}
		}
	}

	/**
	 * Adds this block to the textureatlas. Once all things to be drawn are
	 * added to a textureatlas, all that needs to be done to finish rendering
	 * the frame is to draw that textureatlas using a batch and dispose of the
	 * textureatlas and batch
	 * 
	 * @param t
	 *            the textureatlas on which to add this block
	 * @param cameraX
	 *            the current xCoord of the bottom leftmost pixel of the screen
	 *            that is not part of the GUI
	 * @param cameraY
	 *            same as cameraX but y obviously
	 */
	public void draw(Batch b, int cameraX, int cameraY) {
		int drawX = (x - cameraX) * Constants.BLOCK_WIDTH;
		int drawY = (y - cameraY) * Constants.BLOCK_HEIGHT;
		int width = com.infuriatedbrute.warpfortress.Constants.BLOCK_WIDTH;
		int height = com.infuriatedbrute.warpfortress.Constants.BLOCK_HEIGHT;
		Texture t = getTexture();
		// http://stackoverflow.com/questions/24748350/libgdx-rotate-a-texture-when-drawing-it-with-spritebatch
		b.draw(t, drawX, drawY, (width / 2), (height / 2), width, height, 1, 1, rotation, 0, 0, t.getWidth(),
				t.getHeight(), false, false);
	} // TODO convert game coordinates, x=1 is not 1 pixel away from x = 2

	/**
	 * @return the texture for this block (facing northward/upward) (even if it hasn't been loaded yet, so long as it's in the assetsfolder and has the correct name)
	 */
	private Texture getTexture() {
		String assetName = getAssetName();
		AssetManager manager = WarpFortress.getInstance().getAssetManager();
		if (!manager.isLoaded(assetName)) {
			manager.load(assetName, Texture.class);
			manager.finishLoading();
		}
		return manager.get(assetName);
	}

	/**
	 * @return i.e. Armor.gif if this is an Armor
	 */
	public String getAssetName() {
		String className = this.getClass().toString();
		return (className.substring(className.lastIndexOf('.') + 1, className.length()) + ".gif");
	}

	@Override
	public int compareTo(Block b) {
		int toReturn = 0;
		if (this.x != b.x) {
			toReturn = (this.x > b.x) ? 1 : -1;
		} else {
			if (this.y != b.y) {
				toReturn = (this.y > b.y) ? 1 : -1;
			}
		}
		return toReturn;
	}

	@Override
	public int compare(Block o1, Block o2) {
		return ((Block) o1).compareTo(o2);
	}
}
