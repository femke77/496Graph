package project;

public class Driver {

	public static void main(String[] args) {

		System.out.println("Project 3 TestCase A:");
		Graph g1 = new Graph("inputA.txt");
		System.out.println("Print Graph");
		g1.printGraph();
		System.out.println("\nNumber of Vertices = " + g1.get_nVertices() + "\tNumber of Edges = " + g1.get_nEdges()
				+ "\tTotal Edge Weight = " + g1.get_TotalWeightOfEdges());

	}

}
