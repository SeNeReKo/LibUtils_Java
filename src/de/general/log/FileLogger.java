/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.log;


import java.util.*;
import java.io.*;

import de.general.util.*;


/**
 *
 * @author knauth
 */
public class FileLogger implements ILogInterface
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	private final static String CRLF = "" + (char)13 + (char)10;

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	String filePath;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public FileLogger(String filePath, boolean bAppend)
	{
		this.filePath = filePath;

		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public void debug(String text)
	{
		__append("DEBUG", text);
	}

	@Override
	public void debug(String text, String param0)
	{
		__append("DEBUG", text + " (" + param0 + ")");
	}

	@Override
	public void info(String text)
	{
		__append("INFO", text);
	}

	@Override
	public void info(String text, String param0)
	{
		__append("INFO", text + " (" + param0 + ")");
	}

	@Override
	public void warn(String text)
	{
		__append("WARN", text);
	}

	@Override
	public void warn(String text, String param0)
	{
		__append("WARN", text + " (" + param0 + ")");
	}

	@Override
	public void error(String text)
	{
		__append("ERROR", text);
	}

	@Override
	public void error(String text, String param0)
	{
		__append("ERROR", text + " (" + param0 + ")");
	}

	private boolean __append(String msgType, String text)
	{
		try {
			FileWriter w = new FileWriter(filePath, true);
			while (msgType.length() < 5) msgType = " " + msgType;
			w.write(msgType);
			w.write(": ");
			w.write(text);
			w.write(CRLF);
			w.close();
			return true;
		} catch (Exception ee) {
			ee.printStackTrace();
			return false;
		}
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
			__append("ERROR", line);
			bNotFirstLine = true;
		}
	}

}
