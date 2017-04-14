import java.util.*;

public class InsertionSort {

	public static void sort(int[] array) {

		if(array == null || array.length < 2)
			return;

		for(int j = 1; j < array.length; j++) {
			int i = j - 1;
			int key = array[j];
			while(i > -1 && array[i] > key) {
				array[i + 1] = array[i];
				i -= 1;
			}
			//System.out.println(j + ";" + i);
			array[i + 1] = key;
		}
	}

	public static void reverseSort(int[] array) {
		if(array == null || array.length < 2)
			return;

		for(int j = 1; j < array.length; j++) {
			int i = j - 1;
			int key = array[j];
			while(i > -1 && array[i] < key) {
				array[i + 1] = array[i];
				i -= 1;
			}
			//System.out.println(j + ";" + i);
			array[i + 1] = key;
		}
	}

	public static void main(String[] args) {

		int[] array = randomArrayGenerator(10);

		System.out.println(Arrays.toString(array));

		sort(array);

		System.out.println(Arrays.toString(array));

		reverseSort(array);

		System.out.println(Arrays.toString(array));
	}

	public static int[] randomArrayGenerator(int size) {
		int[] arr = new int[size];
		for(int i = 0; i < size; i++)
			arr[i] = (int)(Math.random() * size + 1);
		return arr;
	}
}