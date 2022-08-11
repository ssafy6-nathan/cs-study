import java.util.Arrays;

public class BubbleSort {

	static int [] arr = {8,3,2,7,5};
	
	public static void main(String[] args) {
	
		for(int i = 1; i < arr.length; i++) {
			
			for(int j = 0; j < arr.length-i; j++) {
				if(arr[j] > arr[j+1]) 
					swap(j, j+1);
			}
			
		}
		System.out.println(Arrays.toString(arr));
	}

	private static void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
