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
public class NullLogger implements ILogInterface
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public NullLogger()
	{
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public void debug(String text)
	{
	}

	@Override
	public void debug(String text, String param0)
	{
	}

	@Override
	public void info(String text)
	{
	}

	@Override
	public void info(String text, String param0)
	{
	}

	@Override
	public void warn(String text)
	{
	}

	@Override
	public void warn(String text, String param0)
	{
	}

	@Override
	public void error(String text)
	{
	}

	@Override
	public void error(String text, String param0)
	{
	}

	@Override
	public void error(Throwable ee)
	{
	}

}
