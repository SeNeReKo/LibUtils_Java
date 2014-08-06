package de.general.cfg.impl;


import de.general.cfg.AbstractConfigurationReader;
import de.general.util.*;
import java.io.*;


/**
 *
 * @author knauth
 */
public class IniFileConfigurationReader extends AbstractConfigurationReader
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	IniFile iniFile;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public IniFileConfigurationReader(IniFile iniFile)
	{
		this.iniFile = iniFile;
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public String[] getSectionNames()
	{
		return iniFile.getSections();
	}

	@Override
	public String[] getSectionKeys(String section)
	{
		String[] ret = iniFile.getSectionKeys(section);
		if (ret == null) return new String[0];
		return ret;
	}

	@Override
	public boolean containsKey(String section, String key)
	{
		return iniFile.containsKey(section, key);
	}

	@Override
	public String[] getStringList(String section, String key, String[] defaultValue) throws IOException
	{
		String s = iniFile.getString(section, key, null);
		if (s == null) return defaultValue;
		return XUtils.splitString(s, ',');
	}

	@Override
	public String[] getStringListE(String section, String key) throws IOException
	{
		String s = iniFile.getString(section, key, null);
		if (s == null) throw new IOException("No such key: " + section + "|" + key);
		return XUtils.splitString(s, ',');
	}

	@Override
	public String getString(String section, String key, String defaultValue) throws IOException
	{
		String s = iniFile.getString(section, key, defaultValue);
		return s;
	}

	@Override
	public String getStringE(String section, String key) throws IOException
	{
		String s = iniFile.getString(section, key, null);
		if (s == null) throw new IOException("No such key: " + section + "|" + key);
		return s;
	}

	@Override
	public boolean getBoolean(String section, String key, boolean defaultValue) throws IOException
	{
		int n = iniFile.getInt(section, key, defaultValue ? 1 : 0);
		return n > 0;
	}

	@Override
	public boolean getBooleanE(String section, String key) throws IOException
	{
		int n = iniFile.getInt(section, key, -1);
		if (n < 0) throw new IOException("No such key: " + section + "|" + key);
		return n > 0;
	}

	@Override
	public int getInt(String section, String key, int defaultValue) throws IOException
	{
		int n = iniFile.getInt(section, key, defaultValue);
		return n;
	}

	@Override
	public int getIntE(String section, String key) throws IOException
	{
		int n = iniFile.getInt(section, key, -1);
		if (n < 0) throw new IOException("No such key: " + section + "|" + key);
		return n;
	}

}
