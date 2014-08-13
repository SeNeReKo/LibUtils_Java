package de.general.transliteration;


import java.util.*;


/**
 *
 * @author knauth
 */
public class TransliterationManager
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	private HashMap<String, ITransliterator> map;
	private HashSet<String> codings;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public TransliterationManager()
	{
		map = new HashMap<String, ITransliterator>();
		codings = new HashSet<String>();
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public String[] getAvailableTransliterators()
	{
		String[] data = (new ArrayList<String>(codings)).toArray(new String[codings.size()]);
		Arrays.sort(data);
		return data;
	}

	protected void register(ITransliterator transliterator)
	{
		String key = transliterator.fromSchema() + "~" + transliterator.toSchema();
		map.remove(key);
		map.put(key, transliterator);

		key = transliterator.fromSchema() + " -> " + transliterator.toSchema();
		codings.remove(key);
		codings.add(key);
	}

	/**
	 * Try to obtain a stored transliterator.
	 *
	 * @return	This method returns <code>null</code> if no transliterator is found for the specified schemas.
	 */
	public ITransliterator get(String fromSchema, String toSchema)
	{
		String key = fromSchema + "~" + toSchema;
		return map.get(key);
	}

}
