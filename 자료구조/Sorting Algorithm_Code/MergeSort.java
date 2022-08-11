package sortAlgorithm;

import java.util.Arrays;

public class MergeSort {

	static int [] arr = {21, 10, 12, 20, 25, 13, 15, 22};
	static int [] answer;

	public static void main(String[] args) {

		answer = new int[arr.length];
		merge(0, arr.length-1);
		System.out.println(Arrays.toString(arr));
		
	}
	public static void merge(int start, int end) {
        
        if (start < end) {

            int mid = (start + end) / 2;
            merge(start, mid);
            merge(mid + 1, end);

            int p = start;
            int q = mid + 1;
            int idx = p;

            while (idx <= end) {

                if (q > end || (p <= mid && arr[p]<arr[q])) {
                    answer[idx++] = arr[p++];

                } else {
                    answer[idx++] = arr[q++];
                }

            }

            for (int i = start; i <= end; i++) {
                arr[i]=answer[i];
            }

        }

	}
}