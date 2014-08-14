package de.general.graph.impl;


import de.general.graph.*;


/**
 *
 * @author knauth
 */
public class ArrayHelper
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

	/**
	 * Constructor.
	 */
	private ArrayHelper()
	{
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public static int[] resizeArray(int[] array, int count, int requiredCapacity)
	{
		if (requiredCapacity > array.length) {
			int newCapacity = array.length * 2;
			while (newCapacity < requiredCapacity) {
				newCapacity *= 2;
			}
			int[] newArray = new int[newCapacity];
			System.arraycopy(array, 0, newArray, 0, count);
			for (int i = count; i < newCapacity; i++) {
				newArray[i] = -1;
			}
			return newArray;
		} else {
			return array;
		}
	}

	public static String[] resizeArray(String[] array, int count, int requiredCapacity)
	{
		if (requiredCapacity > array.length) {
			int newCapacity = array.length * 2;
			while (newCapacity < requiredCapacity) {
				newCapacity *= 2;
			}
			String[] newArray = new String[newCapacity];
			System.arraycopy(array, 0, newArray, 0, count);
			return newArray;
		} else {
			return array;
		}
	}

	public static Edge[] resizeArray(Edge[] array, int count, int requiredCapacity)
	{
		if (requiredCapacity > array.length) {
			int newCapacity = array.length * 2;
			while (newCapacity < requiredCapacity) {
				newCapacity *= 2;
			}
			Edge[] newArray = new Edge[newCapacity];
			System.arraycopy(array, 0, newArray, 0, count);
			return newArray;
		} else {
			return array;
		}
	}

}
