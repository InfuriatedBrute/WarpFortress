package com.infuriatedbrute.warpfortress;

public class ShieldArmor extends Block{
	public ShieldArmor(double output){
		super();
		health = Constants.SHIELD_ARMOR_HEALTH*output;
	}
	@Override
	public void activate() {
		this.health=0;
		updateHealth();
	}

}
