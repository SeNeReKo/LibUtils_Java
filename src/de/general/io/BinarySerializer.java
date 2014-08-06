package de.general.io;


import java.util.*;
import java.io.*;

import de.general.util.*;


/**
 *
 * @author knauth
 */
public class BinarySerializer implements ISerializer
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	OutputStream os;
	DataOutputStream dos;
	BinarySerializer parent;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public BinarySerializer(BinarySerializer parent)
	{
		this.os = new ByteArrayOutputStream();
		dos = new DataOutputStream(os);
		this.parent = parent;
	}

	public BinarySerializer(OutputStream os)
	{
		this.os = os;
		dos = new DataOutputStream(os);
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public void writeASCIIFixed(String value) throws Exception
	{
		byte[] asciiBytes = value.getBytes("ASCII");
		dos.write(asciiBytes, 0, asciiBytes.length);
	}

	@Override
	public void writeUTF8(String value) throws Exception
	{
		if (value == null) {
			dos.writeInt(-1);
			return;
		}
		byte[] utf8Bytes = value.getBytes("UTF8");
		dos.writeInt(utf8Bytes.length);
		dos.write(utf8Bytes, 0, utf8Bytes.length);
	}

	@Override
	public void writeInt32(int value) throws Exception
	{
		dos.writeInt(value);
	}

	@Override
	public void writeBinary(byte[] data) throws Exception
	{
		dos.writeInt(data.length);
		dos.write(data, 0, data.length);
	}

	@Override
	public ISerializer close() throws Exception
	{
		dos.flush();
		dos.close();
		if (parent != null) {
			ByteArrayOutputStream baos = (ByteArrayOutputStream)os;
			byte[] data = baos.toByteArray();
			baos = null;
			dos = null;
			os = null;
			parent.writeBinary(data);
		}
		return parent;
	}

	@Override
	public ISerializer beginNested() throws Exception
	{
		return new BinarySerializer(this);
	}

	@Override
	public void writeUTF8Array(String[] value) throws Exception
	{
		if (value == null) {
			dos.writeInt(-1);
			return;
		}
		dos.writeInt(value.length);

		for (int i = 0; i < value.length; i++) {
			if (value[i] == null) {
				dos.writeInt(-1);
			} else {
				byte[] utf8Bytes = value[i].getBytes("UTF8");
				dos.writeInt(utf8Bytes.length);
				dos.write(utf8Bytes, 0, utf8Bytes.length);
			}
		}
	}

	@Override
	public void writeStringHashMap(HashMap<String, String> map) throws Exception
	{
		DynamicArray<String> keys = new DynamicArray<String>(String.class, 16);
		DynamicArray<String> values = new DynamicArray<String>(String.class, 16);

		int count = 0;
		for (String key : map.keySet()) {
			if (key == null) continue;
			String value = map.get(key);
			if (value == null) continue;

			keys.ensureCapacity(count + 1);
			values.ensureCapacity(count + 1);
			keys.set(count, key);
			values.set(count, value);
			count++;
		}

		dos.writeInt(count);
		for (int i = 0; i < count; i++) {
			writeUTF8(keys.get(i));
			writeUTF8(values.get(i));
		}
	}

}
