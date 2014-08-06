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
public class XStack<T>
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	final ArrayList<T> stack;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public XStack()
	{
		stack = new ArrayList<T>();
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public void push(T t)
	{
		stack.add(t);
	}

	public T pop()
	{
		if (stack.size() == 0) return null;
		T t = stack.get(stack.size() - 1);
		stack.remove(stack.size() - 1);
		return t;
	}

	public T peek()
	{
		if (stack.size() == 0) return null;
		T t = stack.get(stack.size() - 1);
		return t;
	}

}