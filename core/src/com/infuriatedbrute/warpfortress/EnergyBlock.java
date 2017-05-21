package com.infuriatedbrute.warpfortress;

public abstract class EnergyBlock extends Block {

	public void activate() {
		if ((storedEnergy - energyGrowth()) <= maxEnergy()) {
			storedEnergy += energyGrowth();
		} else {
			storedEnergy = Constants.BATTERY_MAX_ENERGY;
		}
		for (int i = 0; i < body.size(); i++) {
			Block b = body.get(i);
			if (Math.abs(b.x - this.x) + Math.abs(b.y - this.y) < transferRange() && b instanceof OutputBlock) {
				OutputBlock o = (OutputBlock) b;
				double energyToTransfer = o.maxPowerRequired() * o.input;
				if (energyToTransfer > energyTransferRate()) {
					energyToTransfer = energyTransferRate();
				}
				if (storedEnergy >= energyToTransfer) {
					storedEnergy -= energyToTransfer;
					o.storedEnergy += energyToTransfer;
				}
			}
		}
	}

	@Override
	public void updateHealth() {
		if (this.health <= 0) {
			if (empDamage()) {
				for (Block b : body.region.blocksNear(this.x, this.y, explosionRange(), explosionRange())) {
					b.storedEnergy -= explosionDamage();
					if (b.storedEnergy < 0) {
						b.storedEnergy = 0;
					}
				}
			} else {
				for (Block b : body.region.blocksNear(this.x, this.y, explosionRange(), explosionRange())) {
					b.health -= explosionDamage();
					b.updateHealth();
				}

			}
		}
		super.updateHealth();
	}

	protected abstract int maxEnergy();

	protected abstract double energyGrowth();

	protected abstract double energyTransferRate();

	protected abstract int transferRange();

	protected abstract int explosionRange();

	protected abstract int explosionDamage();

	protected abstract boolean empDamage();
}
