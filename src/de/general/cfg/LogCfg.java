package de.general.cfg;


import java.io.*;
import java.util.*;

import de.general.log.*;
import de.general.util.*;
import de.general.io.*;


public class LogCfg
{

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	String globalLogDir;
	String name;
	int logLevel;
	String logFileName;
	boolean bDebugLogCalls;
	int logRotation;
	boolean bCompressLogAfterRotation;
	String moveToDirAfterRotationDirPath;
	boolean bAdditionalyPrintToStdOut;
	ILogInterface defaultStdOutLogger;

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	protected LogCfg(AbstractConfigurationReader cfgBase, String section, String keyBase, String globalLogDir,
		ILogInterface defaultStdOutLogger) throws IOException
	{
		this.defaultStdOutLogger = defaultStdOutLogger;
		this.name = section + "-" + keyBase;
		this.globalLogDir = globalLogDir;

		logLevel = cfgBase.getLogLevel(section, keyBase + ".logLevel", EnumLogLevel.DEBUG);
		logFileName = cfgBase.getStringE(section, keyBase + ".logFileName");
		bDebugLogCalls = cfgBase.getBooleanE(section, keyBase + ".debugLogCalls");
		logRotation = cfgBase.getLogRotationValue(section, keyBase + ".logRotation");
//		if (logRotation < 0) log.warn("No log rotation for framework log enabled!");
		bCompressLogAfterRotation = cfgBase.getBooleanE(section, keyBase + ".enableCompressionAfterRotation");
		moveToDirAfterRotationDirPath = cfgBase.getString(section, keyBase + ".moveToDirAfterRotationDirPath", null);
		bAdditionalyPrintToStdOut = cfgBase.getBoolean(section, keyBase + ".additionalyPrintToStdOut", false);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ILogInterface createLogger() throws IOException
	{
		if (bAdditionalyPrintToStdOut) {
			MultiLogger ml = new MultiLogger(defaultStdOutLogger, createLogger0());
			return ml;
		} else {
			return createLogger0();
		}
	}

	private ILogInterface createLogger0() throws IOException
	{
		// new LogRotationFileLogInterface(IOUtils.makeAbsolutePath(globalLogDir, logFileName, true), true, logRotation);
		FileLogger fli = new FileLogger(IOUtils.makeAbsolutePath(globalLogDir, logFileName), true);

		/*
		fli.enableTimeStamping(true);
		if (bDebugLogCalls) {
			fli.showCallingInfo(true);
			fli.debugLogOutput(true);
		}
		if (bCompressLogAfterRotation) {
			fli.enableCompressionAfterRotation(true);
		}
		if (moveToDirAfterRotationDirPath != null) {
			fli.setMoveToDirAfterRotationDirPath(IOUtils.makeAbsolutePath(globalLogDir, moveToDirAfterRotationDirPath, true));
		}
		FrameworkLogInterface log = new FrameworkLogInterface(name, fli);
		*/
		//log.setLogLevel(logLevel);
//		BufferedLogInterface2 bli = new BufferedLogInterface2();
//		log.add(bli);
//		logBuffers.add(new WeakReference<BufferedLogInterface2>(bli));
		return fli;
	}

}
