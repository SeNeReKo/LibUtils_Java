/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.general.util;

/**
 *
 * @author knauth
 */
public class CharBuffer
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	char[] buffer;
	int count;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public CharBuffer()
	{
		buffer = new char[256];
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public int length()
	{
		return count;
	}

	public void clear()
	{
		count = 0;
	}

	private void __ensureCapacity(int requiredLength)
	{
		if (requiredLength > buffer.length) {
			int newLength = buffer.length * 2;
			while (newLength < requiredLength) newLength *= 2;
			char[] newBuffer = new char[newLength];
			System.arraycopy(buffer, 0, newBuffer, 0, count);
			buffer = newBuffer;
		}
	}

	public void append(char c)
	{
		__ensureCapacity(count + 1);
	}

	public void append(String s)
	{
		char[] newChars = s.toCharArray();

		__ensureCapacity(count + newChars.length);

		System.arraycopy(newChars, 0, buffer, count, newChars.length);

		count += newChars.length;
	}

	@Override
	public String toString()
	{
		return new String(buffer, 0, count);
	}

}
