package de.general.io;


import java.util.*;
import java.io.*;

import de.general.util.*;


/**
 *
 * @author knauth
 */
public class BinaryDeserializer implements IDeserializer
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	InputStream ins;
	DataInputStream dis;
	StringFactory factory;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public BinaryDeserializer(StringFactory factory, InputStream ins)
	{
		this.factory = factory;
		this.ins = ins;
		dis = new DataInputStream(ins);
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public IDeserializer readNested() throws Exception
	{
		byte[] data = readBinary();
		if (data == null) throw new Exception("No data!");
		return new BinaryDeserializer(factory, new ByteArrayInputStream(data));
	}

	@Override
	public byte[] readBinary() throws Exception
	{
		int len = dis.readInt();
		if (len < 0) return null;
		byte[] dataBytes = new byte[len];
		int pos = 0;
		while (true) {
			int bytesRead = dis.read(dataBytes, pos, len);
			if (bytesRead < 0) {
				throw new IOException("I/O Error");
			}
			pos += bytesRead;
			len -= bytesRead;
			if (len == 0) return dataBytes;
		}
	}

	@Override
	public byte[] readBinaryFixed(int len) throws Exception
	{
		byte[] dataBytes = new byte[len];
		int pos = 0;
		while (true) {
			int bytesRead = dis.read(dataBytes, pos, len);
			if (bytesRead < 0) {
				throw new IOException("I/O Error");
			}
			pos += bytesRead;
			len -= bytesRead;
			if (len == 0) return dataBytes;
		}
	}

	@Override
	public String readASCIIFixed(int len) throws Exception
	{
		String s = new String(readBinaryFixed(len), "ASCII");
		if (factory != null) s = factory.get(s);
		return s;
	}

	@Override
	public String readUTF8() throws Exception
	{
		int n = dis.readInt();
		if (n < 0) return null;
		String s = new String(readBinaryFixed(n), "UTF-8");
		if (factory != null) s = factory.get(s);
		return s;
	}

	@Override
	public int readInt32() throws Exception
	{
		return dis.readInt();
	}

	@Override
	public void close() throws Exception
	{
		dis.close();
	}

	@Override
	public String[] readUTF8Array() throws Exception
	{
		int n = dis.readInt();
		if (n < 0) return null;
		String[] ret = new String[n];
		if (factory != null) {
			for (int i = 0; i < n; i++) {
				int wlen = dis.readInt();
				if (wlen >= 0) {
					String s = new String(readBinaryFixed(n), "UTF-8");
					s = factory.get(s);
					ret[i] = s;
				}
			}
		} else {
			for (int i = 0; i < n; i++) {
				int wlen = dis.readInt();
				if (wlen >= 0) {
					ret[i] = new String(readBinaryFixed(n), "UTF-8");
				}
			}
		}
		return ret;
	}

	@Override
	public HashMap<String, String> readStringHashMap() throws Exception
	{
		HashMap<String, String> ret = new HashMap<String, String>();
		int count = dis.readInt();
		for (int i = 0; i < count; i++) {
			String key = readUTF8();
			String value = readUTF8();
			if ((key == null) || (value == null)) throw new Exception("Data format error!");
			ret.put(key, value);
		}
		return ret;
	}

}
