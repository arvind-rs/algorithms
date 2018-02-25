
/*
 * Counting sort implementation in Java.
 * @author: ArvindRS
 * @date: 12/23/2017
 */

import java.util.*;

class CountingSort {
	public static void main(String[] args) {
		// Test cases
		int[] test1 = generateRandomArray(10);

		System.out.println(Arrays.toString(sort(test1)));
	}

	// TC = O(n + m), SC = O(m) where m is the maximum element in the array
	public static int[] sort(int[] array) {
		if(array == null || array.length == 0) return array;
		System.out.println(Arrays.toString(array));

		int max = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] > max) 
				max = array[i];
		}

		int[] buckets = new int[max+1];
		for(int i = 0; i < array.length; i++)
			buckets[array[i]]++;

		for(int i = 0, j = 0; i < buckets.length; i++) {
			while(buckets[i] > 0) {
				buckets[i]--;
				array[j++] = i;
			}
		}

		return array;
	}

	// Helper function to generate an array of random numbers
	public static int[] generateRandomArray(int size) {
		int[] array = new int[size];
		for(int i = 0; i < array.length; i++)
			array[i] = (int)(Math.random() * size + 1);
		return array;
	}
}
