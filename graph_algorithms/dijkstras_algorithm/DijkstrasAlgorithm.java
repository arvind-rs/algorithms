
/*
 * Java implementation of the Dijkstra's single source shortest path finder algorithm
 * @author: ArvindRS
 * @date: 10/22/2017
 */

import java.util.*;
import java.io.*;

// Helper class to wrap a vertex to be added to the MinHeap
class Vertex implements Comparable<Vertex> {

	int id, dist;

	Vertex(int id, int dist) {
		this.id = id;
		this.dist = dist;
	}

	public int compareTo(Vertex v) {
		return this.dist - v.dist;
	}

}

class DijkstrasAlgorithm {

	public static void main(String[] args) throws Exception {

		String filename = args[0];
		Scanner in = new Scanner(new File(filename));
		int n = in.nextInt();

		int[][] graph = new int[n][n];
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < n; j++)
				graph[i][j] = in.nextInt();

		int[][] dist = dijkstras(graph, 0);

		for(int i = 0; i < dist.length; i++) {
			System.out.println((i+1) + " : " +dist[i][0] + " : " + (dist[i][1]+1));
		}
	}

	// Public function implementing the Dijkstra's algorithm
	public static int[][] dijkstras(int[][] graph, int source) {

		// Defining infinity
		int oo = (int)1e9;
		// Creating dist and visited arrays
		int[][] dist = new int[graph.length][2];
		boolean[] visited = new boolean[graph.length];
		// Create a minHeap
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		// vertexCount is used to keep track of number of vertices seen
		int vertexCount = 0;
		// Initialize everything
		for(int i = 0; i < dist.length; i++)
			dist[i][0] = oo;
		dist[source][0] = 0;
		dist[source][1] = source;

		pq.add(new Vertex(source,dist[source][0]));

		// Loop until all the vertices have been seen
		while(!pq.isEmpty() && vertexCount < graph.length) {
			Vertex v = pq.remove();
			if(visited[v.id]) continue; // Skip over if the current vertex was already seen
			//System.out.println(v.id);
			visited[v.id] = true;
			vertexCount++;
			// Add all the neighbours that are cost effective to visit and have not been visited
			for(int i = 0; i < graph.length; i++) {
				if(graph[v.id][i] > 0 && !visited[i] && dist[v.id][0] + graph[v.id][i] < dist[i][0]) {
					dist[i][0] = dist[v.id][0] + graph[v.id][i];
					dist[i][1] = v.id;
					pq.add(new Vertex(i, dist[i][0]));
				}
			}
		}

		// Return the dist array
		return dist;
	}
}