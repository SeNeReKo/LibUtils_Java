package de.general.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author knauth
 */
public class IniFile
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	private Pattern  _section  = Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
	private Pattern  _keyValue = Pattern.compile("\\s*([^=]*)=(.*)");
	private Map<String,Map<String,String>> _entries = new HashMap<String,Map<String,String>>();

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public IniFile(String path) throws IOException
	{
		load(path);
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public void load(String path) throws IOException
	{
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line;
			String section = null;
			while ((line = br.readLine()) != null) {
				Matcher m = _section.matcher(line);
				if (m.matches()) {
					section = m.group( 1 ).trim();
				} else
				if (section != null) {
					m = _keyValue.matcher(line);
					if (m.matches()) {
						String key   = m.group( 1 ).trim();
						String value = m.group( 2 ).trim();
						Map<String,String> kv = _entries.get(section);
						if (kv == null) {
							_entries.put(section, kv = new HashMap<String, String>());
						}
						kv.put(key, value);
					}
				}
			}
		} finally {
			if (br != null) {
				fr = null;
				try {
					br.close();
				} catch (Exception e) {
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public String[] getSections()
	{
		Set<String> keys = _entries.keySet();
		return keys.toArray(new String[keys.size()]);
	}

	public String[] getSectionKeys(String section)
	{
		Map<String, String> kv = _entries.get(section);
		if (kv == null) return null;

		Set<String> keys = kv.keySet();
		return keys.toArray(new String[keys.size()]);
	}

	public boolean containsKey(String section, String key)
	{
		Map<String, String> kv = _entries.get(section);
		if (kv == null) return false;

		Set<String> keys = kv.keySet();
		return keys.contains(key);
	}

	public String getString(String section, String key, String defaultvalue)
	{
		Map<String, String> kv = _entries.get(section);
		if (kv == null) {
			return defaultvalue;
		}
		return kv.get(key);
	}

	public int getInt(String section, String key, int defaultvalue)
	{
		Map<String, String> kv = _entries.get(section);
		if (kv == null) {
			return defaultvalue;
		}
		return Integer.parseInt(kv.get(key));
	}

	public float getFloat(String section, String key, float defaultvalue)
	{
		Map<String, String> kv = _entries.get(section);
		if (kv == null) {
			return defaultvalue;
		}
		return Float.parseFloat(kv.get(key));
	}

	public double getDouble(String section, String key, double defaultvalue)
	{
		Map<String, String> kv = _entries.get(section);
		if (kv == null) {
			return defaultvalue;
		}
		return Double.parseDouble(kv.get(key));
	}

}
