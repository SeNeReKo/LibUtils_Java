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
public class DynamicArrayList<T> extends DynamicArray implements Iterable<T>
{

	private class ArrayIterator<T> implements Iterator<T>
	{

		T[] elements;
		int max;
		int pos = 0;

		public ArrayIterator(T[] elements, int max)
		{
			this.elements = elements;
			this.max = max;
		}

		@Override
		public boolean hasNext()
		{
			return pos < max;
		}

		@Override
		public T next()
		{
			if (pos < max) {
				while (true) {
					T t = elements[pos];
					if (t == null) {
						pos++;
						if (pos >= max) return null;
						else continue;
					} else {
						pos++;
					}
					return t;
				}
			} else {
				return null;
			}
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

	}

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	public int count;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public DynamicArrayList(Class<T> c, int initialSize)
	{
		super(c, initialSize);
	}

	public DynamicArrayList(Class<T> c)
	{
		super(c);
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public Iterator<T> iterator()
	{
		return new ArrayIterator<T>((T[])data, count);
	}

}
