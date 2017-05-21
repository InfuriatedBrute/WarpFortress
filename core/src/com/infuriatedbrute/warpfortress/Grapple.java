package com.infuriatedbrute.warpfortress;

import java.util.ArrayList;

public class Grapple extends TargettingBlock {

	@Override
	public void activate() {
		if (payEnergy()) {
			ArrayList<Body> toMerge = new ArrayList<>();
			for (Block b : targetBlocks()) {
				if (!toMerge.contains(b.body)) {
					toMerge.add(b.body);
				}
			}
			for (Body b : toMerge) {
				for (int i = 0; i < b.size(); i++) {
					Block b1 = b.get(i);
					assert (!this.body.contains(b1));
					this.body.add(b1);
					b.remove(b1);
					b1.body = this.body;
				}
			}
		}
	}
	@Override
	public double getEnergyCost() {
		return Constants.THRUSTER_BASE_ENERGY_COST;
	}

}
