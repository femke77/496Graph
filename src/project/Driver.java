package project;

public class Driver {

	public static void main(String[] args) {

		System.out.println("Project 3 TestCase A:");
		Graph g1 = new Graph("inputA.txt");
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

		System.out.println("\nDijkstra SP");
		int start = 3;
		g1.dijkstraShortestPaths(start);

		// -----------------------------------------------------

		System.out.println("\nKruskal's MST ");
		Graph treeKruskal = g1.kruskalMST();
		if (treeKruskal != null) {
			System.out.println("\nMST Tree");
			treeKruskal.printGraph();
		}
		// ------------TestCase B ------------------------
		System.out.println("\n\nProject 3 TestCase B:");
		Graph g2 = new Graph("inputB.txt");
		System.out.println("Print Graph");
		g2.printGraph();
		// -----------------------------------------------

		int v2 = 3;
		System.out.println("\nDFS Traversal starting at " + v2);
		Graph tree2 = g2.dfsTraversal(v2);
		if (tree2 != null) {
			System.out.println("\nDFS Spanning Tree ");
			tree2.printGraph();
		}

		// -----------------------------------------------
		System.out.println("\nDijkstra SP");
		int start2 = 2;
		g2.dijkstraShortestPaths(start2);

	}

}
//PERSONAL: anything passed by reference will update and be usable in original after recursive helper method finishes, 
//but a primitive type can only be passed? when i tried to pass int cycle to helper, change it in helper, and use it again 
//in original method, it failed. but my arrays work fine. check on this later. 