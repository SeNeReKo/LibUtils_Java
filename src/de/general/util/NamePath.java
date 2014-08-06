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
public class NamePath
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	ArrayList<String> list;
	boolean bReadOnly;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public NamePath()
	{
		list = new ArrayList<String>();
	}

	public NamePath(NamePath np, boolean bReadOnly)
	{
		list = new ArrayList<String>(np.list);
		this.bReadOnly = bReadOnly;
	}

	public NamePath(NamePath np, String element, boolean bReadOnly)
	{
		list = new ArrayList<String>(np.list);

		if ((element.trim().length() != element.length()) || (element.length() == 0)) throw new RuntimeException("Invalid element : \"" + element + "\"");

		list.add(element);
		this.bReadOnly = bReadOnly;
	}

	public NamePath(NamePath np)
	{
		list = new ArrayList<String>(np.list);
		bReadOnly = np.bReadOnly;
	}

	public NamePath(String ... elements)
	{
		list = new ArrayList<String>();
		if (elements != null) {

			int i = 0;
			for (String s : elements) {
				if ((s.trim().length() != s.length()) || (s.length() == 0)) throw new RuntimeException("Invalid element at index " + i + ": \"" + s + "\"");
				i++;
			}

			list.addAll(Arrays.asList(elements));
		}
	}

	public NamePath(boolean bReadOnly, String ... elements)
	{
		this.bReadOnly = bReadOnly;
		list = new ArrayList<String>();
		if (elements != null) {

			int i = 0;
			for (String s : elements) {
				if ((s.trim().length() != s.length()) || (s.length() == 0)) throw new RuntimeException("Invalid element at index " + i + ": \"" + s + "\"");
				i++;
			}

			list.addAll(Arrays.asList(elements));
		}
	}

	public NamePath(Collection<String> elements)
	{
		if (elements != null) {
			int i = 0;
			for (String s : elements) {
				if ((s.trim().length() != s.length()) || (s.length() == 0)) throw new RuntimeException("Invalid element at index " + i + ": \"" + s + "\"");
				i++;
			}

			list = new ArrayList<String>(elements);
		} else {
			list = new ArrayList<String>();
		}
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public NamePath getParent()
	{
		NamePath np = new NamePath();
		np.bReadOnly = bReadOnly;
		for (int i = 0; i < list.size() - 1; i++) {
			np.list.add(list.get(i));
		}
		return np;
	}

	public NamePath derive(String additionalNameElement)
	{
		NamePath np = new NamePath(list);
		np.push(additionalNameElement);
		np.bReadOnly = bReadOnly;
		return np;
	}

	public void push(String s)
	{
		if (bReadOnly) throw new RuntimeException("List is readonly!");
		list.add(s);
	}

	public String pop()
	{
		if (bReadOnly) throw new RuntimeException("List is readonly!");
		if (list.isEmpty()) return null;
		String s = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		return s;
	}

	public String peek()
	{
		if (list.isEmpty()) return null;
		String s = list.get(list.size() - 1);
		return s;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {
			if (i > 0) sb.append(" | ");
			sb.append(list.get(i));
		}

		return sb.toString();
	}

	public String get(int index)
	{
		return list.get(index);
	}

	public int size()
	{
		return list.size();
	}

	public NamePath subPath(int ofs)
	{
		return subPath(ofs, list.size() - ofs);
	}

	public NamePath subPath(int ofs, int len)
	{
		NamePath np = new NamePath();
		for (int i = ofs; i < ofs + len; i++) {
			np.list.add(list.get(i));
		}
		np.bReadOnly = true;
		return np;
	}

	public String toString(String delimiter)
	{
		if (delimiter == null) throw new RuntimeException("No delimiter specified!");

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {
			if (i > 0) sb.append(delimiter);
			sb.append(list.get(i));
		}

		return sb.toString();
	}

	public String toStringExceptLast(String delimiter)
	{
		if (delimiter == null) throw new RuntimeException("No delimiter specified!");

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size() - 1; i++) {
			if (i > 0) sb.append(delimiter);
			sb.append(list.get(i));
		}

		return sb.toString();
	}

	public String[] toStringListExceptLast()
	{
		String[] ret = new String[list.size() - 1];

		for (int i = 0; i < list.size() - 1; i++) {
			ret[i] = list.get(i);
		}

		return ret;
	}

	public String toString(char delimiter)
	{
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {
			if (i > 0) sb.append(delimiter);
			sb.append(list.get(i));
		}

		return sb.toString();
	}

	public String toStringExceptLast(char delimiter)
	{
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size() - 1; i++) {
			if (i > 0) sb.append(delimiter);
			sb.append(list.get(i));
		}

		return sb.toString();
	}

	public String[] toArray()
	{
		return list.toArray(new String[list.size()]);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		if (!(obj instanceof NamePath)) return false;
		NamePath np = (NamePath)obj;

		if (np.list.size() != list.size()) return false;
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).equals(np.list.get(i))) return false;
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		return toString().hashCode();
	}

	public boolean equals(String[] pathElements)
	{
		if (pathElements == null) return false;

		if (pathElements.length != list.size()) return false;
		for (int i = 0; i < pathElements.length; i++) {
			if (!list.get(i).equals(pathElements[i])) return false;
		}
		return true;
	}

	public boolean startsWith(NamePath np)
	{
		if (np == null) return false;

		if (list.size() < np.list.size()) return false;
		for (int i = 0; i < np.list.size(); i++) {
			if (!list.get(i).equals(np.list.get(i))) return false;
		}
		return true;
	}

	public boolean startsWith(String[] pathElements)
	{
		if (pathElements == null) return false;

		if (list.size() < pathElements.length) return false;
		for (int i = 0; i < pathElements.length; i++) {
			if (!list.get(i).equals(pathElements[i])) return false;
		}
		return true;
	}

	public void setReadOnly()
	{
		bReadOnly = true;
	}

	public boolean isReadOnly()
	{
		return bReadOnly;
	}

	public static NamePath parse(String pathElementText, char delimiter, boolean bReadOnly)
	{
		ArrayList<String> pathElements = new ArrayList<String>();
		int pos = 0;
		for (int i = 0; i < pathElementText.length(); i++) {
			if (pathElementText.charAt(i) == delimiter) {
				String s = pathElementText.substring(0, i).substring(pos);
				pathElements.add(s);
				pos = i + 1;
			}
		}
		if (pos != pathElementText.length()) {
			String s = pathElementText.substring(pos);
			pathElements.add(s);
		}

		return new NamePath(bReadOnly, pathElements.toArray(new String[0]));
	}

}
