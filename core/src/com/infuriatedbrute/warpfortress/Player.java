package com.infuriatedbrute.warpfortress;

import java.util.LinkedHashMap;

/**
 * An object representing an Player and their controls. The Player may be a  human player or
 * an AI that attempts to simulate a human player to some degree.
 *
 */
public class Player {
	public LinkedHashMap<Character, Computer> keyToComputerBlock = new LinkedHashMap<>();
	public Cockpit cockpit = new Cockpit(); //since it's not on the map it doesn't do much
	public boolean AI = true;

	/**
	 * Do what the actor would do on their turn if they pressed the given key.
	 * Do not do the things that inherently come with a turn such as rendering
	 * and giving the AI a turn; let other methods handle that, since this can
	 * be an AI actor.
	 * 
	 * @param key
	 *            the key that was pressed
	 */
	public void playerPressed(Character key) {
		Computer c = keyToComputerBlock.get(key);
		if (c != null) {
			c.giveOrders();
		} else {
			com.infuriatedbrute.warpfortress.StaticMethods.blankOrderMessage(this.toString());
		}
	}

	/**
	 * Randomly picks one of the keys in keyToComputerBlock and then calls
	 * this.playerPressed(k) where k is that key.
	 */
	public void pressRandomValidKey() {
		int rand = (int) Math.floor(Math.random() * keyToComputerBlock.size());
		playerPressed((Character) (keyToComputerBlock.keySet().toArray()[rand]));
	}
}
