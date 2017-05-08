package project;

public class Driver {

	public static void main(String[] args) {

		System.out.println("Project 3 TestCase A:");
		Graph g1 = new Graph("inputC.txt");
		System.out.println("Print Graph");
		g1.printGraph();
		
		// -----------------------------------------------------
		
		System.out.println("\nKruskal's MST ");
		Graph treeKruskal = g1.kruskalMST();
		if (treeKruskal != null) {
			System.out.println("\nMST Tree");
			treeKruskal.printGraph();
		}
	}

}
