package com.infuriatedbrute.warpfortress;

public class Gun extends TargettingBlock {

	@Override
	public void activate() {
		if (payEnergy()) {
			for (Block b : targetBlocks()) {
				b.health -= Constants.GUN_DAMAGE*output;
				b.updateHealth();
			}
		}
	}

	@Override
	public double getEnergyCost() {
		return Constants.GUN_BASE_ENERGY_COST*input;
	}

}
