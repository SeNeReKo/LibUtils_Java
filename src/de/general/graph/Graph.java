package de.general.graph;


import java.lang.reflect.*;
import java.util.*;

import de.general.graph.impl.*;


/**
 *
 * @author knauth
 */
public class Graph
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	private int nextNodeID;
	protected int nextEdgeID;
	private HashMap<String, Node> nodeMap;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public Graph()
	{
		clear();
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public int getNodeCount()
	{
		return nodeMap.size();
	}

	public HashSet<String> getNodeNames()
	{
		HashSet<String> set = new HashSet<String>(nodeMap.keySet());
		return set;
	}

	public ArrayList<Node> getNodes()
	{
		ArrayList<Node> set = new ArrayList<Node>(nodeMap.values());
		return set;
	}

	protected Edge createEdgeObject(Node from, Node to)
	{
		Edge e = new Edge(this, nextEdgeID, from, to);
		nextEdgeID++;
		return e;
	}

	public void clear()
	{
		nextNodeID = 0;
		nextEdgeID = 0;
		nodeMap = new HashMap<String, Node>();
	}

	public Node createNode(String name)
	{
		if ((name == null) || (name.length() == 0)) throw new RuntimeException("Nodes must have a name!");
		if (nodeMap.containsKey(name)) throw new RuntimeException("Node \"" + name + "\" already exists!");

		Node node = new Node(this, nextNodeID, name);
		nextNodeID++;
		nodeMap.put(name, node);
		return node;
	}

	public Node getCreateNode(String name)
	{
		if ((name == null) || (name.length() == 0)) throw new RuntimeException("Nodes must have a name!");
		Node node = nodeMap.get(name);
		if (node == null) {
			node = new Node(this, nextNodeID, name);
			nextNodeID++;
			nodeMap.put(name, node);
		}
		return node;
	}

	public Node getNode(String name)
	{
		if ((name == null) || (name.length() == 0)) throw new RuntimeException("Nodes must have a name!");
		Node node = nodeMap.get(name);
		return node;
	}

}
