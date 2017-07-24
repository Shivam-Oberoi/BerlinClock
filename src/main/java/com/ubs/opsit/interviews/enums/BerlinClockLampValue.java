package com.ubs.opsit.interviews.enums;
/**
 * BerlinClockLampValue
 * The ENUM the value of each lamp in each row.
 * @author shivamoberoi
 *
 */
public enum BerlinClockLampValue {
	FIRSTROWHOURS(5), SECONDROWHOURS(1), FIRSTROWMINUTES(5), SECONDROWMINUTES(1);

	private int lampValue;

	private BerlinClockLampValue(int lampValue) {
		this.lampValue = lampValue;
	}

	public int getlampValue() {
		return lampValue;
	}
}
