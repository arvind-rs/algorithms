import java.util.*;

public class RecursiveInsertionSort {

	public static void sort(int[] a, int i) {
		if(i == 0) return;
		int key = a[i];
		sort(a,i - 1);
		int j = i - 1;
		while(j > -1 && a[j] > key) {
			a[j + 1] = a[j];
			j--;
		}
		a[j + 1] = key;
	}

	public static void main(String[] args) {

		int[] array = randomArrayGenerator(10);

		System.out.println(Arrays.toString(array));

		sort(array,array.length - 1);

		System.out.println(Arrays.toString(array));
	}

	public static int[] randomArrayGenerator(int size) {
		int[] arr = new int[size];
		for(int i = 0; i < size; i++)
			arr[i] = (int)(Math.random() * size + 1);
		return arr;
	}
}