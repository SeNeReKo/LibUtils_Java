package de.general.util;


import java.util.*;
import java.lang.reflect.*;


/**
 *
 * @author knauth
 */
public class DynamicArray<T>
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	Class c;
	protected T[] data;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public DynamicArray(Class<T> c, int initialSize)
	{
		if (c == null) throw new RuntimeException("No class representing the element types specified!");
		if (initialSize <= 0) throw new RuntimeException("Invalid initial size specified: " + initialSize);

		this.c = c;
		data = (T[])(Array.newInstance(c, initialSize));
	}

	public DynamicArray(Class<T> c)
	{
		if (c == null) throw new RuntimeException("No class representing the element types specified!");

		this.c = c;
		data = (T[])(Array.newInstance(c, 1024));
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	/**
	 * Enlarge the array maintained by this instance.
	 *
	 * @param	requiredCapacity	The number of elements required.
	 */
	public T[] ensureCapacity(int requiredCapacity)
	{
		int n = data.length;
		while (requiredCapacity >= n) n = n * 2;
		if (n == requiredCapacity) return data;
		T[] newData = (T[])(Array.newInstance(c, n));
		System.arraycopy(data, 0, newData, 0, data.length);
		data = newData;
		return data;
	}

	public int capacity()
	{
		return data.length;
	}

	/**
	 * Access the data array in this array directly. Please note that
	 * a call to <code>ensureCapacity()</code> may create a new data array
	 * internally rendering the old one invalid.
	 */
	public T[] data()
	{
		return data;
	}

	public T get(int index)
	{
		return data[index];
	}

	public void set(int index, T value)
	{
		data[index] = value;
	}

}
