package de.general.cfg;


import de.general.log.*;
import java.io.*;


/**
 *
 * @author knauth
 */
public abstract class AbstractConfigurationReader
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	/*
	public static final String[] LOG_ROTATION_LABELS = { "off", "minutely", "hourly", "daily", "weekly", "monthly" };		// NOPMD
	public static final int[] LOG_ROTATION_VALUES = {																		// NOPMD
		LogRotationFileLogInterface.ROTATE_OFF, LogRotationFileLogInterface.ROTATE_MINUTELY,
		LogRotationFileLogInterface.ROTATE_HOURLY, LogRotationFileLogInterface.ROTATE_DAILY,
		LogRotationFileLogInterface.ROTATE_WEEKLY, LogRotationFileLogInterface.ROTATE_MONTHLY };
	*/

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public AbstractConfigurationReader()
	{
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public abstract String[] getSectionNames();

	/**
	 * List all section keys.
	 */
	public abstract String[] getSectionKeys(String section);

	public abstract boolean containsKey(String section, String key);

	public String[] getStringList(String section, String key) throws IOException
	{
		return getStringList(section, key, null);
	}

	public abstract String[] getStringList(String section, String key, String[] defaultValue) throws IOException;

	public abstract String[] getStringListE(String section, String key) throws IOException;

	public String getString(String section, String key) throws IOException
	{
		return getString(section, key, null);
	}

	public abstract String getString(String section, String key, String defaultValue) throws IOException;

	public abstract String getStringE(String section, String key) throws IOException;

	public boolean getBoolean(String section, String key) throws IOException
	{
		return getBoolean(section, key, false);
	}

	public abstract boolean getBoolean(String section, String key, boolean defaultValue) throws IOException;

	public abstract boolean getBooleanE(String section, String key) throws IOException;

	public int getInt(String section, String key) throws IOException
	{
		return getInt(section, key, -1);
	}

	public int getPortE(String section, String key) throws IOException
	{
		int port = getInt(section, key, -1);
		if ((port <= 0) || (port > 65535)) throw new IOException("No or invalid port number specified for \"" + key + "\" in section \"" + section + "\"!");
		return port;
	}

	public abstract int getInt(String section, String key, int defaultValue) throws IOException;

	public abstract int getIntE(String section, String key) throws IOException;

	/**
	 * Retrieve a value that should be of type enumeration.
	 *
	 * @param	section		The name of the section to retrieve the value from
	 * @param	key			The name of the key that holds the value
	 * @param	set			The strings the value should be of
	 * @param	values		The values represented by the enumeration strings
	 * @param	bMatchCase	Enable or disable case sensitivity
	 * @return	Either returns <code>-1</code> or the value as found in <code>values</code>.
	 */
	public int getSetValue(String section, String key, String[] set, int[] values, boolean bMatchCase) throws IOException
	{
		String v = null;
		try {
			v = getString(section, key, null);
		} catch (Exception e) {
		}
		if (v == null) {
			return -1;
		}
		v = v.trim();
		if (set == null) return -1;
		for (int i = 0; i < set.length; i++) {
			String s = set[i];
			s = s.trim();
			if (bMatchCase) {
				if (v.equals(s)) return values[i];
			} else {
				if (v.equalsIgnoreCase(s)) return values[i];
			}
		}
		return -1;
	}

	public int getLogRotationValue(String section, String key) throws IOException
	{
		return EnumLogLevel.DEBUG;
		// return getSetValue(section, key, LOG_ROTATION_LABELS, LOG_ROTATION_VALUES, false);
	}

	public int getLogLevelE(String section, String key, EnumLogLevel defaultValue) throws IOException
	{
		String s = getStringE(section, key);
		return EnumLogLevel.parse(s);
	}

	public int getLogLevel(String section, String key, int defaultValue) throws IOException
	{
		String s = getString(section, key, null);
		if (s == null) return defaultValue;
		return EnumLogLevel.parse(s);
	}

	public LogCfg getLogCfg(String section, String keyBase, String globalLogDir, ILogInterface defaultStdOutLogger) throws IOException
	{
		return new LogCfg(this, section, keyBase, globalLogDir, defaultStdOutLogger);
	}

}
