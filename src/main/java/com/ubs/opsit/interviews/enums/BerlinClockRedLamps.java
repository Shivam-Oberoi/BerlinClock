package com.ubs.opsit.interviews.enums;

/**
 * 
 * ENUM for occurence of Red Lamps in the minutes first row.
 * 
 * @author shivamoberoi
 *
 */
public enum BerlinClockRedLamps {
	THIRD(3), SIXTH(6), NINTH(9);

	private int occurence;

	private BerlinClockRedLamps(int occurence) {
		this.occurence = occurence;
	}

	public int getoccurence() {
		return occurence;
	}
}
