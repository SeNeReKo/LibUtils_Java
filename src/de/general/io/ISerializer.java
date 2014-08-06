/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.io;


import java.util.*;


/**
 *
 * @author knauth
 */
public interface ISerializer
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

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public void writeASCIIFixed(String value) throws Exception;

	public void writeUTF8(String value) throws Exception;

	public void writeUTF8Array(String[] value) throws Exception;

	public void writeInt32(int value) throws Exception;

	public void writeBinary(byte[] data) throws Exception;

	public ISerializer beginNested() throws Exception;

	public void writeStringHashMap(HashMap<String, String> map) throws Exception;

	public ISerializer close() throws Exception;

}
