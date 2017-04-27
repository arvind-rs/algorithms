import java.util.*;

/*
* Solution to the Longest Common Subsequence of two given strings problem.
* Author: ArvindRS
* Date: 04/26/2017
*/

public class LongestCommonSubsequence {

	// Main function
	public static void main(String[] args) {

		// Test cases 
		String test1_a = "abcd";
		String test1_b = "bd";

		String test2_a = "goodmorning";
		String test2_b = "hodor";

		// Calling the DP function
		System.out.println(lcssDP(test1_a, test1_b));
		System.out.println(lcssDP(test2_a, test2_b));

		// Calling the recursive function
		System.out.println(lcssMemo(test1_a, test1_b));
		System.out.println(lcssMemo(test2_a, test2_b));
	}

	// Public wrapper function for the recursive solution to LCSS
	public static int lcssMemo(String a, String b) {

		// Print the strings
		System.out.println(a + "," + b);

		// Create a 2D Matrix of dimensions n,m instead of n+1,m+1
		int[][] memo = new int[a.length()][b.length()];

		// Initialize the matrix
		for(int i = 0; i < a.length(); i++)
			for(int j = 0; j < b.length(); j++)
				memo[i][j] = -1;

		// Call the recursive function
		return lcssMemo(a,b,memo);
	}

	// Private recursive LCSS function with memoization
	private static int lcssMemo(String a, String b, int[][] memo) {

		// Base cases
		if(a.length() == 0 || b.length() == 0)
			return 0;

		// Check if solution has already been computed
		if(memo[a.length()-1][b.length()-1] != -1)
			return memo[a.length()-1][b.length()-1];

		// Get the substrings
		String asub = a.substring(0,a.length()-1);
		String bsub = b.substring(0,b.length()-1);

		// Recursively compute the value
		int d = (a.charAt(a.length()-1) == b.charAt(b.length()-1))? 1 : 0;
		int result = Math.max(lcssMemo(asub,bsub,memo) + d, Math.max(lcssMemo(a,bsub,memo),lcssMemo(asub,b,memo)));
		
		// Store the result
		memo[a.length()-1][b.length()-1] = result;

		// Return the result
		return result;
	}

	// Public function to find the longest common subsequence using dynamic programming
	public static int lcssDP(String a, String b) {

		// Print the strings
		System.out.println(a + "," + b);

		// Edge case
		if(a == null || b == null)
			return -1;

		// Create the 2D matrix
		int[][] cache = new int[a.length()+1][b.length()+1];

		// Initialize the matrix
		for(int i = 0; i <= a.length(); i++)
			cache[i][0] = 0;
		for(int i = 0; i <= b.length(); i++)
			cache[0][i] = 0;

		// Iteratively, build up the values from the base cases
		for(int i = 1; i <= a.length(); i++) {
			for(int j = 1; j <= b.length(); j++) {
				int d = (a.charAt(i-1) == b.charAt(j-1))? 1 : 0;
				cache[i][j] = Math.max(cache[i-1][j-1] + d, Math.max(cache[i-1][j],cache[i][j-1]));
			}
		}

		// Return the result
		return cache[a.length()][b.length()];
	}
}