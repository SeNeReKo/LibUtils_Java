package de.general.graph;


import de.general.graph.impl.*;


/**
 *
 * @author knauth
 */
public class EdgePath
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	private Edge[] edges;
	private int count;

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public EdgePath()
	{
		edges = new Edge[16];
	}

	/**
	 * Constructor.
	 */
	public EdgePath(EdgePath other)
	{
		int n = 16;
		while (n < other.count) n *= 2;

		edges = new Edge[n];
		System.arraycopy(other.edges, 0, edges, 0, other.count);
		this.count = other.count;
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public Node getFirstNode()
	{
		if (count == 0) return null;
		return edges[0].getNodeFrom();
	}

	public Node getLastNode()
	{
		if (count == 0) return null;
		return edges[count - 1].getNodeTo();
	}

	public void push(Edge edge)
	{
		edges = ArrayHelper.resizeArray(edges, count, count + 1);
		edges[count] = edge;
		count++;
	}

	public Edge pop()
	{
		if (count == 0) return null;
		count--;
		return edges[count];
	}

	public int size()
	{
		return count;
	}

	public Edge get(int index)
	{
		if ((index < 0) || (index >= count)) return null;
		return edges[index];
	}

	public Edge[] toArray()
	{
		Edge[] ret = new Edge[count];
		for (int i = 0; i < count; i++) {
			ret[i] = edges[i];
		}
		return ret;
	}

	public EdgePath cloneObject()
	{
		return new EdgePath(this);
	}

}
