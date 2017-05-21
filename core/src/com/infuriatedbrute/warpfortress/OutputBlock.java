package com.infuriatedbrute.warpfortress;

import java.util.ArrayList;

public abstract class OutputBlock extends Block {

	/**
	 * A value between 0 and 1 (inclusive) representing the input currently
	 * required as a ratio of the maximum input possible to require.
	 */
	public double input = 0;
	/**
	 * A value between 0 and 1 (inclusive) representing the output that will
	 * currently be produced (given sufficient input) as a ratio of the maximum
	 * output possible to produce. Should always be equal to the input to the
	 * power of statics.Constant.OUTPUT_EXPONENT, which should always be a
	 * fraction in order to cause diminishing returns.
	 */
	public double output = 0;
	// TODO make output matter for all subclasses

	@Override
	public void parseOrder(ArrayList<String> orders) {
		super.parseOrder(orders);
		if (orders.size() > 2) {
			String order = orders.get(2).toLowerCase();
			try {
				input = Integer.parseInt(order);
				assert (input <= 1);
				output = Math.pow(input, com.infuriatedbrute.warpfortress.Constants.OUTPUT_EXPONENT);
			} catch (NumberFormatException e) {
				com.infuriatedbrute.warpfortress.StaticMethods.blankOrderMessage(this.toString());
			}
		}
	}

	public double maxPowerRequired() {
		return Constants.DEFAULT_BLOCK_MAX_POWER_REQUIRED;
	}

	protected boolean payEnergy() {
		double c = getEnergyCost();
		if (storedEnergy < c) {
			return false;
		} // else
		storedEnergy -= c;
		return true;
	}

	/**
	 * This typically returns Constants.[classname]_BASE_ENERGY_COST * input.
	 * But there are exceptions, for example the Grapple class does not use
	 * output during activation, so for that class this method only returns
	 * Constants.GRAPPLE_BASE_ENERGY_COST.
	 * 
	 * @return The energy cost for this block to successfully activate
	 */
	abstract public double getEnergyCost();
}
