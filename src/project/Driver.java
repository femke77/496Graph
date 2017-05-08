package project;

public class Driver {

	public static void main(String[] args) {

		System.out.println("Project 3 TestCase A:");
		Graph g1 = new Graph("inputD.txt");
		System.out.println("Print Graph");
		g1.printGraph();

		// -----------------------------------------------

		int v = 0;
		System.out.println("\nDFS Traversal starting at " + v);
		Graph tree = g1.dfsTraversal(v);
		if (tree != null) {
			System.out.println("\nDFS Spanning Tree ");
			tree.printGraph();
		}

		// -----------------------------------------------
/*		
		System.out.println("\nDijkstra SP");
		int start = 4;
		g1.dijkstraShortestPaths(start);
*/
		// -----------------------------------------------------
/*
		System.out.println("\nKruskal's MST "); 
		Graph treeKruskal = g1.kruskalMST(); 
		if (treeKruskal != null) {
			System.out.println("\nMST Tree"); 
			treeKruskal.printGraph(); 
		}
*/
	}

}
