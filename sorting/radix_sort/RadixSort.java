
/*
 * Radix sort implementation in Java.
 * @author: ArvindRS
 * @date: 12/23/2017
 */

import java.util.*;

class RadixSort {
	public static void main(String[] args) {
		// Test cases
		int[] test1 = {456,678,2345,61,2,489,590,1234,567,890};

		System.out.println(Arrays.toString(sort(test1)));
	}

	// TC = O(nk) where k is the number of digits in the maximum number in the array
	// SC = O(n)
	public static int[] sort(int[] array) {
		if(array == null || array.length == 0) return array;
		System.out.println(Arrays.toString(array));

		// Find the biggest number
		int max = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] > max)
				max = array[i];
		}
		// Find the number of digits in the biggest number
		// Using String's toCharArray() to make it easier here
		int k = (max + "").toCharArray().length;

		// Hard-coding 10 at only one place
		int radixCount = 10;
		// Create an array of Queues. No need to initialize with Object[], directly using LinkedList[] works
		Queue<Integer>[] buckets = new LinkedList[radixCount];
		for(int i = 0; i < 10; i++)
			buckets[i] = new LinkedList<Integer>();
		// Sort 
		for(int i = 0; i < k; i++) {
			for(int j = 0; j < array.length; j++) {
				// The index is the digit at the specfied position in the number
				int index = getIndex(array[j], i);
				// Add the number to the queue at the specified index
				buckets[index].add(array[j]);
			}
			// Dequeue the queue in order
			for(int j = 0, l = 0; j < buckets.length; j++) {
				while(!buckets[j].isEmpty()) {
					// No need to cast the element returned by queue().remove() as it's redundant
					array[l++] = buckets[j].remove();
				}
			}
		}
		return array;
	}

	// Helper function to get the digit of a number at a specified position
	private static int getIndex(int number, int i) {
		number /= Math.pow(10, i);
		return number % 10;
	}
}