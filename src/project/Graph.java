// Megan Meyers
// 496 Project 3
// 5/11/19

package project;

import java.io.*;
import java.util.*;

public class Graph {

	private ArrayList<EdgeNode>[] adjList;
	private int nVertices;
	private int nEdges;
	private int totalEdgeWeight;
	private boolean cycle;

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
			System.out.println("File not found");
			e.printStackTrace();
		}
	}

	// Creates a Graph with n vertices and 0 edges
	@SuppressWarnings("unchecked")
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

	
	public void dijkstraShortestPaths(int start){
		 
		 PriorityQueue<PQNode> pq = new PriorityQueue<PQNode>();
		 int[] distance = new int[nVertices];
		 int[] parent = new int[nVertices];					 
		 int[] s = new int[nVertices];		 				 //is a vertex in the cloud? if yes 1, if not 0
		 for (int i = 0; i < nVertices; i++){
			 distance[i] = 1000000;
			 parent[i] = -1;
			 s[i] = 0;
			 if(i == start)
				 pq.add(new PQNode(i, 0));					 //make sure the start node gets marked with 0 distance	 
			 else
				 pq.add(new PQNode(i, distance[i]));			 			 
		 }
		 distance[start] = 0;			 
		 while (!pq.isEmpty()){
			 PQNode temp = pq.poll();  						 //remove the min node - gets start vertex first (0 distance)
			 int u = temp.vertex;
			 s[u] = 1;  			 						 //add vertex u to the cloud					 
			 for (int i = 0; i < adjList[u].size(); i++){	 //look at each edge that originates from vertex u
				 int z = adjList[u].get(i).vertex2;
				 int w = adjList[u].get(i).weight;
				 if (s[z] == 0){ 							 //check that vertex z is not already in the cloud
					 if (distance[z] > distance[u] + w){	 //edge relaxation- shrinks the upper bound on distance
						 distance[z] = distance[u] + w;
						 parent[z] = u;						 
						 pq.add(new PQNode(z,distance[z]));  //update the distance by replacing the node						 
					 }					 
				 }				 
			 }			 
		 }
		 System.out.println("Distance: "+Arrays.toString(distance));
		 System.out.print("From: "+Arrays.toString(parent));
	 }
	 
	
    //Kruskal's algorithm for MST implements clusters as an array of linked-lists with an addition names array that memoizes locations of 
	//vertices. Integer objects are aliases for vertices in the linked-lists. A java PQ allows for consideration by increasing weight - 
	//ties are handled arbitrarily. If edge weights are not unique, different MSTs MAY result. 
	
	@SuppressWarnings("unchecked")
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
		int u, v, indexu, indexv;
		while ((mst.nEdges < nEdges - 1) & (!pq.isEmpty())) {
			EdgeNode temp = pq.poll();
			u = temp.vertex1;
			v = temp.vertex2;
			indexu = names[u];  										//check the names array to find location of u and v
			indexv = names[v];		
			if (names[indexu] != names[indexv]) {   					//if these values != then they reside in different clusters
				mst.addEdge(u, v, temp.weight);				
				if (cluster[indexu].size() > cluster[indexv].size()) {
					for (int i = 0; i < cluster[indexv].size(); i++) {
						int vertex = cluster[indexv].remove(i); 		//remove a vertex from old cluster
						cluster[indexu].add(vertex);					//add it to the new cluster
						names[vertex] = indexu; 						//go to its names memo and update its location
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
	
	public Graph dfsTraversal(int start){
		
		boolean connected = true;
		int components;
		Graph tree = new Graph(nVertices);		
		ArrayList<Integer> order = new ArrayList<Integer>();
	    int[] visit = new int[nVertices];
	    for (int i = 0; i < nVertices; i++){
	    	visit[i] = 0;
	    }
		dfsHelper(start, visit, order);	
        components = 1;
		for (int i = 0; i < visit.length; i++){
			if (visit[i] == 0)
				connected = false;
		}				
		if (connected){
			System.out.println("Graph is connected");
			tree = spanFromDFS(tree);						
		} else {
			System.out.println("Graph is not connected. Running dfsTraversal on remaining vertices");		
			tree = null;
			for (int i = 0; i < visit.length; i++){
				if (visit[i] == 0){
					components += 1;
					dfsHelper(i, visit, order);
				}
			}
		}
		System.out.println("Order nodes were visited: "+ order.toString());
		System.out.println("Number of components: "+ components + "\nHas cycle? "+ cycle);		
		return tree;
	}
	
	private void dfsHelper(int s, int[] visited, ArrayList<Integer> orders)	{
		
		visited[s] = 1; 								   //mark vertex visited with 1 
		orders.add(s);									   //save the order that vertices are visited
		for (int i = 0; i < adjList[s].size(); i++){
			if (!adjList[s].get(i).explored){  			   //if edge is unexplored
				int w = adjList[s].get(i).vertex2;  	   //let w = the end vertex
				if (visited[w] == 0){ 					   //if the vertex is unexplored
					adjList[s].get(i).back = false; 	   //not back = discovery
					dfsHelper(w, visited, orders);					  
				} else {
					adjList[s].get(i).back = true;
					cycle = true;						   //back edges indicate cycles
				}
			}			
		}		
	}
	
	private Graph spanFromDFS(Graph t){
		
		for (int i = 0; i < nVertices; i++){
			for (int j = 0; j < adjList[i].size(); j++){	
				if (!adjList[i].get(j).back){				
					t.addEdge(adjList[i].get(j).vertex1, adjList[i].get(j).vertex2, adjList[i].get(j).weight);
				}
			}			
		}		
		return t;
	}
}// end class Graph



class EdgeNode implements Comparable<EdgeNode> {

	int vertex1;
	int vertex2;
	int weight;
	boolean back;			 //for DFS - back = false means edge is discovery
	boolean explored;		 //for DFS 
    
	
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
		return "(" + vertex + "," + distance + ")";
	}

}// end class PQNode

//PERSONAL: anything passed by reference will update and be usable in original after recursive helper method finishes, 
//but a primitive type can only be passed? when i tried to pass int cycle to helper, change it in helper, and use it again 
//in original method, it failed. but my arrays work fine. check on this later. 
