package sortAlgorithm;

import java.util.Arrays;

public class SelectionSort {

	static int [] arr = {8,3,2,7,5};
	
	public static void main(String[] args) {

		for(int i = 0; i < arr.length-1; i++) {
			int least = i;
			for(int j = i+1; j < arr.length; j++) {
				if(arr[least] > arr[j])
					least = j;
			}
			swap(i, least);
			
		}
		System.out.println(Arrays.toString(arr));		
	}
	private static void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

}
