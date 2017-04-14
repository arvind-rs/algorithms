import java.util.*;

public class Quicksort {

	public static void main(String[] args) {

		int[] test1 = randomArrayGenerator(10);

		System.out.println(Arrays.toString(sort(test1)));
	}

	public static int[] sort(int[] array) {

		System.out.println(Arrays.toString(array));
		sort(array,0,array.length - 1);

		return array;
	}

	public static void sort(int[] array, int lo, int hi) {

		if(lo < hi) {
			int correctlyPlaced = partition(array,lo,hi);
			sort(array,lo,correctlyPlaced - 1);
			sort(array,correctlyPlaced + 1,hi);
		}
	}

	public static int partition(int[] array, int lo, int hi) {

		int i = lo, j = hi + 1;
		while(true) {
			while(array[++i] < array[lo])
				if(i == hi) break;
			while(array[lo] < array[--j])
				if(j == lo) break;
			if(i >= j) break;
			swap(array,i,j);
		}

		swap(array,lo,j);
		return j;
	}

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static int[] randomArrayGenerator(int size) {
		int[] arr = new int[size];
		for(int k = 0; k < size; k++)
			arr[k] = (int)(Math.random() * size + 1);
		return arr;
	}
}