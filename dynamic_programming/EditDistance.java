import java.util.*;

/*
* A dynamic programming solution to the Edit Distance problem.
* The Edit Distance problem is defined as given two strings A and B, what is the minimum number of operations (substitution, insertion and deletion)
* required to convert string A to string B.
* Author: ArvindRS
* Date: 04/16/2017
*/ 

public class EditDistance {

	// Main function
	public static void main(String[] args) {

		// Test cases
		String test_1_a = "hello world";
		String test_1_b = "wello horld";
		String test_2_a = "shot";
		String test_2_b = "spot";
		String test_3_a = "a recurrent relation";
		String test_3_b = "arecurrnnt relations"; // 3

		// Calculating runtime of my function calls
		long startTime = System.currentTimeMillis();

		// Calling the vanilla recursive function
		System.out.println(compute(test_1_a,test_1_b));
		System.out.println(compute(test_2_a,test_2_b));
		//System.out.println(compute(test_3_a,test_3_b)); //Takes a lot of time.

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("runtime: "+(totalTime) / 1000);

		// Calling the memoized recursive function
		System.out.println(computeMemo(test_1_a,test_1_b));
		System.out.println(computeMemo(test_2_a,test_2_b));
		System.out.println(computeMemo(test_3_a,test_3_b));

		// Calling the DP function
		System.out.println(computeDP(test_1_a,test_1_b));
		System.out.println(computeDP(test_2_a,test_2_b));
		System.out.println(computeDP(test_3_a,test_3_b));
	}

	// Public function to compute the edit distance using a vanilla recursive approach
	public static int compute(String a, String b) {

		// Base cases
		if(a.length() <= 0 && b.length() <= 0) return 0;
		if(a.length() <= 0) return b.length();
		if(b.length() <= 0) return a.length();

		// Temp variable to hold {0|1} depending on whether the last characters in the strings are equal or not
		int add = (a.charAt(a.length()-1) == b.charAt(b.length()-1))? 0 : 1;

		// Recursively find the min() of the three possible branches
		return Math.min(compute(a.substring(0,a.length()-1),b.substring(0,b.length()-1)) + add,
						Math.min(compute(a.substring(0,a.length()-1),b) + 1,
								compute(a,b.substring(0,b.length()-1)) + 1)
						);
	}

	// Public wrapper function to call the memoized recursive function
	public static int computeMemo(String a, String b) {

		// Create a cache<String,result>
		HashMap<String,Integer> memo = new HashMap<String,Integer>();

		return computeMemo(a,b,memo);
	}

	// Private function to compute the edit distance using a memoized recursive approach
	public static int computeMemo(String a, String b, HashMap<String,Integer> memo) {

		// Base cases
		if(a.length() <= 0 && b.length() <= 0) return 0;
		if(a.length() <= 0) return b.length();
		if(b.length() <= 0) return a.length();

		// Creating a Pair string to hold the pair of strings
		String pair = a + b;

		// Check if the value has already been computed; if yes return it
		if(memo.get(pair) != null) return memo.get(pair);

		// Temp variable to hold {0|1} depending on whether the last characters in the strings are equal or not
		int add = (a.charAt(a.length()-1) == b.charAt(b.length()-1))? 0 : 1;

		// Recursively find the min() of the three possible branches
		int result = Math.min(computeMemo(a.substring(0,a.length()-1),b.substring(0,b.length()-1),memo) + add,
						Math.min(computeMemo(a.substring(0,a.length()-1),b,memo) + 1,
								computeMemo(a,b.substring(0,b.length()-1),memo) + 1)
						);

		// Store the result with the Pair of strings in the memo and return the result
		memo.put(pair,result);

		return result;
	}


	// Public function to compute the edit distance using the dynamic programming approach
	public static int computeDP(String a, String b) {

		// Create the cache matrix
		int[][] cache = new int[a.length() + 1][b.length() + 1];

		// Initialize the cache
		for(int i = 0; i <= a.length(); i++)
			cache[0][i] = i;
		for(int i = 0; i <= b.length(); i++)
			cache[i][0] = i;

		// Iterate through the 2D matrix, computing the result using the recursive formula and storing it in the cache
		for(int i = 1; i <= a.length(); i++) {
			for(int j = 1; j <= b.length(); j++) {
				int add = (a.charAt(i-1) == b.charAt(j-1))? 0 : 1;
				cache[i][j] = Math.min(cache[i-1][j-1] + add, Math.min(cache[i-1][j] + 1, cache[i][j-1] + 1));
			}
		}

		// Return the required result, which will be stored at cache[n,m]
		return cache[a.length()][b.length()];
	}
}