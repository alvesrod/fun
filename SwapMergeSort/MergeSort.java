/**
 * Fun time: Implement a Merge sort algorithm that uses only final variables. 
 * For simplicity, assume the input size is a multiple of 2.
 * @author Rodrigo Alves
 */

public class MergeSort {

	public static void sort(final int[] list) {
		if (!multOf2(list.length))
			System.out.println("This sorting alg is only for multiples of 2");
		else
			mergeSort(list, 0, list.length - 1);
	}
	
	private static void mergeSort(final int[] list, final int start, final int end) {
		if (start >= end) return;
		mergeSort(list, start, half(start, end));
		mergeSort(list, half(start, end) + 1, end);
		merge(list, start, end);
	}
	
	private static boolean multOf2(final int value) {
		return (largestMultiple2(value, 2) == value);
	}
	
	private static int largestMultiple2(final int n, final int largest) {
		if (largest > n) return largest / 2;
		return largestMultiple2(n, largest*2);
	}
	
	private static void merge(final int[] list, final int start, final int end) {
		if (start >= end) return; //1 element is sorted
		reverse(list, half(start, end) + 1, end);
		bitonicMerge(list, start, end);
	}
	
	private static void bitonicMerge(final int[] list, final int start, 
													   final int end) {
		if (start >= end) return; //1 element is sorted
		swapWithHalf(list, start, half(start, end) + 1);
		bitonicMerge(list, start, half(start, end));
		bitonicMerge(list, half(start, end) + 1, end);
	}
	
	private static int half(final int start, final int end) {
		return (start + end) / 2;
	}
	
	private static void swapWithHalf(final int[] list, final int start, 
													   final int end) {
		swapWithHalf(list, start, end, end - start);
	}

	private static void swapWithHalf(final int[] list, final int start, 
									 final int end, final int i) {
		if (i <= 0) return;
		swapIfBigger(list, start, end);
		swapWithHalf(list, start + 1, end + 1, i - 1);
	}
	
	private static void reverse(final int[] list, final int start, final int end) {
		if ((end - start) < 1) return;
		swap(list, start, end);
		reverse(list, start + 1, end - 1);
	}
	
	private static void swapIfBigger(final int[] list, final int index1, 
													   final int index2) {
		if (list[index1] > list[index2])
			swap(list, index1, index2);
	}
	
	/** Swap two elements of a list without declaring any variables: */
	private static void swap(final int[] list, final int index1, final int index2) {
		list[index1] = list[index1] ^ list[index2]; //XOR
		list[index2] = list[index1] ^ list[index2];
		list[index1] = list[index1] ^ list[index2];
	}

}
