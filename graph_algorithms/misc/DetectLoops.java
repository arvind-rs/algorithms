
/*
 * Algorithm to detect the presence of loops / cycles in a graph using DFS in Java.
 * @author: ArvindRS
 * @date: 12/24/2017
 */

import java.util.*;

class DetectLoops {
	public static void main(String[] args) {
		// Test cases
		int[][] graph = {{0, 1, 0, 1},{1, 0, 1, 1},{0, 1, 0, 1},{1, 1, 1, 0}}; // True
		int[][] graph1 = {{0, 1, 0, 1},{1, 0, 0, 1},{0, 0, 0, 1},{1, 1, 1, 0}}; // True
		int[][] graph2 = {{0, 1, 0, 1},{1, 0, 0, 0},{0, 0, 0, 1},{1, 0, 1, 0}}; // False
		displayMatrix(graph2);
		System.out.println(detectLoop(graph2));
	}

	// Function to detect loops / cycles in a graph using DFS and a HashSet
	public static boolean detectLoop(int[][] graph) {
		if(graph == null || graph.length == 0)
			return false;
		// Implementing DFS iteratively using an explicit stack.
		Stack<Integer> stack = new Stack<Integer>();
		// Using a Set to track the vertices currently in the recursion stack.
		// If we come across a vertex that's already in the recursion stack,
		// then that means there's a cycle in the graph.
		HashSet<Integer> set = new HashSet<Integer>();
		// Array of visited, to track the visited vertices
		boolean[] visited = new boolean[graph.length];

		stack.push(0);
		set.add(0);
		while(!stack.isEmpty()) {
		 	int vertex = stack.pop();
		 	// Once we pop a vertex from the stack, remove it from the set
		 	set.remove(vertex);
		 	// Mark the vertex as visited
		 	visited[vertex] = true;
		 	for(int i = 0; i < graph.length; i++) {
		 		if(graph[vertex][i] > 0 && !visited[i]) {
		 			// Check if the prospective vertex is already in the set
		 			if(set.contains(i)) return true;
		 			set.add(i);
		 			stack.push(i);
		 		}
		 	}
		}
		return false;
	}

	// Helper function to display a 2D matrix
	public static void displayMatrix(int[][] matrix) {
		if(matrix == null || matrix.length == 0) return;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println();
		}
	}
}