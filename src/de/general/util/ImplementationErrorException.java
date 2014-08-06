/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.util;


/**
 *
 * @author knauth
 */
public class ImplementationErrorException extends RuntimeException
{

	public ImplementationErrorException()
	{
	}

	public ImplementationErrorException(String message)
	{
		super(message);
	}

}
