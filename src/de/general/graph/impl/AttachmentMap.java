package de.general.graph.impl;


import java.util.*;


/**
 *
 * @author knauth
 */
public class AttachmentMap
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	HashMap<String, HashMap<Integer, Object>> map;
	int countTotal;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public AttachmentMap()
	{
		map = new HashMap<String, HashMap<Integer, Object>>();
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public void attach(String key, int id, Object obj)
	{
		HashMap<Integer, Object> m = map.get(key);
		if (m == null) {
			m = new HashMap<Integer, Object>();
			map.put(key, m);
		}
		if (m.get(id) == null) countTotal++;
		m.put(id, obj);
	}

	public void detach(String key, int id)
	{
		HashMap<Integer, Object> m = map.get(key);
		if (m == null) return;
		if (m.get(id) == null)
			countTotal--;
		else
			m.remove(id);
	}

	public Object get(String key, int id)
	{
		HashMap<Integer, Object> m = map.get(key);
		if (m == null) return null;
		return m.get(id);
	}

}
