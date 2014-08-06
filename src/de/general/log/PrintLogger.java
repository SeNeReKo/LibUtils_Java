/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.log;


import java.io.*;

import de.general.util.*;


/**
 *
 * @author knauth
 */
public class PrintLogger implements ILogInterface
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

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public void debug(String text)
	{
		System.out.println("DEBUG: " + text);
	}

	@Override
	public void debug(String text, String param0)
	{
		System.out.println("DEBUG: " + text + " (" + param0 + ")");
	}

	@Override
	public void info(String text)
	{
		System.out.println(" INFO: " + text);
	}

	@Override
	public void info(String text, String param0)
	{
		System.out.println(" INFO: " + text + " (" + param0 + ")");
	}

	@Override
	public void warn(String text)
	{
		System.out.println(" WARN: " + text);
	}

	@Override
	public void warn(String text, String param0)
	{
		System.out.println(" WARN: " + text + " (" + param0 + ")");
	}

	@Override
	public void error(String text)
	{
		System.out.println("ERROR: " + text);
	}

	@Override
	public void error(String text, String param0)
	{
		System.out.println("ERROR: " + text + " (" + param0 + ")");
	}

	@Override
	public void error(Throwable ee)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ee.printStackTrace(pw);
		pw.close();
		String s = sw.toString();
		String[] lines = s.split(TextUtils.CRLF);

		boolean bNotFirstLine = false;
		for (String line : lines) {
			if ((line == null) || (line.length() == 0)) continue;
			line = line.trim();
			if (bNotFirstLine) {
				line = "    " + line;
			}
			System.out.println("ERROR: " + line);
			bNotFirstLine = true;
		}
	}

}
