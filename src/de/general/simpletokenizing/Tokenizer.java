package de.general.simpletokenizing;


import java.util.*;

import de.general.util.*;

/**
 *
 * @author knauth
 */
public class Tokenizer
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	public static final int OriginalSpaces = 1;
	public static final int RemoveDuplicateSpaces = 2;
	public static final int SkipAllSpaces = 3;

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	boolean bLowerizeCharacters;
	int spaceProcessing;
	String extraAlphabetLetters;
	boolean bParseStrings;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 *
	 * @param	bLowerizeCharacters		Convert all characters to lower case using the standard <code>char.ToLower()</code> method
	 * @param	bRemoveDuplicateSpaces	Convert duplicate spaces to a single space character
	 * @param	bParseStrings			Recognize strings in the input data
	 * @param	extraAlphabetLetters	Extra alphabet letters to recognize more characters as letters than the default ones
	 */
	public Tokenizer(boolean bLowerizeCharacters, int spaceProcessing, boolean bParseStrings,
			String extraAlphabetLetters)
	{
		this.bParseStrings = bParseStrings;
		this.spaceProcessing = spaceProcessing;
		this.bLowerizeCharacters = bLowerizeCharacters;
		this.extraAlphabetLetters = (extraAlphabetLetters == null) ? "" : extraAlphabetLetters;
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	private void __createTokenFromBufferDontSkipIfEmpty(int tokenType, int posLastEmit, CharBuffer sb,
		ArrayList<Token> outTokenCollection)
	{
		switch (tokenType) {
			case ITokenConstants.StringSQ:
				outTokenCollection.add(Token.createStringSQToken(posLastEmit, sb.toString()));
				break;
			case ITokenConstants.StringDQ:
				outTokenCollection.add(Token.createStringDQToken(posLastEmit, sb.toString()));
				break;
			case ITokenConstants.Word:
				outTokenCollection.add(Token.createWordToken(posLastEmit, sb.toString()));
				break;
			default:
				throw new RuntimeException();
		}
		sb.clear();
	}

	private void __createTokenFromBufferSkipIfEmpty(int tokenType, int posLastEmit, CharBuffer sb,
		ArrayList<Token> outTokenCollection)
	{
		if (sb.length() == 0) return;

		switch (tokenType) {
			case ITokenConstants.StringSQ:
				outTokenCollection.add(Token.createStringSQToken(posLastEmit, sb.toString()));
				break;
			case ITokenConstants.StringDQ:
				outTokenCollection.add(Token.createStringDQToken(posLastEmit, sb.toString()));
				break;
			case ITokenConstants.Word:
				outTokenCollection.add(Token.createWordToken(posLastEmit, sb.toString()));
				break;
			default:
				throw new RuntimeException();
		}
		sb.clear();
	}

	public Token[] Tokenize(String input)
	{
		/*
		if (input.StartsWith("[a + paṭi°]")) {
			int xx = 0;
		}
		*/

		ArrayList<Token> tokens = new ArrayList<Token>();
		CharBuffer sb = new CharBuffer();

		boolean bInSingleQuotes = false;
		boolean bInDoubleQuotes = false;
		boolean bMasked = false;

		int pos = 0;
		int posLastBegin = 1;
		for (char c2 : input.toCharArray()) {
			pos++;
			char c = bLowerizeCharacters ? Character.toLowerCase(c2) : c2;

			// ----

			if (bMasked) {
				sb.append(c);
				bMasked = false;
				continue;
			}

			if (bInSingleQuotes) {
				if (c == '\'') {
					__createTokenFromBufferDontSkipIfEmpty(ITokenConstants.StringSQ, posLastBegin, sb, tokens);
					bInSingleQuotes = false;
				} else
				if (c == '\\') {
					if (sb.length() == 0) posLastBegin = pos;
					bMasked = true;
				} else {
					if (sb.length() == 0) posLastBegin = pos;
					sb.append(c);
				}
				continue;
			}

			if (bInDoubleQuotes) {
				if (c == '\"') {
					__createTokenFromBufferDontSkipIfEmpty(ITokenConstants.StringDQ, posLastBegin, sb, tokens);
					bInDoubleQuotes = false;
				} else
				if (c == '\\') {
					if (sb.length() == 0) posLastBegin = pos;
					bMasked = true;
				} else {
					if (sb.length() == 0) posLastBegin = pos;
					sb.append(c);
				}
				continue;
			}

			// ----

			if (bParseStrings) {
				if (c == '\'') {
					__createTokenFromBufferSkipIfEmpty(ITokenConstants.Word, posLastBegin, sb, tokens);
					bInSingleQuotes = true;
					continue;
				} else
				if (c == '\"') {
					__createTokenFromBufferSkipIfEmpty(ITokenConstants.Word, posLastBegin, sb, tokens);
					bInDoubleQuotes = true;
					continue;
				}
			}

			// ----

			if (Character.isWhitespace(c) || (c == (char)13) || (c == (char)10)) {
				__createTokenFromBufferSkipIfEmpty(ITokenConstants.Word, posLastBegin, sb, tokens);

				if (spaceProcessing == OriginalSpaces) {
					tokens.add(Token.createSpaceToken(pos));
				} else
				if (spaceProcessing == RemoveDuplicateSpaces) {
					if ((tokens.size() > 0) && tokens.get(tokens.size() - 1).isSpace()) continue;
					tokens.add(Token.createSpaceToken(pos));
				} else
				if (spaceProcessing == SkipAllSpaces) {
				} else {
					throw new ImplementationErrorException();
				}

				continue;
			}

			// ----

			if (Character.isLetterOrDigit(c) || (extraAlphabetLetters.indexOf(Character.toLowerCase(c)) >= 0)) {
				if (sb.length() == 0) posLastBegin = pos;
				sb.append(c);
				continue;
			}

			// ----

			__createTokenFromBufferSkipIfEmpty(ITokenConstants.Word, posLastBegin, sb, tokens);

			tokens.add(Token.createDelimiterToken(pos, c));
		}

		if (bMasked) throw new RuntimeException("Syntax error at: " + pos);
		if (bInSingleQuotes) throw new RuntimeException("Syntax error at: " + pos);
		if (bInDoubleQuotes) throw new RuntimeException("Syntax error at: " + pos);

		__createTokenFromBufferSkipIfEmpty(ITokenConstants.Word, posLastBegin, sb, tokens);

		tokens.add(new Token(pos, "", ITokenConstants.EOS));

		return tokens.toArray(new Token[tokens.size()]);
	}

}
