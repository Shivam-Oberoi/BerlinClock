package com.ubs.opsit.interviews.enums;

/**
 * Enum having all different lamps.
 * 
 * @author shivamoberoi
 *
 */
public enum BerlinClockLamp {

	REDLAMP("R"), YELLOWLAMP("Y"), OFFLAMP("O");

	private String lamps;

	private BerlinClockLamp(String lamps) {
		this.lamps = lamps;
	}

	public String getLamp() {
		return lamps;
	}

}
