/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.log;


import java.util.*;


/**
 *
 * @author knauth
 */
public class MultiLogger implements ILogInterface
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	ArrayList<ILogInterface> logger;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public MultiLogger()
	{
		logger = new ArrayList<ILogInterface>();
	}

	public MultiLogger(ILogInterface loggerA, ILogInterface loggerB)
	{
		logger = new ArrayList<ILogInterface>();
		if (loggerA != null) logger.add(loggerA);
		if (loggerB != null) logger.add(loggerB);
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public void debug(String text)
	{
		for (ILogInterface l : logger) {
			l.debug(text);
		}
	}

	@Override
	public void debug(String text, String param0)
	{
		for (ILogInterface l : logger) {
			l.debug(text, param0);
		}
	}

	@Override
	public void info(String text)
	{
		for (ILogInterface l : logger) {
			l.info(text);
		}
	}

	@Override
	public void info(String text, String param0)
	{
		for (ILogInterface l : logger) {
			l.info(text, param0);
		}
	}

	@Override
	public void warn(String text)
	{
		for (ILogInterface l : logger) {
			l.warn(text);
		}
	}

	@Override
	public void warn(String text, String param0)
	{
		for (ILogInterface l : logger) {
			l.warn(text, param0);
		}
	}

	@Override
	public void error(String text)
	{
		for (ILogInterface l : logger) {
			l.error(text);
		}
	}

	@Override
	public void error(String text, String param0)
	{
		for (ILogInterface l : logger) {
			l.error(text, param0);
		}
	}

	@Override
	public void error(Throwable ee)
	{
		for (ILogInterface l : logger) {
			l.error(ee);
		}
	}

}
