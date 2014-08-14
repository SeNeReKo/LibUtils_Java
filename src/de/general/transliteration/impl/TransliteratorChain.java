package de.general.transliteration.impl;


import de.general.transliteration.*;


/**
 *
 * @author knauth
 */
public class TransliteratorChain implements ITransliterator
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	ITransliterator[] transliterators;
	String fromSchema;
	String toSchema;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public TransliteratorChain(ITransliterator ... transliterators)
	{
		this.transliterators = transliterators;
		fromSchema = transliterators[0].fromSchema();
		toSchema = transliterators[transliterators.length - 1].toSchema();
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public String fromSchema()
	{
		return fromSchema;
	}

	@Override
	public String toSchema()
	{
		return toSchema;
	}

	@Override
	public String transliterate(String text)
	{
		String s = text;
		for (int i = 0; i < transliterators.length; i++) {
			s = transliterators[i].transliterate(s);
		}
		return s;
	}

}
