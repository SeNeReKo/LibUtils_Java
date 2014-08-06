/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.util;

import java.io.*;
import java.util.*;

/**
 *
 * @author knauth
 */
public class TextUtils
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	public static final String CRLF = "" + (char)13 + (char)10;

	private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

    static
    {
    }

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public static String frontPadWithZeros(int n, int maxLen)
	{
		String s = "" + n;
		while (s.length() < maxLen) {
			s = "0" + s;
		}
		return s;
	}

	public static String[] normalize(String[] slist)
	{
		if (slist == null) return null;
		String[] ret = new String[slist.length];
		for (int i = 0; i < slist.length; i++) {
			ret[i] = normalize(slist[i]);
		}
		return ret;
	}

	public static String normalize(String s)
	{
		if (s == null) return null;
		s = s.trim();
		if (s.length() == 0) return null;
		return s;
	}

	public static HashMap<String, String> kvpsToMap(String[] keyValuePairs)
	{
		if (keyValuePairs == null) return null;
		if ((keyValuePairs.length % 2) != 0) throw new RuntimeException("Invalid list specified: "
			+ "Key-value pairs must have an even list size!");

		HashMap<String, String> ret = new HashMap<String, String>();
		for (int i = 0; i < keyValuePairs.length; i += 2) {
			String key = normalize(keyValuePairs[i]);
			String value = normalize(keyValuePairs[i + 1]);
			if (key == null) throw new RuntimeException("Invalid key!");
			if (value != null) ret.put(key, value);
		}
		return ret;
	}

	public static void printList(Map.Entry[] elements, String prefix, int numberOfElementsPerLine, PrintStream pw)
	{
		int i = 0;
		boolean bComma = false;
		while (i < elements.length) {
			String s = elements[i].getKey() + "(" + elements[i].getValue().toString() + ")";
			if (bComma) pw.print(", ");
			else pw.print(prefix);
			pw.print(s);
			i++;
			if ((i % numberOfElementsPerLine) == 0) {
				bComma = false;
				pw.println();
			} else {
				bComma = true;
			}
		}
		if (bComma) pw.println();
	}

	public static void printList(String[] elements, String prefix, int numberOfElementsPerLine, PrintStream pw)
	{
		int i = 0;
		boolean bComma = false;
		while (i < elements.length) {
			String s = elements[i];
			if (bComma) pw.print(", ");
			else pw.print(prefix);
			pw.print(s);
			i++;
			if ((i % numberOfElementsPerLine) == 0) {
				bComma = false;
				pw.println();
			} else {
				bComma = true;
			}
		}
		if (bComma) pw.println();
	}

	public static void printList(Map.Entry[] elements, String prefix, int numberOfElementsPerLine, PrintWriter pw)
	{
		int i = 0;
		boolean bComma = false;
		while (i < elements.length) {
			String s = elements[i].getKey() + "(" + elements[i].getValue().toString() + ")";
			if (bComma) pw.print(", ");
			else pw.print(prefix);
			pw.print(s);
			i++;
			if ((i % numberOfElementsPerLine) == 0) {
				bComma = false;
				pw.println();
			} else {
				bComma = true;
			}
		}
		if (bComma) pw.println();
	}

	public static void printList(String[] elements, String prefix, int numberOfElementsPerLine, PrintWriter pw)
	{
		int i = 0;
		boolean bComma = false;
		while (i < elements.length) {
			String s = elements[i];
			if (bComma) pw.print(", ");
			else pw.print(prefix);
			pw.print(s);
			i++;
			if ((i % numberOfElementsPerLine) == 0) {
				bComma = false;
				pw.println();
			} else {
				bComma = true;
			}
		}
		if (bComma) pw.println();
	}

	/**
	 * Check if this word only consists of letters. Only the first letter may be upper case!
	 *
	 * @param s	The word to check
	 * @return Returns <code>true</code> on success.
	 */
	public static boolean isRegularWord(String s)
	{
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isLetter(s.charAt(i))) return false;
		}
		for (int i = 1; i < s.length(); i++) {
			if (!Character.isLowerCase(s.charAt(i))) return false;
		}
		return true;
	}

	public static boolean containsOnlyLetters(String s)
	{
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isLetter(s.charAt(i))) return false;
		}
		return true;
	}

	public static boolean containsOnlyDigits(String s)
	{
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) return false;
		}
		return true;
	}

	public static String encodeCounterWithLetters(int counter)
	{
		int n = counter % ALPHABET.length;
		StringBuilder sb = new StringBuilder("" + ALPHABET[n]);
		counter /= ALPHABET.length;
		while (counter > 0) {
			n = counter % ALPHABET.length;
			counter /= ALPHABET.length;
			sb.insert(0, ALPHABET[n]);
		}
		return sb.toString();
	}

	public static String encodeCounterWithLetters(int counter, int minLength)
	{
		int n = counter % ALPHABET.length;
		StringBuilder sb = new StringBuilder("" + ALPHABET[n]);
		counter /= ALPHABET.length;
		while (counter > 0) {
			n = counter % ALPHABET.length;
			counter /= ALPHABET.length;
			sb.insert(0, ALPHABET[n]);
		}
		while (sb.length() < minLength) {
			sb.insert(0, ALPHABET[0]);
		}
		return sb.toString();
	}

}
