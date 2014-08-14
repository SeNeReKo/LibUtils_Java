package de.general.graph;


import java.util.*;


/**
 *
 * @author knauth
 */
public class Node
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	private int id;
	private final String name;
	private final Graph graph;

	protected ArrayList<Edge> edgesTo;
	protected ArrayList<Edge> edgesFrom;

	private HashMap<String, Object> assocs;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	protected Node(Graph graph, int id, String name)
	{
		this.graph = graph;
		this.id = id;
		this.name = name;

		edgesTo = new ArrayList<Edge>();
		edgesFrom = new ArrayList<Edge>();
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public HashMap<String, Object> associations()
	{
		if (assocs == null) assocs = new HashMap<String, Object>();
		return assocs;
	}

	public ListIterator<Edge> getOutgoingEdges()
	{
		return edgesTo.listIterator();
	}

	public ListIterator<Edge> getIncomingEdges()
	{
		return edgesFrom.listIterator();
	}

	public int getID()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public Graph getGraph()
	{
		return graph;
	}

	public Edge connectToNode(Node toNode)
	{
		Edge e = getEdgeTo(this, toNode);
		if (e == null) {
			e = graph.createEdgeObject(this, toNode);
			edgesTo.add(e);
			toNode.edgesFrom.add(e);
		}
		return e;
	}

	public Edge connectToNode(String name)
	{
		Edge e = connectToNode(name, false);
		if (e == null) throw new RuntimeException("No such node: " + name);
		return e;
	}

	public Edge connectToNode(String name, boolean bCreateIfNotExists)
	{
		Node toNode;
		if (bCreateIfNotExists) {
			toNode = graph.getCreateNode(name);
		} else {
			toNode = graph.getNode(name);
			if (toNode == null) return null;
		}
		Edge e = getEdgeTo(this, toNode);
		if (e == null) {
			e = graph.createEdgeObject(this, toNode);
			edgesTo.add(e);
			toNode.edgesFrom.add(e);
		}
		return e;
	}

	public Edge getEdgeTo(Node fromNode, Node toNode)
	{
		for (Edge e : edgesTo) {
			if (e.getNodeTo() == toNode) return e;
		}
		return null;
	}

	public void depthFirstIteration(IEdgePathIterationDelegate d)
	{
		__depthFirstIteration(new EdgePath(), new HashSet<Integer>(), d);
	}

	private void __depthFirstIteration(EdgePath path, HashSet<Integer> nodesVisited, IEdgePathIterationDelegate d)
	{
		for (Edge e : edgesTo) {
			if (nodesVisited.contains(e.getNodeTo().getID())) continue;
			nodesVisited.add(e.getNodeTo().getID());

			path.push(e);
			d.OnVisited(path);

			e.getNodeTo().__depthFirstIteration(path, nodesVisited, d);

			path.pop();
		}

	}

	@Override
	public String toString()
	{
		return "Node[" + name + "]";
	}

}
