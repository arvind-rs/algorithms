
/*
 * Topological sort implementation in Java.
 * @author: arvind-rs
 * @date: 02/25/2018
 */

import java.util.*;
import java.io.*;

class TopologicalSort {

	public static void main(String[] args) throws FileNotFoundException {

		// Get the input graph file from the command line and read the file
		String filename = args[0];
		Scanner sc = new Scanner(new File(filename));

		// Build the graph
		int n = sc.nextInt();
		int[][] graph = new int[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				graph[i][j] = sc.nextInt();

		// Run the algorithm
		topoSort(graph);
	}

	// Function that performs Topological sort.
	// TC = O(V + E), SC = O(V)
	public static void topoSort(int[][] graph) {
		if(graph == null || graph.length == 0) return;

		// Array to keep track of number of incoming edges for each vertex
		int[] incoming = new int[graph.length];
		for(int i = 0; i < graph.length; i++)
			for(int j = 0; j < graph.length; j++)
				incoming[j] += (graph[i][j] > 0)? 1: 0;

		// Variable to track the number of vertices visited
		int vertexCount = 0;

		// Queue to visit the next vertices with 0 incoming edges
		Queue<Integer> queue = new LinkedList<>();

		// Add all the vertices with 0 incoming edges to the queue
		for(int i = 0; i < incoming.length; i++) {
			if(incoming[i] == 0)
				queue.add(i);
		}

		// Visit all the other vertices, removing the incoming edge to their neighbours as we visit.
		while(!queue.isEmpty()) {
			int u = queue.remove();
			System.out.println(u);
			vertexCount++;
			for(int v = 0; v < graph.length; v++) {
				if(graph[u][v] > 0 && --incoming[v] == 0)
					queue.add(v);
			}
		}

		// If we haven't visited all the vertices then there was a loop somewhere in the graph.
		if(vertexCount != graph.length)
			System.out.println("The graph contains loop(s)!");
	}
}