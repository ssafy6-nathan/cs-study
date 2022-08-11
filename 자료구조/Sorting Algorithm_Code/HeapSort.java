package sortAlgorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HeapSort {

	static int [] arr = {21,10,12,20,25,13,15,22};
	
	public static void main(String[] args) {

		PriorityQueue<Integer> heap = new PriorityQueue<>();
		
		for(int i = 0; i < arr.length; i++) {
			heap.add(arr[i]);
		}
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = heap.poll();
		}
		
		System.out.println(Arrays.toString(arr));
	}

}
