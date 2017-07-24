package com.ubs.opsit.interviews.enums;

/**
 *
 * ENUM of no of lamps in all the rows.
 * 
 * @author shivamoberoi
 *
 */
public enum BerlinClockRows {
	FIRSTROWLHOURSLAMPS(4), SECONDROWLHOURSLAMPS(4), FIRSTROWMINUTESLAMP(11), SECONDROWLMINUTESLAMPS(4);

	private int rows;

	private BerlinClockRows(int rows) {
		this.rows = rows;
	}

	public int getRows() {
		return rows;
	}
}
