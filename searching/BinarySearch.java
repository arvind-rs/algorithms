import java.util.*;

public class BinarySearch {

	public static void main(String[] args) {

		int[] test1 = randomArrayGenerator(100);
		int k = 6;

		Arrays.sort(test1);

		System.out.println(Arrays.toString(test1));
		System.out.println(k);
		System.out.println(search(test1,0,test1.length - 1,k));
	}

	public static int[] randomArrayGenerator(int size) {

		int[] arr = new int[size];
		for(int i = 0; i < size; i++)
			arr[i] = (int)(Math.random() * size + 1);
		return arr;
	}

	public static int search(int[] array,int lo, int hi,int key) {

		int mid = lo + (hi - lo) / 2;
		if(array[mid] == key) return mid;
		else if(key < array[mid]) return search(array,lo,mid,key);
		else return search(array,mid+1,hi,key);
	}
}