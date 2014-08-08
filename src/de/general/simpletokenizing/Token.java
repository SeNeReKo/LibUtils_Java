package de.general.simpletokenizing;


import java.util.*;

import de.general.util.*;


/**
 *
 * @author knauth
 */
public class Token implements ITokenConstants
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	public final int GeneralType;
	public final String Text;
	public final int CharacterPosition;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public Token(int pos, String text, int generalType)
	{
		this.CharacterPosition = pos;
		this.Text = text;
		this.GeneralType = generalType;
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public boolean isSpace()
	{
		return GeneralType == ITokenConstants.Space;
	}

	public boolean isWord()
	{
		return GeneralType == ITokenConstants.Word;
	}

	public boolean isStringSingleQuotes()
	{
		return GeneralType == ITokenConstants.StringSQ;
	}

	public boolean isStringDoubleQuotes()
	{
		return GeneralType == ITokenConstants.StringDQ;
	}

	public boolean isDelimiter()
	{
		return GeneralType == ITokenConstants.Delimiter;
	}

	public boolean isEOS()
	{
		return GeneralType == ITokenConstants.EOS;
	}

	public int getType () {
		return GeneralType;
	}
	
	public String toString()
	{
		switch (GeneralType) {
			case ITokenConstants.Space:
				return "SPACE";
			case ITokenConstants.Delimiter:
				return "DELIM['" + Text + "']";
			case ITokenConstants.Word:
				return Text;
			case ITokenConstants.StringSQ:
				return "'" + Text.replace("\'", "\\\'") + "'";
			case ITokenConstants.StringDQ:
				return "\"" + Text.replace("\"", "\\\"") + "\"";
			case ITokenConstants.EOS:
				return "EOS";
			default:
				throw new NotImplementedException();
		}
	}

		////////////////////////////////////////////////////////////////

	public static Token createStringSQToken(int pos, String text)
	{
		return new Token(pos, text, ITokenConstants.StringSQ);
	}

	public static Token createStringDQToken(int pos, String text)
	{
		return new Token(pos, text, ITokenConstants.StringDQ);
	}

	public static Token createWordToken(int pos, String text)
	{
		return new Token(pos, text, ITokenConstants.Word);
	}

	public static Token createDelimiterToken(int pos, char delimiter)
	{
		return new Token(pos, "" + delimiter, ITokenConstants.Delimiter);
	}

	public static Token createSpaceToken(int pos)
	{
		return new Token(pos, "", ITokenConstants.Space);
	}

	public Token cloneObject()
	{
		Token t = new Token(CharacterPosition, Text, GeneralType);
		return t;
	}

}
