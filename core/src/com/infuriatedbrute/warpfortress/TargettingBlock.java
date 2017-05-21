package com.infuriatedbrute.warpfortress;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public abstract class TargettingBlock extends OutputBlock {
	int range = 0;
	int pierce = 0;

	@Override
	public void parseOrder(ArrayList<String> orders) {
		for (int i = 3; i <= 5 && i <= orders.size(); i++) {
			String order = orders.get(i).toLowerCase();
			switch (i) {
			case 3:
				switch (order) {
				case "north":
					rotation = 0;
					break;
				case "west":
					rotation = 90;
					break;
				case "south":
					rotation = 180;
					break;
				case "east":
					rotation = 270;
					break;
				default:
					com.infuriatedbrute.warpfortress.StaticMethods.blankOrderMessage(this.toString());
				}
				break;
			case 4:
				try {
					range = Integer.parseInt(order);
				} catch (NumberFormatException e) {
					com.infuriatedbrute.warpfortress.StaticMethods.blankOrderMessage(this.toString());
				}
				break;
			case 5:
				try {
					pierce = Integer.parseInt(order);
				} catch (NumberFormatException e) {
					com.infuriatedbrute.warpfortress.StaticMethods.blankOrderMessage(this.toString());
				}
				break;
			}
		}
		input *= (1 + com.infuriatedbrute.warpfortress.Constants.RANGE_INPUT_MULTIPLIER * range);
		input *= (1 + com.infuriatedbrute.warpfortress.Constants.PIERCE_INPUT_MULTIPLIER * pierce);
	}

	/**
	 * Since multitargetting is currently unimplemented the list is always one
	 * element.
	 * 
	 * @return A list of arrays whose first element is the x-coordinate of a
	 *         targetblock, and whose second element is the y-coordinate of a
	 *         targetblock.
	 */
	private ArrayList<int[]> targetLocations() {
		ArrayList<int[]> toReturn = new ArrayList<>();
		switch (rotation) {
		case 0:
			toReturn.add(new int[] { x, y + range });
			break;
		case 90:
			toReturn.add(new int[] { x - range, y });
			break;
		case 180:
			toReturn.add(new int[] { x, y - range });
			break;
		case 270:
			toReturn.add(new int[] { x + range, y });
			break;
		}
		return toReturn;
	}

	public ArrayList<Block> targetBlocks() {
		ArrayList<Block> toReturn = new ArrayList<>();
		for (int[] i : targetLocations()) {
			LinkedHashSet<Block> bl = body.region.blocksNear(i[0], i[1], 0, 0);
			assert bl.size() <= 1;
			toReturn.addAll(bl);
		}
		return toReturn;
	}
}
