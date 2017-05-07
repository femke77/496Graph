package project;

import java.io.*;
import java.util.*;

public class Graph {

	private ArrayList<EdgeNode>[] adjList;
	private int nVertices;
	private int nEdges;
	private int totalEdgeWeight;
	

	// creates Graph from data in file
	public Graph(String inputFileName) {

		File file = new File(inputFileName);
		try {
			Scanner scan = new Scanner(file);
			nVertices = scan.nextInt(); 
			adjList = new ArrayList[nVertices];
			for (int i = 0; i < nVertices; i++) {
				adjList[i] = new ArrayList<EdgeNode>(); 
			}
			int v1, v2, w;
			while (scan.hasNext()) {
				v1 = scan.nextInt();
				v2 = scan.nextInt();
				w = scan.nextInt();
				addEdge(v1, v2, w);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Creates a Graph with n vertices and 0 edges
	public Graph(int n) {

		nVertices = n;
		nEdges = 0;
	}

	public void addEdge(int i, int j, int weight) {
		
		adjList[i].add(new EdgeNode(i, j, weight));
		adjList[j].add(new EdgeNode(j, i, weight));
		totalEdgeWeight += weight;
		nEdges += 1;
	}

	public void printGraph() {
		// prints nVertices, nEdges, and adjacency lists and total edge weight
		// See format below in item 6.
	}

	public int get_nVertices() {
		return nVertices;
	}

	public int get_nEdges() {
		return nEdges;
	}

	public int get_TotalWeightOfEdges() {
		return totalEdgeWeight;
	}
/*
	
	public Graph dfsTraversal(int start) {
		/*
		 * Use recursion by calling a recursive dfs method. Visit all nodes. If
		 * graph is not connected you will need to call dfs more than once to
		 * visit all nodes .
		 * 
		 * Print the following information gleaned from the dfs traversal 
		 * Print nodes in order visited  Connected? ____  NumberOfComponents?
		 * _____  Has a cycle? _______ If the graph is connected, return the
		 * spanning tree from the dfs traversal. Otherwise, return null.
		 *
	}

	public void dijkstraShortestPaths(int start) {
		/*
		 * Implement Dijkstra algorithm from text or class; Use the Java
		 * PriorityQueue<PQNode> class. Use PQNode class below. The Java
		 * PriorityQueue class has no updateKey method. For our problem, just
		 * add a new updated node to the priority queue. This will work for
		 * Dijkstra’s algorithm since the new node has a smaller priority than
		 * the node you want to update. See Problem C-14.3 in text. An
		 * alternative is to remove the old node and add a new node.
		 * 
		 * Print shortest paths from vertex start to all other vertices
		 * reachable from start. Use format from class.
		 *}
         
	public Graph kruskalMST() {
		/*
		 * Implement Kruskal algorithm from text or class. You may assume that
		 * the graph is connected. You may sort the edges or use a priority
		 * queue. Use clusters. Print the edges of the MST found and its total
		 * weight Return the minimum spanning tree as a Graph
		 *
	}
    */
}// end class Graph

class EdgeNode implements Comparable<EdgeNode> {

	int vertex1;
	int vertex2;
	int weight;

	public EdgeNode(int v1, int v2, int w) {
		vertex1 = v1;
		vertex2 = v2;
		weight = w;
	}

	public int compareTo(EdgeNode node) {
		return weight - node.weight;
	}

	public String toString() {

		String s = "(";
		s = s + vertex1 + "," + vertex2 + "," + weight + ")";
		return s;
	}

}// end class EdgeNode

class PQNode implements Comparable<PQNode> {

	int vertex;
	int distance;

	public PQNode(int v, int z) {
		vertex = v;
		distance = z;
	}

	public int compareTo(PQNode node) {
		return (distance - node.distance);
	}

	public String toString() {
		return "(" + vertex + "," + distance;
	}

}// end class PQNode
