/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.util;


import java.util.*;
import java.text.*;
import de.general.log.*;
import java.lang.reflect.ParameterizedType;


/**
 *
 * @author knauth
 */
public class XUtils
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	public static final SimpleDateFormat DATE_FORMATTER_ISO = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATE_FORMATTER_DE = new SimpleDateFormat("dd.MM.yyyy");
	public static final SimpleDateFormat[] DATE_FORMATTERS = new SimpleDateFormat[] {
		DATE_FORMATTER_ISO, DATE_FORMATTER_DE
	};

	public final static String CRLF = "" + (char)13 + (char)10;

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public static int findNext(String s, String chars, int fromIndex)
	{
		int p = Integer.MAX_VALUE;
		for (int i = 0; i < chars.length(); i++) {
			char c = chars.charAt(i);
			int p2 = s.indexOf(c, fromIndex);
			if (p2 < 0) continue;
			if (p2 < p) p = p2;
		}
		if (p == Integer.MAX_VALUE) return -1;
		else return p;
	}

	public static String[] splitString(String s)
	{
		if (s == null) return new String[0];

		ArrayList<String> ret = new ArrayList<String>();
		int pos = 0;
		while (true) {
			int end = findNext(s, CRLF, pos);
			if (end < 0) {
				ret.add(s.substring(pos));
				break;
			} else {
				int len = end - pos;
				if (len > 0) {
					ret.add(s.substring(pos, end));
				}
				pos = end + 1;
			}
		}

		return ret.toArray(new String[ret.size()]);
	}

	public static String[] splitString(String s, char c)
	{
		if (s == null) return new String[0];

		ArrayList<String> ret = new ArrayList<String>();
		int pos = 0;
		while (true) {
			int end = s.indexOf(s, pos);
			if (end < 0) {
				ret.add(s.substring(pos));
				break;
			} else {
				int len = end - pos;
				if (len > 0) {
					ret.add(s.substring(pos, end));
				}
				pos = end + 1;
			}
		}

		return ret.toArray(new String[ret.size()]);
	}

	public static void noop()
	{
	}

	public static Date parseDate(String strDate)
	{
		if (strDate == null) return null;
		for (SimpleDateFormat f : DATE_FORMATTERS) {
			try {
				Date date = f.parse(strDate);
				if (date != null) return date;
			} catch (Exception ee) {
			}
		}
		return null;
	}

	public static String toString(Date date)
	{
		if (date == null) return null;
		return DATE_FORMATTER_ISO.format(date);
	}

	public static String formatString(String s)
	{
		if (s == null) return "(null)";
		return "\"" + s + "\"";
	}

	public static String formatStrObject(String s)
	{
		if (s == null) return "(null)";
		return s;
	}

	/**
	 * Returns <code>true</code> if the specified string is a long or a double.
	 */
	public static boolean isNumeric(String s)
	{
		try {
			Double d = Double.parseDouble(s);
			return true;
		} catch (Exception ee) {
			try {
				Long l = Long.parseLong(s);
				return true;
			} catch (Exception eee) {
			}
			return false;
		}
	}

	public static boolean equals(String s1, String s2)
	{
		if (s1 == null) {
			if (s2 == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (s2 == null) {
				return false;
			} else {
				return s1.equals(s2);
			}
		}
	}

	public static boolean equals(Object o1, Object o2)
	{
		if (o1 == null) {
			if (o2 == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (o2 == null) {
				return false;
			} else {
				return o1.equals(o2);
			}
		}
	}

	public static boolean equals(byte[] s1, byte[] s2)
	{
		if (s1 == null) {
			if (s2 == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (s2 == null) {
				return false;
			} else {
				if (s1.length != s2.length) return false;
				for (int i = 0; i < s1.length; i++) {
					if (s1[i] != s2[i]) return false;
				}
				return true;
			}
		}
	}

	public static Object[] createGenericArray(Class componentType, int len)
	{
		Object[] ret = (Object[])(java.lang.reflect.Array.newInstance(componentType, len));
		return ret;
	}

	public static Object[] createGenericArray(Object arrayElementPrototype, int len)
	{
		return createGenericArray(arrayElementPrototype.getClass(), len);
	}

	public static String padWithLeadingZeros(int n, int minLength)
	{
		return padWithLeadingZeros("" + n, minLength);
	}

	public static String padWithLeadingZeros(String s, int minLength)
	{
		while (s.length() < minLength) s = "0" + s;
		return s;
	}

	public static int max(int a, int b)
	{
		if (a > b) return a;
		else return b;
	}

	public static double max(double a, double b)
	{
		if (a > b) return a;
		else return b;
	}

	public static int max(int a, int b, int c)
	{
		if (a > b) {
			if (a > c) return a;
			else return c;
		} else {
			if (b > c) return b;
			else return c;
		}
	}

	public static double max(double a, double b, double c)
	{
		if (a > b) {
			if (a > c) return a;
			else return c;
		} else {
			if (b > c) return b;
			else return c;
		}
	}

	public static int min(int a, int b)
	{
		if (a < b) return a;
		else return b;
	}

	public static double min(double a, double b)
	{
		if (a < b) return a;
		else return b;
	}

	public static int min(int a, int b, int c)
	{
		if (a < b) {
			if (a < c) return a;
			else return c;
		} else {
			if (b < c) return b;
			else return c;
		}
	}

	public static double min(double a, double b, double c)
	{
		if (a < b) {
			if (a < c) return a;
			else return c;
		} else {
			if (b < c) return b;
			else return c;
		}
	}

	public static <T> boolean compareEqualityTo(Set<T> setA, Set<T> setB, ILogInterface log)
	{
		HashSet<T> setA2 = new HashSet<T>(setA);
		HashSet<T> setB2 = new HashSet<T>(setB);

		boolean b = true;

		if (log != null) {

			for (Object t2 : setA2.toArray()) {
				T t = (T)t2;
				if (!setB2.remove(t)) {
					log.error("Element " + t + " found only on left hand side!");
					b = false;
				}
			}
			for (T t : setB2) {
				log.error("Element " + t + " found only on right hand side!");
				b = false;
			}

		} else {

			for (Object t2 : setA2.toArray()) {
				T t = (T)t2;
				if (!setB2.remove(t)) {
					b = false;
					break;
				}
			}

			if (b) b = setB2.size() == 0;

		}

		return b;
	}

	public static void reverse(Object[] array)
	{
		for (int i = 0; i < array.length / 2; i++) {
			Object obj = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = obj;
		}
	}

	public static String formatDuration(long milliseconds)
	{
		int s = (int)(milliseconds / 1000);
		milliseconds = milliseconds % 1000;

		int m = s / 60;
		s = s % 60;

		int h = m / 60;
		m = m % 60;

		return padWithLeadingZeros(h, 2) + ":" + padWithLeadingZeros(m, 2) + ":" + padWithLeadingZeros(s, 2)
			+ "." + padWithLeadingZeros((int)milliseconds, 3);
	}

	public static <T> List<T[]> combinations2unordered(List<T> someData)
	{
		Object[] dataArray = someData.toArray();
		Class<T> clazz = (Class<T>) ((ParameterizedType)(dataArray.getClass()).getGenericSuperclass()).getActualTypeArguments()[0];
		int len = dataArray.length;
		ArrayList<T[]> ret = new ArrayList<T[]>();
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				T[] data = (T[])(java.lang.reflect.Array.newInstance(clazz, 2));
				data[0] = (T)(dataArray[i]);
				data[1] = (T)(dataArray[j]);
				ret.add(data);
			}
		}
		return ret;
	}

	public static <T> List<T[]> combinations3unordered(List<T> someData)
	{
		Object[] dataArray = someData.toArray();
		Class<T> clazz = (Class<T>) ((ParameterizedType)(dataArray.getClass()).getGenericSuperclass()).getActualTypeArguments()[0];
		int len = dataArray.length;
		ArrayList<T[]> ret = new ArrayList<T[]>();
		for (int i = 0; i < len - 2; i++) {
			for (int j = i + 1; j < len - 1; j++) {
				for (int k = j + 1; k < len; k++) {
					T[] data = (T[])(java.lang.reflect.Array.newInstance(clazz, 3));
					data[0] = (T)(dataArray[i]);
					data[1] = (T)(dataArray[j]);
					data[2] = (T)(dataArray[k]);
					ret.add(data);
				}
			}
		}
		return ret;
	}

	public static <T> List<T[]> combinations2ordered(List<T> someData)
	{
		Object[] dataArray = someData.toArray();
		Class<T> clazz = (Class<T>) ((ParameterizedType)(dataArray.getClass()).getGenericSuperclass()).getActualTypeArguments()[0];
		int len = dataArray.length;
		ArrayList<T[]> ret = new ArrayList<T[]>();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (i != j) {
					T[] data = (T[])(java.lang.reflect.Array.newInstance(clazz, 2));
					data[0] = (T)(dataArray[i]);
					data[1] = (T)(dataArray[j]);
					ret.add(data);
				}
			}
		}
		return ret;
	}

	public static <T> List<T[]> combinations3ordered(Collection<T> someData)
	{
		Object[] dataArray = someData.toArray();
		Class<T> clazz = (Class<T>) ((ParameterizedType)(dataArray.getClass()).getGenericSuperclass()).getActualTypeArguments()[0];
		int len = dataArray.length;
		ArrayList<T[]> ret = new ArrayList<T[]>();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				for (int k = 0; k < len; k++) {
					if ((i != j) && (j != k) && (i != k)) {
						T[] data = (T[])(java.lang.reflect.Array.newInstance(clazz, 3));
						data[0] = (T)(dataArray[i]);
						data[1] = (T)(dataArray[j]);
						data[2] = (T)(dataArray[k]);
						ret.add(data);
					}
				}
			}
		}
		return ret;
	}

}
