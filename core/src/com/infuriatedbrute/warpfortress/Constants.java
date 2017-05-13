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
	public static int BASE_ARMOR_HEALTH = 5;
	public static int BASE_DEFAULT_BLOCK_HEALTH = 1;

}
