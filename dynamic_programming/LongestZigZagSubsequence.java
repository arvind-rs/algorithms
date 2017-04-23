import java.util.*;

/*
* This is a DP solution to the longest zigzag subsequence problem (https://community.topcoder.com/stat?c=problem_statement&pm=1259&rd=4493)
* T(N) = O(N^2) & S(N) = O(N)
* Author: ArvindRS
* Date: 04/23/2017
*/

public class LongestZigZagSubsequence {

	// Main function
	public static void main(String[] args) {

		// Test cases
		int[] test_1 = {1,5,3,2,4}; // ans = 4
		int[] test_2 = {}; // ans = 0
		int[] test_3 = { 1, 7, 4, 9, 2, 5 }; // ans = 6
		int[] test_4 = { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 }; // ans = 7
		int[] test_5 = { 44 }; // ans = 1
		int[] test_6 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 }; // ans = 2
		int[] test_7 = { 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 }; // ans = 8
		int[] test_8 = { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 
600, 947, 978, 46, 100, 953, 670, 862, 568, 188, 
67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 
477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 
249, 22, 176, 279, 23, 22, 617, 462, 459, 244 }; // ans = 36

		// Calling the DP function
		System.out.println(zigzagDPCorrect(test_1));
		System.out.println(zigzagDPCorrect(test_2));
		System.out.println(zigzagDPCorrect(test_3));
		System.out.println(zigzagDPCorrect(test_4));
		System.out.println(zigzagDPCorrect(test_5));
		System.out.println(zigzagDPCorrect(test_6));
		System.out.println(zigzagDPCorrect(test_7));
		System.out.println(zigzagDPCorrect(test_8));

		// Calling the DP O(N) function
		System.out.println(zigzagDPNSolution(test_1));
		System.out.println(zigzagDPNSolution(test_2));
		System.out.println(zigzagDPNSolution(test_3));
		System.out.println(zigzagDPNSolution(test_4));
		System.out.println(zigzagDPNSolution(test_5));
		System.out.println(zigzagDPNSolution(test_6));
		System.out.println(zigzagDPNSolution(test_7));
		System.out.println(zigzagDPNSolution(test_8));
	}

	// Public function to compute the longest zigzag subsequence using dynamic programming. 
	// After further analysis, it's confirmed that this solution produces wrong results for certain inputs. Eg: { 1, 7, 4, 9, 2, 5 }
	public static int zigzagDP(int[] array) {

		// Print the array
		System.out.println(Arrays.toString(array));

		// Create the cache matrix
		int[] cache = new int[array.length];

		// Initialize the cache matrix
		for(int i = 0; i < cache.length; i++)
			cache[i] = 1;

		// Iteratively compute the length of the zigzag subsequence upto each element and store the result in the cache array
		for(int i = 1; i < array.length; i++) {
			// Initialize the variable that will keep track of the positive or negative sign
			// Dividing the difference by it's absolute value will give either '+1' or '-1', which can be used to measure the sign
			int expectedSign = (array[i] - array[0]) / Math.abs(array[i] - array[0]);
			for(int j = 0; j < i; j++) {
				// Calculate the difference
				int diff = array[i] - array[j];
				// Check if the difference is of the expected sign and if adding it to our sequence will increase the cache value at that position
				if((diff / Math.abs(diff)) == expectedSign && (cache[j] + 1) > cache[i]) {
					// Increment the cache value at position i
					cache[i] = cache[j] + 1;
					// Change the expectedSign
					expectedSign *= -1;
				}
			}
		}

		// Find the max value from the cache array
		int max = 0;
		for(int i = 0; i < cache.length; i++)
			if(cache[i] > max)
				max = cache[i];

		// Print the cache array
		System.out.println(Arrays.toString(cache));

		// Return the result
		return max;
	}

	// Correct solution
	// We'll need two cache arrays to keep track of positive and negative sequence lengths
	public static int zigzagDPCorrect(int[] array) {

		// Print the result
		System.out.println(Arrays.toString(array));

		// Create the cache arrays
		int[] pos = new int[array.length];
		int[] neg = new int[array.length];

		// Initialize the arrays
		for(int i = 0; i < pos.length; i++) {
			pos[i] = 1;
			neg[i] = 1;
		}

		// Iteratively compute the length of the zigzag subsequence upto each element and store the result in the cache arrays
		for(int i = 1; i < array.length; i++) {
			for(int j = 0; j < i; j++) {
				// Get the difference
				int diff = array[i] - array[j];
				if(diff > 0 && neg[j] + 1 > pos[i])
					pos[i] = neg[j] + 1;
				else if(diff < 0 && pos[j] + 1 > neg[i])
					neg[i] = pos[j] + 1;
			}
		}

		// Find the max value from the two arrays
		int max = 0;
		for(int i = 0; i < pos.length; i++) {
			if(pos[i] > max) max = pos[i];
			if(neg[i] > max) max = neg[i];
		}

		// Print the cache array
		System.out.println(Arrays.toString(pos));
		System.out.println(Arrays.toString(neg));

		// Return the result
		return max;
	}

	// A O(N) solution to the ZigZag problem
	// Here, we encode the difference between the adjacent numbers as pos = 0 and neg = 1 and try to find the length of the longest run of alternating 1s and -1s
	public static int zigzagDPNSolution(int[] array) {

		// Print the array
		System.out.println(Arrays.toString(array));

		// Explicitly check for base case here
		if(array.length <= 0)
			return 0;
		if(array.length == 1)
			return 1;

		// Create the cache array of length N-1 to store the differences
		int[] cache = new int[array.length - 1];

		// Iteratively compute the difference between adjacent numbers and encode it into the cache array
		for(int i = 1; i < array.length; i++) {
			int diff = array[i] - array[i-1];
			if(diff > 0)
				cache[i-1] = 1;
			else if(diff < 0)
				cache[i-1] = -1;
			else if(diff == 0)
				cache[i-1] = 0;
		}

		// From the cache array find the longest run of alternating 1s and -1s
		ArrayList<Integer> result = new ArrayList<Integer>();
		int flag = cache[0];
		// We must ensure that adjecent numbers of equal value are not added to the run
		if(flag != 0)
			result.add(flag);
		for(int i = 0; i < cache.length; i++) {
			if(cache[i] != 0 && cache[i] != flag) {
				result.add(cache[i]);
				flag = cache[i];
			}
		}

		// We add +1 to the size of the result as we had computed the difference between adjacent numbers from the original input
		return result.size() + 1;
	}
}