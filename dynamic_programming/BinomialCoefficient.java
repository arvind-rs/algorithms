import java.util.*;
import java.awt.*;

/*
* A memoization and DP solution to the Binomial Coefficient problem or n choose k problem.
* Author: ArvindRS
* Date: 04/15/2017
*/

public class BinomialCoefficient {

	// Main function
	public static void main(String[] args) {

		// Test cases
		int test_1_n = 5;
		int test_1_k = 3;

		int test_2_n = 10;
		int test_2_k = 5;

		// Calling the compute functions and displaying the results
		System.out.println(computeMemo(test_1_n,test_1_k));
		System.out.println(computeMemo(test_2_n,test_2_k));
		System.out.println(computeDP(test_1_n,test_1_k));
		System.out.println(computeDP(test_2_n,test_2_k));
	}

	// Public function to instantiate the cache and call the recursive function
	public static int computeMemo(int n, int k) {

		// Create a Hashmap<Point(),Integer>
		HashMap<Point,Integer> memo = new HashMap<Point,Integer>();

		// Call the recursive function
		return computeMemo(n,k,memo);
	}

	// Private function to recursively compute the binomial co-efficient value
	private static int computeMemo(int n, int k, HashMap<Point,Integer> memo) {

		// Base cases
		if(n == k) return 1;
		if(k == 0) return 1;

		// Create a Point object of (n,k)
		Point point = new Point(n,k);

		// Check if the result is available in the cache
		if(memo.get(point) != null) return memo.get(point);

		// Recursively call the function and get the result;
		int result = computeMemo(n-1,k-1,memo) + computeMemo(n-1,k,memo);

		// Store the result in the Hashmap
		memo.put(point,result);

		// Return the result
		return result;
	}

	// Public function to compute the binomial co-efficient using DP
	public static int computeDP(int n, int k) {

		// Create the cache to store the results
		int[][] memo = new int[n + 1][n + 1];

		// Initialize the cache
		for(int i = 0; i < n + 1; i++)
			memo[i][0] = 1;
		for(int i = 0; i < n + 1; i++)
			memo[i][i] = 1;

		// Iteratively compute the results using the existing values in the table and store it back in the table
		for(int i = 1; i < n + 1; i++) {
			for(int j = 1; j < i; j++) {
				memo[i][j] = memo[i - 1][j - 1] + memo[i - 1][j];
			}
		}

		// Return the required result which will be stored in table[n][k]
		return memo[n][k];
	}
}