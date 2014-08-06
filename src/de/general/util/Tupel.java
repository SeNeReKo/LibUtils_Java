/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.util;


/**
 *
 * @author knauth
 */
public class Tupel<K,V>
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	public K k;
	public V v;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public Tupel(K k, V v)
	{
		this.k = k;
		this.v = v;
	}

	public Tupel()
	{
		this.k = null;
		this.v = null;
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Tupel) {
			Tupel t = (Tupel)obj;
			if (!XUtils.equals(this.k, t.k)) return false;
			if (!XUtils.equals(this.v, t.v)) return false;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		int h = 17;
		if (k != null) h = h * 32 + k.hashCode();
		if (v != null) h = h * 32 + v.hashCode();
		return h;
	}

	@Override
	public String toString()
	{
		return k + " -> " + v;
	}

}
