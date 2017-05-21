package com.infuriatedbrute.warpfortress;

public class ShieldProjector extends TargettingBlock {

	@Override
	public void activate() {
		if (payEnergy()) {
			for (Block b : targetBlocks()) {
				ShieldArmor s = new ShieldArmor(output);
				if (body.region.blocksNear(b.x, b.y, 0, 0).size() == 0) {
					s.x = b.x;
					s.y = b.y;
					body.add(s);
				}
			}
		}
	}

	@Override
	public double getEnergyCost() {
		return Constants.SHIELD_PROJECTOR_BASE_ENERGY_COST*input;
	}
	
	

}
