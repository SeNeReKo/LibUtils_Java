/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.log;


/**
 *
 * @author knauth
 */
public class ProgressLogger
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	long timeStamp;
	ILogInterface log;
	String logText;
	double max3;
	double max2;
	int i;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public ProgressLogger(ILogInterface log, String logText, long max)
	{
		this.max2 = max;
		this.max3 = 1000.0 / max2;
		this.log = log;
		this.logText = logText;
		timeStamp = System.currentTimeMillis();
		log.debug(logText + " ...");
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	private String __formatTime(int tSeconds)
	{
		if (tSeconds < 0) return "??:??:??";

		int tMinutes = tSeconds / 60;
		tSeconds = tSeconds % 60;
		int tHours = tMinutes / 60;
		tMinutes = tMinutes % 60;

		return (
			((tHours >= 10) ? "" + tHours : "0" + tHours)
			+ ((tMinutes >= 10) ? ":" + tMinutes : ":0" + tMinutes)
			+ ((tSeconds >= 10) ? ":" + tSeconds : ":0" + tSeconds)
			);
	}

	public void next()
	{
		i++;
		if ((i & 0x03ff) == 0) {
			int estimatedSecondsLeft = -1;
			int tGuessedTotalSeconds = -1;
			int tElapsedSeconds = -1;
			double progress = -1;
			if (i > 0) {
				progress = i / max2;
				long tElapsed = System.currentTimeMillis() - timeStamp;
				tElapsedSeconds = (int)(tElapsed / 1000.0);
				if (tElapsedSeconds > 0) {
					long tGuessedTotal = (long)(tElapsed / (progress /* * progress */));
					tGuessedTotalSeconds = (int)(tGuessedTotal / 1000);
					estimatedSecondsLeft = tGuessedTotalSeconds - tElapsedSeconds;
				}
			}
			log.debug(logText + " " + ((int)(i * max3) / 10.0)
				+ "% (" + __formatTime(tElapsedSeconds)
				+ " " + __formatTime(tGuessedTotalSeconds)
				+ "  ETA " + __formatTime(estimatedSecondsLeft) + ")");
		}
	}

	public void completed()
	{
		long t = System.currentTimeMillis() - timeStamp;
		log.debug(logText + " completed (" + __formatTime((int)t) + ")");
	}

}
