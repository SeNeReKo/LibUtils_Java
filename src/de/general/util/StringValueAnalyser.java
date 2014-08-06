/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.util;


import java.util.*;



/**
 *
 * @author knauth
 */
public class StringValueAnalyser
{

	private class Value
	{
		public int value;
	}

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	HashMap<String, Value> map;
	int total;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public StringValueAnalyser()
	{
		map = new HashMap<String, Value>();
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public Set<String> keys()
	{
		return map.keySet();
	}

	public int getValue(String key)
	{
		Value v = map.get(key);
		if (v == null) return 0;
		return v.value;
	}

	public String analyseValueTypes()
	{
		boolean bIsBoolean = true;

		for (String key : map.keySet()) {
			String s = key.toLowerCase();
			if (!s.equals("true") && !s.equals("false") && !s.equals("0") && !s.equals("1")) {
				bIsBoolean = false;
				break;
			}
		}
		if (bIsBoolean) {
			return "Boolean";
		}

		boolean bIsInteger = true;
		int intMin = Integer.MAX_VALUE;
		int intMax = Integer.MIN_VALUE;

		for (String key : map.keySet()) {
			try {
				int v = Integer.parseInt(key);
				if (v > intMax) intMax = v;
				if (v < intMin) intMin = v;
			} catch (Exception ee) {
				bIsInteger = false;
				break;
			}
		}
		if (bIsInteger) {
			return "Integer[" + intMin + ".." + intMax + "]";
		}

		boolean bIsLong = true;
		long longMin = Long.MAX_VALUE;
		long longMax = Long.MIN_VALUE;

		for (String key : map.keySet()) {
			try {
				long v = Long.parseLong(key);
				if (v > longMax) longMax = v;
				if (v < longMin) longMin = v;
			} catch (Exception ee) {
				bIsLong = false;
				break;
			}
		}
		if (bIsLong) {
			return "Long[" + longMin + ".." + longMax + "]";
		}

		boolean bIsDouble = true;
		double doubleMin = Double.MAX_VALUE;
		double doubleMax = Double.MIN_VALUE;

		for (String key : map.keySet()) {
			try {
				double v = Double.parseDouble(key);
				if (v > doubleMax) doubleMax = v;
				if (v < doubleMin) doubleMin = v;
			} catch (Exception ee) {
				bIsDouble = false;
				break;
			}
		}
		if (bIsDouble) {
			return "Double[" + doubleMin + ".." + doubleMax + "]";
		}

		return "String";
	}

	public void increment(String s)
	{
		total++;

		Value v = map.get(s);
		if (v == null) {
			v = new Value();
			map.put(s, v);
		}
		v.value++;
	}

	public boolean hasOnlyDifferentValues()
	{
		return total == map.size();
	}

	public int size()
	{
		return map.size();
	}

	@Override
	public String toString()
	{
		if (map.size() > 200) {

			return map.size() + " different kinds of " + analyseValueTypes()
				+ (hasOnlyDifferentValues() ? " (all are different)" : "");

		} else {

			StringBuilder sb = new StringBuilder();

			ArrayList<String> keyList = new ArrayList<String>(map.keySet());
			String[] keys = keyList.toArray(new String[keyList.size()]);
			Arrays.sort(keys);

			boolean bNotFirstKey = false;
			for (String key : keys) {
				if (bNotFirstKey) sb.append(",  ");

				Value v = map.get(key);
				sb.append(v.value + " x \"" + key + "\"");

				bNotFirstKey = true;
			}

			return map.size() + " different kinds of " + analyseValueTypes()
				+ (hasOnlyDifferentValues() ? " (all are different)" : "")
				+ ",  " + sb.toString();

		}
	}

	public String toMultiLines(String prefix, int maxCount)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		sb.append(map.size() + " different kinds of " + analyseValueTypes());
		if (hasOnlyDifferentValues()) sb.append(" (all are different)");
		sb.append(TextUtils.CRLF);

		// get all keys

		ArrayList<String> keyList = new ArrayList<String>(map.keySet());
		String[] keys = keyList.toArray(new String[keyList.size()]);
		Arrays.sort(keys);

		int n = 0;
		for (String key : keys) {
			sb.append(prefix);
			sb.append('\t');
			if (n >= maxCount) {
				sb.append("...");
				sb.append(TextUtils.CRLF);
				break;
			} else {
				Value v = map.get(key);
				sb.append(v.value + " x \"" + key + "\"");
				sb.append(TextUtils.CRLF);
			}
			n++;
		}

		return sb.toString();
	}

}
