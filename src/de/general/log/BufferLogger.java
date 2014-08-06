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
public class BufferLogger implements ILogInterface
{

	private static final int DEBUG = 0;
	private static final int INFO = 2;
	private static final int WARN = 4;
	private static final int ERROR = 6;

	private class LogRecord
	{
		public final int msgType;
		public final String message;

		public LogRecord(int msgType, String message)
		{
			this.msgType = msgType;
			this.message = message;
		}

		@Override
		public String toString()
		{
			String sMsgType;
			switch (msgType) {
				case DEBUG: sMsgType = "DEBUG"; break;
				case ERROR: sMsgType = "ERROR"; break;
				default: sMsgType = "?????"; break;
			}

			StringWriter w = new StringWriter();
			while (sMsgType.length() < 5) sMsgType = " " + sMsgType;
			w.write(sMsgType);
			w.write(": ");
			w.write(message);
			try {
				w.close();
			} catch (Exception ee) {
			}

			return w.toString();
		}
	}

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	ArrayList<LogRecord> list;
	HashMap<String, String> strings;
	boolean bHasError;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public BufferLogger()
	{
		list = new ArrayList<LogRecord>();
		strings = new HashMap<String, String>();
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public void forwardTo(ILogInterface log)
	{
		for (LogRecord r : list) {
			switch (r.msgType) {
				case DEBUG:		log.debug(r.message); break;
				case INFO:		log.info(r.message); break;
				case WARN:		log.warn(r.message); break;
				case ERROR:		log.error(r.message); break;
				default:		log.error(r.message); break;
			}
		}
		list.clear();
		strings.clear();
	}

	public void debug(String text)
	{
		__append(DEBUG, text);
	}

	public void debug(String text, String param0)
	{
		__append(DEBUG, text + " (" + param0 + ")");
	}

	public void info(String text)
	{
		__append(INFO, text);
	}

	public void info(String text, String param0)
	{
		__append(INFO, text + " (" + param0 + ")");
	}

	public void warn(String text)
	{
		__append(WARN, text);
	}

	public void warn(String text, String param0)
	{
		__append(WARN, text + " (" + param0 + ")");
	}

	public void error(String text)
	{
		bHasError = true;

		__append(ERROR, text);
	}

	public void error(String text, String param0)
	{
		bHasError = true;

		__append(ERROR, text + " (" + param0 + ")");
	}

	private synchronized void __append(int msgType, String text)
	{
		String s = strings.get(text);
		if (s == null) {
			strings.put(text, text);
			s = text;
		}
		list.add(new LogRecord(msgType, s));
	}

	@Override
	public void error(Throwable ee)
	{
		bHasError = true;

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ee.printStackTrace(pw);
		pw.close();
		String s = sw.toString();
		String[] lines = s.split(TextUtils.CRLF);

		synchronized (this) {
			boolean bNotFirstLine = false;
			for (String line : lines) {
				if ((line == null) || (line.length() == 0)) continue;
				line = line.trim();
				if (bNotFirstLine) {
					line = "    " + line;
				}
				__append(ERROR, line);
				bNotFirstLine = true;
			}
		}
	}

	public synchronized void clear()
	{
		list.clear();
	}

	public synchronized ArrayList<String> retrieveBufferedData(boolean bDelete)
	{
		ArrayList<String> ret = new ArrayList<String>();
		for (LogRecord r : list) {
			ret.add(r.toString());
		}
		if (bDelete) {
			list.clear();
			strings.clear();
		}
		return ret;
	}

	public synchronized int countLines()
	{
		return list.size();
	}

	public void dump(PrintWriter pw)
	{
		for (LogRecord s : this.list) {
			pw.println(s.toString());
		}
	}

	public boolean hasError()
	{
		return bHasError;
	}

}
