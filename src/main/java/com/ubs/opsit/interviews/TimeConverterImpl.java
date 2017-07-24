package com.ubs.opsit.interviews;

import java.util.StringTokenizer;

import com.ubs.opsit.interviews.enums.BerlinClockLamp;
import com.ubs.opsit.interviews.enums.BerlinClockLampValue;
import com.ubs.opsit.interviews.enums.BerlinClockRedLamps;
import com.ubs.opsit.interviews.enums.BerlinClockRows;

/**
 * TimeConverterImpl.
 *
 * Creates a representation of a berlin clock from a String and provides a way
 * of printing this.
 */
public class TimeConverterImpl implements TimeConverter {

	public static final String NEW_LINE = System.getProperty("line.separator");

	private StringBuffer expectedTime;

	public TimeConverterImpl() {
		expectedTime = new StringBuffer();
	}

	@Override
	/**
	 * Converts the String into a BerlinClock format.
	 * 
	 * @param aTime-
	 *            time format in HH:MM:SS
	 * 
	 */
	public String convertTime(String aTime) {
		if (aTime == null)
			throw new IllegalArgumentException(BerlinClockConstant.NO_TIME_ERROR);

		StringTokenizer clockTokenizer = new StringTokenizer(aTime, ":");
		int hours;
		int minutes;
		int seconds;
		while (clockTokenizer.hasMoreTokens()) {
			try {
				hours = Integer.parseInt(clockTokenizer.nextToken());
				minutes = Integer.parseInt(clockTokenizer.nextToken());
				seconds = Integer.parseInt(clockTokenizer.nextToken());
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(BerlinClockConstant.NUMERIC_TIME_ERROR);
			}

			if (hours < 0 || hours > 24)
				throw new IllegalArgumentException("Hours out of bounds.");
			if (minutes < 0 || minutes > 60)
				throw new IllegalArgumentException("Minutes out of bounds.");
			if (seconds < 0 || seconds > 60)
				throw new IllegalArgumentException("Seconds out of bounds.");

			secondsRow(seconds);
			expectedTime.append(NEW_LINE);
			hoursFirstRow(hours);
			expectedTime.append(NEW_LINE);
			hourSecondRow(hours % BerlinClockLampValue.FIRSTROWHOURS.getlampValue());
			expectedTime.append(NEW_LINE);
			minutesFirstRow(minutes);
			expectedTime.append(NEW_LINE);
			minutesSecondRow(minutes % BerlinClockLampValue.FIRSTROWMINUTES.getlampValue());
		}
		System.out.println(expectedTime.toString());
		return expectedTime.toString();
	}

	/**
	 * Converts the seconds of HH:MM:SS to equivalent lamp .
	 *
	 * @param seconds
	 *            - SS(Seconds) of HH:MM:SS
	 */
	private void secondsRow(int seconds) {
		expectedTime = (seconds % BerlinClockConstant.BLINK == 0)
				? expectedTime.append(BerlinClockLamp.YELLOWLAMP.getLamp())
				: expectedTime.append(BerlinClockLamp.OFFLAMP.getLamp());

	}

	/**
	 * Converts the hours of HH:MM:SS to equivalent first row of hours .
	 *
	 * @param hours
	 *            - HH(Hours) of HH:MM:SS
	 */
	private void hoursFirstRow(int hours) {
		processRowTime(BerlinClockRows.FIRSTROWLHOURSLAMPS.getRows(), BerlinClockLampValue.FIRSTROWHOURS.getlampValue(),
				BerlinClockLamp.REDLAMP.getLamp(), BerlinClockLamp.OFFLAMP.getLamp(), hours);

	}

	/**
	 * Converts the remaining hours of HH:MM:SS to equivalent second row of hours .
	 *
	 * @param hours
	 *            - remaining hours after first row.
	 */
	private void hourSecondRow(int hours) {
		processRowTime(BerlinClockRows.SECONDROWLHOURSLAMPS.getRows(),
				BerlinClockLampValue.SECONDROWHOURS.getlampValue(), BerlinClockLamp.REDLAMP.getLamp(),
				BerlinClockLamp.OFFLAMP.getLamp(), hours);
	}

	/**
	 * Converts the MM(minutes) of HH:MM:SS to equivalent first row of minutes.
	 *
	 * @param minutes
	 *            - MM(Minutes) of HH:MM:SS.
	 */
	private void minutesFirstRow(int minutes) {
		for (int i = 1; i <= BerlinClockRows.FIRSTROWMINUTESLAMP.getRows(); i++) {
			if (minutes >= BerlinClockLampValue.FIRSTROWMINUTES.getlampValue()) {
				expectedTime = (i == BerlinClockRedLamps.THIRD.getoccurence()
						|| i == BerlinClockRedLamps.SIXTH.getoccurence()
						|| i == BerlinClockRedLamps.NINTH.getoccurence())
								? expectedTime.append(BerlinClockLamp.REDLAMP.getLamp())
								: expectedTime.append(BerlinClockLamp.YELLOWLAMP.getLamp());
				minutes = minutes - BerlinClockLampValue.FIRSTROWMINUTES.getlampValue();
			}

			else {
				expectedTime.append(BerlinClockLamp.OFFLAMP.getLamp());
			}
		}
	}

	/**
	 * Converts the remaining minutes of HH:MM:SS to equivalent second row of
	 * minutes.
	 *
	 * @param minutes
	 *            - the remaining minutes after first row of minutes.
	 */
	private void minutesSecondRow(int minutes) {
		processRowTime(BerlinClockRows.SECONDROWLMINUTESLAMPS.getRows(),
				BerlinClockLampValue.SECONDROWMINUTES.getlampValue(), BerlinClockLamp.YELLOWLAMP.getLamp(),
				BerlinClockLamp.OFFLAMP.getLamp(), minutes);
	}

	/**
	 * Processes the each row with the lamps.
	 *
	 * @param lamps
	 *            - the number of lamps in a row.
	 * @param lampValue
	 *            - the value of a lamp in terms of hh/mm.
	 * @param onLamps
	 *            - The Lamp when HH/MM is there.
	 * @param offLamp
	 *            - The Lamp when HH/MM is zero.
	 * @param time
	 *            - the time to be used for printing BerlinClock.
	 */
	private int processRowTime(int lamps, int lampValue, String onLamp, String offLamp, int time) {
		for (int i = 0; i < lamps; i++) {
			expectedTime = (time >= lampValue) ? expectedTime.append(onLamp) : expectedTime.append(offLamp);
			time = time - lampValue;
		}
		return time;
	}
}
