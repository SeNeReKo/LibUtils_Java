/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.io;


import java.util.*;
import java.io.*;


/**
 *
 * @author knauth
 */
public class FileProvider implements Enumeration<File>
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	ArrayList<File> files;
	int pos;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	public FileProvider(File[] files)
	{
		this.files = new ArrayList<File>();
		addAll(files);
	}

	public FileProvider()
	{
		this.files = new ArrayList<File>();
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	protected boolean addAll(List<File> files)
	{
		if (files != null) {
			for (File f : files) {
				this.files.add(f);
			}
			return true;
		} else {
			return false;
		}
	}

	protected boolean addAll(File[] files)
	{
		if (files != null) {
			for (File f : files) {
				this.files.add(f);
			}
			return true;
		} else {
			return false;
		}
	}

	protected boolean add(File file)
	{
		if (file != null) {
			this.files.add(file);
			return true;
		} else {
			return false;
		}
	}

    /**
     * Tests if this enumeration contains more elements.
     *
     * @return  <code>true</code> if and only if this enumeration object
     *           contains at least one more element to provide;
     *          <code>false</code> otherwise.
     */
    public boolean hasMoreElements()
	{
		return pos < files.size();
	}

    /**
     * Returns the next element of this enumeration if this enumeration
     * object has at least one more element to provide.
     *
     * @return     the next element of this enumeration.
     * @exception  NoSuchElementException  if no more elements exist.
     */
    public File nextElement()
	{
		if (pos < files.size()) {
			File f = files.get(pos);
			pos++;
			return f;
		} else {
			throw new NoSuchElementException();
		}
	}

}
