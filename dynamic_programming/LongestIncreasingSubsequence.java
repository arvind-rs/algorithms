import java.util.*;

/*
* A recursive and DP solution to the Longest Increasing Subsequence problem.
* Author: ArvindRS
* Date: 04/22/2017
*/

public class LongestIncreasingSubsequence {

	// Main function
	public static void main(String[] args) {

		// Test cases
		int[] test_1 = {2,5,3,4,6};
		int[] test_2 = {2,5,3};
		int[] test_3 = {2,4,3,5,1,7,6,9,8};

		// Calling the wrapper function for the recursive LIS
		System.out.println(wrapperLIS(test_1));
		System.out.println(wrapperLIS(test_2));
		System.out.println(wrapperLIS(test_3));

		// Calling the dynamic solution to LIS
		System.out.println(dynamicLIS(test_1));
		System.out.println(dynamicLIS(test_2));
		System.out.println(dynamicLIS(test_3));
	}

	// Wrapper function for the recursive LIS solution
	// Interestingly, this is actually a bottom-up evaluation in the guise of being top-down recursive
	public static int wrapperLIS(int[] array) {

		// Print the input array
		System.out.println(Arrays.toString(array));

		// Variable to keep track of the maximum value
		int max = 0;
		for(int i = 0; i < array.length; i++) {
			int result = recursiveLIS(array, i, i+1, 1);
			//System.out.println(result);
			if(result > max)
				max = result;
		}

		// Return the max value
		return max;
	}

	// Private recursive function to find the LIS from the given array
	private static int recursiveLIS(int[] array, int i, int j, int count) {

		// Base case
		//if(j >= array.length)
		//	return count;
		// We don't need an explicit base case as the below logic captures the base case

		// Intialize the max value with current count 
		int max = count;
		// Iteratively check for the next number to add to the subsequence and call the recursive function on the new subsequence to get the max value
		for(int k = j; k < array.length; k++) {
			if(array[i] < array[k]) {
				// This is wrong as, if we increment count here then the next iteration of the loop will have an increased count, leading to wrong results
				//count += 1;
				int result = recursiveLIS(array, k, k+1, count + 1);
				if(result > max)
					max = result;
			}
		}

		// Return the max value
		return max;
	}

	// Public function to solve the LIS problem using dynamic programming
	public static int dynamicLIS(int[] array) {

		// Print the array
		System.out.println(Arrays.toString(array));

		// Create the cache matrix
		int[] cache = new int[array.length];

		// Initialize the cache matrix
		for(int i = 0; i < cache.length; i++)
			cache[i] = 1;

		// Iteratively find the length of the increasing subsequences and store the result in the cache
		for(int i = 1; i < array.length; i++) {
			for(int j = 0; j < i; j++) {
				if(array[j] < array[i] && cache[j] + 1 > cache[i])
					cache[i] = cache[j] + 1;
			}
		}

		// Find the max value in the cache
		int max = 0;
		for(int i = 0; i < cache.length; i++) {
			if(cache[i] > max)
				max = cache[i];
		}

		// Return the max value
		return max;
	}
}