package com.infuriatedbrute.warpfortress;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * A region that is simulated block-by-block as a player would expect it to be,
 * rather than using less accurate and less performance-intensive simulation
 * methods such as auto-battle. This is currently the only kind of Region, but
 * in the future there may be other types and this may implement a Region
 * interface.
 */
public class BlockRegion{
	/**
	 * Please sort this using a BlockCoordComparator whenever you search it
	 */
	public ArrayList<Block> blockList = new ArrayList<>();

	/**
	 * Sorts the blockList before searching.
	 * 
	 * @return Blocks in the blockList whose xcoord is within the range
	 *         (x+-xTolerance) and whos y coordinate is within the range
	 *         (y+-yTolerance)
	 */
	public LinkedHashSet<Block> blocksNear(int x, int y, int xTolerance, int yTolerance) {
		if (!blockList.isEmpty()) {
			Block b = blockList.get(0);
			blockList.sort(b);
			LinkedHashSet<Block> toReturn = new LinkedHashSet<>();
			outerloop: for (Block toCompare : blockList) {
				if (Math.abs(toCompare.x - x) <= xTolerance && Math.abs(toCompare.y - y) <= yTolerance) {
					toReturn.add(toCompare);
				} else if (toCompare.x > x + xTolerance || (toCompare.x == x && toCompare.y > y + yTolerance)) {
					break outerloop;
				}
			}
			return toReturn;
		} else {
			com.infuriatedbrute.warpfortress.StaticMethods.blankOrderMessage(this.toString());
			return null;
		}
	}
}