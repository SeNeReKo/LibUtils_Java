package de.general.simpletokenizing;

import java.util.ArrayList;
import java.util.List;

/**
 * Validates a list of tokens against a type structure
 * @author David
 *
 */
public class TokenValidator {
	
	/////////////////////////////////////////////////
	// Variables
	/////////////////////////////////////////////////
	
	/**
	 * Short notation for type list
	 * @author David
	 *
	 */
	public enum ShortType {
		/**
		 * Short notation
		 */
		CDC(ITokenConstants.StringDQ, ITokenConstants.Delimiter, ITokenConstants.StringDQ);
		
		/**
		 * Type list
		 */
		private List<Integer> typelist;
		
		/**
		 * Constructor
		 * @param types types
		 */
		private ShortType(Integer...types) {
			typelist = new ArrayList<Integer>();
			for (int t : types) {
				typelist.add(t);
			}
		}
		
		/**
		 * Returns this short notation type list's expanded type list
		 * @return expanded type list
		 */
		public List<Integer> getTypelist () {
			return typelist;
		}
	}
	
	/////////////////////////////////////////////////
	// Constructors
	/////////////////////////////////////////////////
	
	/////////////////////////////////////////////////
	// Methods
	/////////////////////////////////////////////////
	
	/**
	 * Checks whether a given list of tokens has a given Type structure
	 * @param st short notation for type list
	 * @param list token list
	 * @return <b>true</b> if token list has given type list structure, <b>false</b> otherwise
	 */
	public static boolean is (ShortType st, List<Token> list) {
		return is(st.getTypelist(), list);
	}
	
	/**
	 * Checks whether a given list of tokens has a given Type structure
	 * @param typelist type list
	 * @param tokenlist token list
	 * @return <b>true</b> if token list has given type list structure, <b>false</b> otherwise
	 */
	public static boolean is (List<Integer> typelist, List<Token> tokenlist) {
		if (typelist.size() != tokenlist.size()) return false;
		for (int i = 0; i < tokenlist.size(); i++) {
			if (typelist.get(i) != tokenlist.get(i).getType()) return false;
		}
		return true;
	}
}
