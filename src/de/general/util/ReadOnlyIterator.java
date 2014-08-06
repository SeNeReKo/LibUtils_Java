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
public class ReadOnlyIterator<T> implements Iterator<T>
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	Iterator<T> iterator;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public ReadOnlyIterator(Iterator<T> iterator)
	{
		this.iterator = iterator;
	}

	public ReadOnlyIterator(Collection<T> list)
	{
		this.iterator = (new ArrayList<T>(list)).iterator();
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public boolean hasNext()
	{
		return iterator.hasNext();
	}

	@Override
	public T next()
	{
		return iterator.next();
	}

	@Override
	public void remove()
	{
		throw new UnsupportedOperationException("Not supported!");
	}

}
