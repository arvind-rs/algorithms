
/*
 * A simple java implementation to the Binary Search Algorithm
 * @author: ArvindRS
 * @date: 07/13/2017
 */

import java.util.*;

public class BinarySearch {
	public static void main(String[] args) {
		// Test cases
		int[] test1 = getRandomInputArray(10);

		Arrays.sort(test1);

		// Solution
		System.out.println(search(test1,6));
	}

	public static int search(int[] input, int k) {
		// Check for edge cases
		if(input.length <= 0) return -1;

		System.out.println(Arrays.toString(input));
		System.out.println(k);

		int lo = 0;
		int hi = input.length - 1;

		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2; // To avoid integer overflow
			if(input[mid] == k) return mid;
			else if(input[mid] > k) hi = mid;
			else lo = mid;
		}

		// Didn't find the element
		return -1;
	}

	public static int[] getRandomInputArray(int size) {
		int[] arr = new int[size];
		for(int i = 0; i < arr.length; i++)
			arr[i] = (int)(Math.random() * size + 1);
		return arr;
	}
}