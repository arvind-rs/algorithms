import java.util.*;

public class SelectionSort {

	public static void sort(int[] a) {
		int n = a.length;
		for(int i = 0; i < n - 1; i++) {
			int min = i;
			for(int j = i + 1; j < n; j++) {
				if(a[j] <= a[min])
					min = j;
			}
			swap(a,i,min);
		}
	}

	private static void swap(int[] a, int i, int j) {
		/*a[i] = a[i] ^ a[j];
		a[j] = a[i] ^ a[j];
		a[i] = a[i] ^ a[j];*/

		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {

		//int[] array = randomArrayGenerator(10);
		int[] array = {6, 6, 7, 9, 10, 10, 9, 9, 1, 8};

		System.out.println(Arrays.toString(array));

		sort(array);

		System.out.println(Arrays.toString(array));
	}

	public static int[] randomArrayGenerator(int size) {
		int[] arr = new int[size];
		for(int i = 0; i < size; i++) 
			arr[i] = (int)(Math.random() * size + 1);
		return arr;
	}
}