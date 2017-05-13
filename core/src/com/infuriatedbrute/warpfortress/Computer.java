package com.infuriatedbrute.warpfortress;

import java.util.ArrayList;
import java.util.HashMap;

public class Computer extends Block {

	public HashMap<Block, ArrayList<String>> orders = new HashMap<>();

	public void giveOrders() { // does not count as activate()ing
		if (body != null) {
			for (Block b : body.blockList) {
				if (orders.containsKey(b)) {
					b.parseOrder(orders.get(b));
				}
			}
		}
	}
	
	public void activate(){
		
	}
}
