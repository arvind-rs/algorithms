
/*
 * Java implementation of the Bidirectional Search algorithm.
 * @author: ArvindRS
 * @date: 12/25/2017
 */

import java.util.*;

class BidirectionalSearch {
	public static void main(String[] args) {
		// Test cases
		int[][] graph = {{0, 1, 0, 1},{1, 0, 0, 1},{0, 0, 0, 1},{1, 1, 1, 0}}; // true
		int[][] graph1 = {{0, 1, 0, 1},{1, 0, 0, 1},{0, 0, 0, 0},{1, 1, 0, 0}}; // false
		System.out.println(Arrays.deepToString(graph));
		System.out.println(bidirSearch(graph, 1, 2));
	}

	// Function to run the bidirectional search. TC = O(b^(d/2)).
	public static boolean bidirSearch(int[][] graph, int start, int end) {
		if(graph == null || graph.length == 0) return false;

		// We run two separate BFS from start and end nodes.
		// Thus we need two visited[] and two Queues.
		boolean[] visitedS = new boolean[graph.length];
		boolean[] visitedE = new boolean[graph.length];
		Queue<Integer> queueS = new LinkedList<Integer>();
		Queue<Integer> queueE = new LinkedList<Integer>();

		visitedS[start] = true;
		visitedE[end] = true;
		queueS.add(start);
		queueE.add(end);

		while(!queueS.isEmpty() && !queueE.isEmpty()) {
			// Moving the duplicate code to a separate function.
			bfs(graph, queueS, visitedS);
			bfs(graph, queueE, visitedE);
			// If we find a node that has been visited by both the BFS runs, then we have found our path.
			if(checkIntersection(visitedS, visitedE))
				return true;
		}
		return false;
	}

	// Function that run the Breadth-First Search algorithm.
	private static void bfs(int[][] graph, Queue<Integer> queue, boolean[] visited) {
		if(graph == null || graph.length == 0 || 
			queue == null || queue.isEmpty() || visited == null)
			return;
		int node = queue.remove();
		for(int i = 0; i < graph.length; i++) {
			if(graph[node][i] > 0 && !visited[i]) {
				visited[i] = true;
				queue.add(i);
			}
		}
	}

	// Function to check if two boolean[] are intersecting (true) at one point
	private static boolean checkIntersection(boolean[] array1, boolean[] array2) {
		if(array1 == null || array2 == null || array1.length != array2.length)
			return false;
		for(int i = 0; i < array1.length; i++) {
			if(array1[i] && array2[i])
				return true;
		}
		return false;
	}
}