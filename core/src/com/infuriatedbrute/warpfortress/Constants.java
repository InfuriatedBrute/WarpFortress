package com.infuriatedbrute.warpfortress;

public class Constants {
	public static final double RAMMING_DAMAGE_MULTIPLIER = 0.5;
	public static final double RAMMING_MOVEMENT_MULTIPLIER = 0.5;
	/*
	 * Make sure there are no half-blocks on the screen. And preferably make the
	 * width a multiple of the width of the gif, but I'm unsure if that's
	 * necessary.
	 */
	public static final int BLOCK_WIDTH = 64;
	public static final int BLOCK_HEIGHT = 64;
	/*
	 * Ideally the screen size should be based on the computer's resolutions
	 * (for maximum possible resolution). But it's much more important that each
	 * object maintains a certain portion of the screen. So for now, these
	 * constants exist.
	 */
	public static final int SCREEN_WIDTH = 1024;
	public static final int SCREEN_HEIGHT = 640;
	public static int NON_UI_SCREEN_WIDTH_IN_BLOCKS = (SCREEN_WIDTH) / BLOCK_WIDTH;
	public static int NON_UI_SCREEN_HEIGHT_IN_BLOCKS = (SCREEN_WIDTH) / BLOCK_HEIGHT;
	public static final double OUTPUT_EXPONENT = 0.65;
	public static final double PIERCE_INPUT_MULTIPLIER = 0.1;
	public static final double RANGE_INPUT_MULTIPLIER = 0.1;
	public static final int SHIFT_CAMERA_MOVEMENT = 10;
	public static final int BATTERY_MAX_ENERGY = 10;
	public static final double BATTERY_ENERGY_GROWTH = 0.1;
	public static final int BATTERY_TRANSFER_RANGE = 5;
	public static final double BATTERY_TRANSFER_RATE = 99999;
	public static final int BATTERY_EXPLOSION_RANGE = 2;
	public static final boolean BATTERY_EMP_DAMAGE = false;
	public static final int BATTERY_EXPLOSION_DAMAGE = 1;
	public static final int REACTOR_MAX_ENERGY = 2;
	public static final double REACTOR_ENERGY_GROWTH = 0.5;
	public static final int REACTOR_TRANSFER_RANGE = 2;
	public static final double REACTOR_TRANSFER_RATE = 99999;
	public static final int REACTOR_EXPLOSION_RANGE = 2;
	public static final boolean REACTOR_EMP_DAMAGE = true;
	public static final int REACTOR_EXPLOSION_DAMAGE = 1;
	public static final int SHIELD_ARMOR_HEALTH = 1;
	public static final double GUN_DAMAGE = 1;
	public static final int SHIELD_PROJECTOR_BASE_ENERGY_COST = 1;
	public static final double GUN_BASE_ENERGY_COST = 1;
	public static final double THRUSTER_BASE_ENERGY_COST = 1;
	public static final double THRUSTER_SPINFORCE_MULTIPLIER = 1;
	public static final double THRUSTER_XYFORCE_MULTIPLIER = 1;
	public static int BASE_ARMOR_HEALTH = 5;
	public static int BASE_DEFAULT_BLOCK_HEALTH = 1;
	public static double DEFAULT_BLOCK_MAX_POWER_REQUIRED = 1;
}
