package de.general.graph;


import java.util.*;


/**
 *
 * @author knauth
 */
public class Edge
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	private final Graph graph;
	private Node fromNode;
	private Node toNode;
	private int id;

	private HashMap<String, Object> assocs;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	protected Edge(Graph graph, int id, Node fromNode, Node toNode)
	{
		this.id = id;
		this.graph = graph;
		this.fromNode = fromNode;
		this.toNode = toNode;
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public HashMap<String, Object> associations()
	{
		if (assocs == null) assocs = new HashMap<String, Object>();
		return assocs;
	}

	public int getID()
	{
		return id;
	}

	public Node getNodeFrom()
	{
		return fromNode;
	}

	public Node getNodeTo()
	{
		return toNode;
	}

	public Graph getGraph()
	{
		return graph;
	}

	public void remove()
	{
		if (fromNode == null) return;

		fromNode.edgesTo.remove(this);
		toNode.edgesFrom.remove(this);

		fromNode = null;
		toNode = null;
		id = -1;
	}

	@Override
	public String toString()
	{
		if (id < 0) {
			return "Edge[(null)]";
		} else {
			return "Edge[" + fromNode.getName() + " -> " + toNode.getName() + "]";
		}
	}

}
