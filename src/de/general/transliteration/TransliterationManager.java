package de.general.transliteration;


import java.util.*;

import de.general.graph.*;
import de.general.transliteration.impl.TransliteratorChain;


/**
 *
 * @author knauth
 */
public class TransliterationManager
{

	private class Visitor implements IEdgePathIterationDelegate
	{
		public final HashMap<String, EdgePath> edgePaths;

		public Visitor()
		{
			edgePaths = new HashMap<String, EdgePath>();
		}

		public void OnVisited(EdgePath path)
		{
			String key = path.getFirstNode().getName() + " -> " + path.getLastNode().getName();
			EdgePath e = edgePaths.get(key);
			if (e == null) {
				edgePaths.put(key, path.cloneObject());
			} else {
				if (path.size() < e.size()) {
					// we have a shorter path; overwrite the existing
					edgePaths.put(key, path.cloneObject());
				}
			}
		}
	}

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	private HashMap<String, ITransliterator> map;

	private HashMap<String, ITransliterator> cachedMap;
	private HashSet<String> codingsCache;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public TransliterationManager()
	{
		map = new HashMap<String, ITransliterator>();

		buildCache();
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public void prepare()
	{
		buildCache();
	}

	private void buildCache()
	{
		// build graph

		Graph g = new Graph();
		for (ITransliterator t : map.values()) {
			Node nodeFrom = g.getCreateNode(t.fromSchema());
			Node nodeTo = g.getCreateNode(t.toSchema());

			Edge edge = nodeFrom.connectToNode(nodeTo);
			edge.associations().put("t", t);
		}

		// build list of all paths

		Visitor v = new Visitor();
		for (Node n : g.getNodes()) {
			n.depthFirstIteration(v);
		}

		// build transliterations

		cachedMap = new HashMap<String, ITransliterator>();
		codingsCache = new HashSet<String>();
		for (EdgePath p : v.edgePaths.values()) {
			String key = p.getFirstNode().getName() + " -> " + p.getLastNode().getName();
			cachedMap.put(key, __buildTransliterator(p));
			codingsCache.add(key);
		}
	}

	private static ITransliterator __buildTransliterator(EdgePath p)
	{
		if (p.size() == 1) return (ITransliterator)(p.get(0).associations().get("t"));

		ITransliterator[] a = new ITransliterator[p.size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = (ITransliterator)(p.get(i).associations().get("t"));
		}
		return new TransliteratorChain(a);
	}

	public String[] getAvailableTransliterators()
	{
		if (cachedMap == null) buildCache();

		String[] data = (new ArrayList<String>(codingsCache)).toArray(new String[codingsCache.size()]);
		Arrays.sort(data);
		return data;
	}

	protected void register(ITransliterator transliterator)
	{
		cachedMap = null;

		String key = transliterator.fromSchema() + "~" + transliterator.toSchema();
		map.remove(key);
		map.put(key, transliterator);
	}

	/**
	 * Try to obtain a stored transliterator.
	 *
	 * @return	This method returns <code>null</code> if no transliterator is found for the specified schemas.
	 */
	public ITransliterator get(String fromSchema, String toSchema)
	{
		if (cachedMap == null) buildCache();

		String key = fromSchema + " -> " + toSchema;
		return cachedMap.get(key);
	}

}
