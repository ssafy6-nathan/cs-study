package sortAlgorithm;

import java.util.Arrays;

public class QuickSort {

	static int [] arr = {21, 10, 12, 20, 25, 13, 15, 22};
	
	public static void main(String[] args) {
		sort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
		
    }

    private static void sort(int[] arr, int low, int high) {
        if (low >= high) 
        	return;

        int mid = partition(arr, low, high);
        sort(arr, low, mid - 1);
        sort(arr, mid, high);
        
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[(low + high) / 2];
        
        while (low <= high) {
        
        	while (arr[low] < pivot) low++;
            
        	while (arr[high] > pivot) high--;
            
        	if (low <= high) {
                swap(low, high);
                low++;
                high--;
            }

        }
        return low;
    }

    private static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
