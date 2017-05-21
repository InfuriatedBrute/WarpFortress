package com.infuriatedbrute.warpfortress;

public class Reactor extends EnergyBlock {
	public int storedEnergy = Constants.REACTOR_MAX_ENERGY;

	@Override
	protected int maxEnergy() {
		return Constants.REACTOR_MAX_ENERGY;
	}

	@Override
	protected double energyGrowth() {
		return Constants.REACTOR_ENERGY_GROWTH;
	}

	@Override
	protected double energyTransferRate() {
		return Constants.REACTOR_TRANSFER_RATE;
	}

	@Override
	protected int transferRange() {
		return Constants.REACTOR_TRANSFER_RANGE;
	}

	protected boolean empDamage(){
		return Constants.REACTOR_EMP_DAMAGE;
	}

	@Override
	protected int explosionRange() {
		return Constants.REACTOR_EXPLOSION_RANGE;
	}

	@Override
	protected int explosionDamage() {
		return Constants.REACTOR_EXPLOSION_DAMAGE;
	}
}
