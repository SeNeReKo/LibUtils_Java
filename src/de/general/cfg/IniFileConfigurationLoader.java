package de.general.cfg;


import java.io.*;

import de.general.util.*;
import de.general.cfg.impl.IniFileConfigurationReader;
import de.general.io.*;
import de.general.log.*;


/**
 *
 * @author knauth
 */
public class IniFileConfigurationLoader implements IConfigurationLoader
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	String filePath;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public IniFileConfigurationLoader(String filePath)
	{
		this.filePath = filePath;
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public AbstractConfigurationReader loadConfiguration(ILogInterface log) throws Exception
	{
		log.debug("Loading configuration from file: " + IOUtils.makeAbsolutePath(null, filePath));
		IniFile iniFile = new IniFile(filePath);
		return new IniFileConfigurationReader(iniFile);
	}

}