package project;

import java.io.*;
import java.util.*;

public class Graph {

	private ArrayList<EdgeNode>[] adjList;
	private int nVertices;
	private int nEdges;
	private int totalEdgeWeight;

	// creates Graph from data in file
	@SuppressWarnings("unchecked")
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
		adjList = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<EdgeNode>();
		}
		nVertices = n;
		nEdges = 0;
	}

	// add *undirected* edges to graph
	public void addEdge(int i, int j, int weight) {

		adjList[i].add(new EdgeNode(i, j, weight));
		adjList[j].add(new EdgeNode(j, i, weight));
		totalEdgeWeight += weight;
		nEdges += 1;
	}

	public void printGraph() {
		System.out.println("\nGraph: Number of Vertices = " + get_nVertices() + "\tNumber of Edges = " + get_nEdges()
				+ "\tTotal Edge Weight = " + get_TotalWeightOfEdges() + "\nAdjacenty Lists: ");
		for (int i = 0; i < get_nVertices(); i++) {
			System.out.println("vertex = " + i + "   " + adjList[i].toString());
		}

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
	 * 
	 * public Graph dfsTraversal(int start) { /* Use recursion by calling a
	 * recursive dfs method. Visit all nodes. If graph is not connected you will
	 * need to call dfs more than once to visit all nodes .
	 * 
	 * Print the following information gleaned from the dfs traversal  Print
	 * nodes in order visited  Connected? ____  NumberOfComponents? _____ 
	 * Has a cycle? _______ If the graph is connected, return the spanning tree
	 * from the dfs traversal. Otherwise, return null.
	 *
	 * }
	 */
	
	 public void dijkstraShortestPaths(int start){
		 
		 
		 
		 
		 
	 }
	 
	
    //Kruskal's algorithm for MST implements clusters as an array of linked-lists with an addition names array that memoizes locations of 
	//vertices. Integer objects are aliases for vertices in the linked-lists. A java PQ allows consideration by increasing weight - 
	//ties are handled arbitrarily. If edge weights are not unique, different MSTs MAY result. 
	public Graph kruskalMST() {
		
		Graph mst = new Graph(nVertices);		
		LinkedList<Integer>[] cluster = new LinkedList[nVertices];
        int[] names = new int[nVertices];
		for (int i = 0; i < nVertices; i++) {
            names[i] = i;
			cluster[i] = new LinkedList<Integer>();
			cluster[i].add(i);
		}
		PriorityQueue<EdgeNode> pq = new PriorityQueue<EdgeNode>();
		for (int i = 0; i < nVertices; i++) {
			pq.addAll(adjList[i]);
		}
		while (mst.nEdges < nEdges - 1) {
			EdgeNode temp = pq.poll();
			int u = temp.vertex1;
			int v = temp.vertex2;
			int indexu, indexv;
			indexu = names[u];  //check the names array to find location of u and v
			indexv = names[v];		
			if (names[indexu] != names[indexv]) {  //if these values != then they reside in different clusters
				mst.addEdge(u, v, temp.weight);				
				if (cluster[indexu].size() > cluster[indexv].size()) {
					for (int i = 0; i < cluster[indexv].size(); i++) {
						int vertex = cluster[indexv].remove(i); //remove a vertex from old cluster
						cluster[indexu].add(vertex); //add it to the new cluster
						names[vertex] = indexu; //go to its names memo and update its location
					}
				} else {
					for (int i = 0; i < cluster[indexu].size(); i++) {
						int vertex = cluster[indexu].remove(i); 
						cluster[indexv].add(vertex); 
						names[vertex] = indexv; 						
					}
				}
			}
		}       
		return mst;
	}

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
