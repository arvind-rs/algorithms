
/*
 * Quick select algorithm to find the kth smallest element from an unordered array.
 * @author: arvind-rs
 * @date: 01/03/2018
 */

import java.util.*;

class QuickSelect {
	public static void main(String[] args) {
		// Test case
		int[] array = generateRandomArray(10);
		int k = 1;
		System.out.println(Arrays.toString(array) + " " + k);
		System.out.println(find(array, k, 0, array.length - 1));
	}

	// Quick select function. TC = O(n), SC = O(logn)
	public static int find(int[] array, int k, int lo, int hi) {
		if(lo < hi) {
			int pivot = partition(array, lo, hi);
			if(k < pivot) return find(array, k, lo, pivot - 1);
			else if(k > pivot) return find(array, k, pivot + 1, hi);
			else return array[k];
		}
		return array[k];
	}

	// Helper function to place a pivot element into it's correct position
	private static int partition(int[] array, int lo, int hi) {
		int i = lo, j = hi + 1;
		while(true) {
			while(array[++i] < array[lo])
				if(i == hi) break;
			while(array[lo] < array[--j])
				if(j == lo) break;
			if(i >= j) break;
			swap(array, i, j);
		}
		swap(array, j, lo);
		return j;
	}

	// Helper function to swap elements in an array
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	// Helper function to generate a random array
	public static int[] generateRandomArray(int size) {
		int[] array = new int[size];
		for(int i = 0; i < size; i++)
			array[i] = (int)(Math.random() * size + 1);
		return array;
	}
}