package com.infuriatedbrute.warpfortress;

public class Thruster extends TargettingBlock {

	public void activate() {
		if (payEnergy()) {
			boolean vertical = (rotation % 180 == 0);
			boolean positive = (rotation % 270 == 0);
			double xyToAdd = output * Constants.THRUSTER_XYFORCE_MULTIPLIER * (positive ? 1 : -1);
			double spinToAdd = output * Constants.THRUSTER_SPINFORCE_MULTIPLIER * (positive ? 1 : -1);
			if (vertical) {
				body.yForce += xyToAdd;
				double averageX = 0;
				for (int i = 0; i < body.size(); i++) {
					averageX += body.get(i).x;
				}
				averageX /= body.size();
				body.spinForce += (spinToAdd * (x - averageX));
			} else {
				body.xForce += xyToAdd;
				double averageY = 0;
				for (int i = 0; i < body.size(); i++) {
					averageY += body.get(i).x;
				}
				averageY /= body.size();
				body.spinForce += (spinToAdd * (x - averageY));
			}
		}
	}

	@Override
	public double getEnergyCost() {
		return Constants.THRUSTER_BASE_ENERGY_COST * input;
	}
}
