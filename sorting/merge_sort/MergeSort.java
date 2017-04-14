import java.util.*;

public class MergeSort {

	public static void main(String[] args) {

		int[] test1 = randomArrayGenerator(10);
		//int[] test2 = randomArrayGenerator(50);

		System.out.println(Arrays.toString(mergeSort(test1)));
		//System.out.println(Arrays.toString(mergeSort(test2)));
	}

	public static int[] mergeSort(int[] array) {

		System.out.println(Arrays.toString(array));

		int[] aux = new int[array.length];
		mergeSort(array,aux,0,array.length - 1);

		return array;
	}

	public static void mergeSort(int[] array, int[] aux, int lo, int hi) {

		if(lo < hi) {
			int mid = lo + (hi - lo) / 2;
			mergeSort(array,aux,lo,mid);
			mergeSort(array,aux,mid+1,hi);
			merge(array,aux,lo,mid,hi);
		}
	}

	public static void merge(int[] array, int[] aux, int lo, int mid, int hi) {

		for(int k = lo; k <= hi; k++)
			aux[k] = array[k];

		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++) {
			if(i > mid) array[k] = aux[j++];
			else if(j > hi) array[k] = aux[i++];
			else if(aux[i] <= aux[j]) array[k] = aux[i++];
			else array[k] = aux[j++];
		}
	}

	public static int[] randomArrayGenerator(int size) {

		int[] arr = new int[size];
		for(int i = 0; i < size; i++)
			arr[i] = (int)(Math.random() * size + 1);

		return arr;
	}
}