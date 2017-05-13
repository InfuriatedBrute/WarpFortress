package com.infuriatedbrute.warpfortress;

import java.util.ArrayList;

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
				try{
					range = Integer.parseInt(order);
				} catch (NumberFormatException e) {
					com.infuriatedbrute.warpfortress.StaticMethods.blankOrderMessage(this.toString());
				}
				break;
			case 5:
				try{
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
}
