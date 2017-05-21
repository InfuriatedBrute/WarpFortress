package com.infuriatedbrute.warpfortress;

public class Battery extends EnergyBlock {
	public int storedEnergy = Constants.BATTERY_MAX_ENERGY;

	@Override
	protected int maxEnergy() {
		return Constants.BATTERY_MAX_ENERGY;
	}

	@Override
	protected double energyGrowth() {
		return Constants.BATTERY_ENERGY_GROWTH;
	}

	@Override
	protected double energyTransferRate() {
		return Constants.BATTERY_TRANSFER_RATE;
	}

	@Override
	protected int transferRange() {
		return Constants.BATTERY_TRANSFER_RANGE;
	}
	
	protected boolean empDamage(){
		return Constants.BATTERY_EMP_DAMAGE;
	}

	@Override
	protected int explosionRange() {
		return Constants.BATTERY_EXPLOSION_RANGE;
	}

	@Override
	protected int explosionDamage() {
		return Constants.BATTERY_EXPLOSION_DAMAGE;
	}

}
