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
public interface IDeserializer
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

	public IDeserializer readNested() throws Exception;

	public byte[] readBinary() throws Exception;

	public byte[] readBinaryFixed(int len) throws Exception;

	public String readASCIIFixed(int len) throws Exception;

	public String readUTF8() throws Exception;

	public String[] readUTF8Array() throws Exception;

	public int readInt32() throws Exception;

	public HashMap<String, String> readStringHashMap() throws Exception;

	public void close() throws Exception;

}
