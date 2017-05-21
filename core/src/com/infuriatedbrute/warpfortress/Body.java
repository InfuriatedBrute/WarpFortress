package com.infuriatedbrute.warpfortress;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Body {
	/**
	 * Please sort this using a BlockCoordComparator whenever you apply it for
	 * an effect
	 */
	private ArrayList<Block> blockList = new ArrayList<>();
	public BlockRegion region;
	/**
	 * The amount of x spaces the body will attempt to move this turn. Digits to
	 * the right of the . determine the probability of adding another space
	 * (i.e. 0.65 has 65% chance of acting like 1, and a 35% chance of acting
	 * like 0).
	 */
	public double xForce = 0;
	/**
	 * @see xForce
	 */
	public double yForce = 0;
	/**
	 * One spinForce causes one 90 degree clockwise rotation. Note that
	 * spinForce cannot cause "ramming", unlike xForce and yForce.
	 * 
	 * @see xForce
	 */
	public Double spinForce = new Double(0);

	public Body(Block firstBlock) {
		blockList.add(firstBlock);
	}
	
	/**
	 * For each xForce, yForce, and spinForce (in a random order, but always
	 * with spinForce last) move it by one in the given direction, unless there
	 * are blocks blocking it and the currently applied force is not spinForce,
	 * in which case apply ramming damage (equal to RAMMING_DAMAGE_MULTIPLIER)
	 * and adjusting forces (equal to RAMMING_MOVEMENT_MULTIPLIER) split among
	 * all blocking blocks. If all blocking blocks are destroyed by ramming the
	 * remaining force will be used to move as normal. After this is done, move
	 * the bodies whose forces were affected (in a somewhat arbitrary order).
	 * 
	 * A 200 line and very complex method. Difficult even to explain.
	 */
	public void move() {
		// create mutable versions for shortened code
		double[] x = { xForce };
		double[] y = { yForce };
		// LinkedHashSets are effectively ArrayLists can't hold duplicate values
		LinkedHashSet<Body> toMove = new LinkedHashSet<>();
		while (x[0] != 0 || y[0] != 0) {
			boolean useX = (y[0] == 0 || Math.random() > 0.5);
			double[] toUse = (useX) ? x : y;
			if (Math.abs(toUse[0]) < 1) {
				if (Math.random() >= toUse[0]) {
					toMove.addAll(moveSnippet(useX, toUse[0] > 0));
				}
				toUse[0] = 0;
			} else {
				toMove.addAll(moveSnippet(useX, toUse[0] > 0));
				if (toUse[0] > 0) {
					toUse[0]--;
				} else {
					toUse[0]++;
				}
			}

		}
		while (spinForce != 0) {
			if (Math.abs(spinForce) <= 1) {
				if (Math.random() > spinForce) {
					spinSnippet(spinForce > 0);
				}
				spinForce = 0.0;
			} else {
				spinSnippet(spinForce > 0);
				spinForce--;
			}
		}
		for (Body b : toMove) {
			b.move();
		}
	}
	
	/**
	 * Invoked when a block would be removed from the blockList. Checks for separation into separate bodies and removes from the body's region
	 * @param b the block removed
	 */
	public void remove(Block b){
		
	}
	
	
	/**
	 * Invoked when a block would be added to the blockList. Checks for joining into multiple bodies and removes from the body's region.
	 * @param b the block added
	 */
	public void add(Block b){
		
	}
	
	/**
	 * 
	 * @return blockList.get(index)
	 * @see ArrayList.get()
	 */
	public Block get(int index){
		return blockList.get(index);
	}
	
	/**
	 * 
	 * @return blockList.size()
	 * @see ArrayList.size()
	 */
	public int size(){
		return blockList.size();
	}
	
	/**
	 * 
	 * @return blockList.contains(Object o)
	 * @see ArrayList.contains(Object o)
	 */
	public boolean contains(Object o){
		return blockList.contains(o);
	}

	/**
	 * A very private code snippet that handles moving one space
	 * 
	 * @param useX
	 *            if true move along the xAxis, otherwise the yAxis
	 * @param positive
	 *            if true move in a rightward or upward direction, otherwise
	 *            move in a leftward or downward direction
	 * @return a LinkedHashSet of Bodies that need to be moved as a result of
	 *         ramming movement
	 */
	private LinkedHashSet<Body> moveSnippet(boolean useX, boolean positive) {
		int modifier = positive ? 1 : -1;
		if (useX) {
			for (Block b : blockList) {
				b.x += modifier;
			}
		} else {
			for (Block b : blockList) {
				b.y += modifier;
			}
		}
		// list since damage and movement can theoretically apply multiple
		// times, though it may be impossible
		ArrayList<Block> overlap = new ArrayList<>();
		LinkedHashSet<Body> toReturn = new LinkedHashSet<>();
		for (Block b : blockList) {
			// note that the body should not overlap with itself
			overlap.addAll(region.blocksNear(b.x, b.y, 0, 0));
			overlap.remove(b);
		}
		if (!overlap.isEmpty()) {
			if (useX) {
				for (Block b : blockList) {
					b.x -= modifier;
				}
			} else {
				for (Block b : blockList) {
					b.y -= modifier;
				}
			}
			for (Block b : overlap) {
				if (useX) {
					b.body.xForce += (modifier * com.infuriatedbrute.warpfortress.Constants.RAMMING_MOVEMENT_MULTIPLIER);
				} else {
					b.body.yForce += (modifier * com.infuriatedbrute.warpfortress.Constants.RAMMING_MOVEMENT_MULTIPLIER);
				}
				toReturn.add(b.body);
				b.health -= com.infuriatedbrute.warpfortress.Constants.RAMMING_DAMAGE_MULTIPLIER;
				b.updateHealth();
			}
		}
		return toReturn;
	}

	/**
	 * A very private code snippet that handles one 90 degree rotation
	 * 
	 * @param positive
	 *            if true spin clockwise, otherwise spin counter-clockwise
	 */
	public void spinSnippet(boolean positive) {
		double averageX = 0;
		double averageY = 0;
		for(Block b : blockList){
			averageX+=b.x;
			averageY+=b.y;
		}
		averageX /= blockList.size();
		averageY /= blockList.size();
		if(Math.random()<(averageX%1)){
			averageX++;
		}
		if(Math.random()<(averageY%1)){
			averageY++;
		}
		averageX = Math.floor(averageX);
		averageY = Math.floor(averageY);
		if (positive) {
			for (Block b : blockList) {
				int toY = (int) (b.x-averageX);
				b.x = (int)(-1 * (b.y-averageY));
				b.y = toY;
			}
		} else {
			for (Block b : blockList) {
				int toX = (int)(b.y-averageY);
				b.y = (int)(-1*  (b.x-averageX));
				b.x = toX;
			}
		}
		boolean undo = false;
		outerloop: for (Block b : blockList) {
			if (!region.blocksNear(b.x, b.y, 0, 0).isEmpty()) {
				undo = true;
				break outerloop;
			}
		}
		if (undo) {
			if (!positive) {
				for (Block b : blockList) {
					int toY = (int) (b.x-averageX);
					b.x = (int)(-1 * (b.y-averageY));
					b.y = toY;
				}
			} else {
				for (Block b : blockList) {
					int toX = (int)(b.y-averageY);
					b.y = (int)(-1*  (b.x-averageX));
					b.x = toX;
				}
			}
		}
	}
}
