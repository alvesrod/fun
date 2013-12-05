/**
 * Fun time: Implement a Merge sort algorithm that uses only final variables. 
 * For simplicity, assume the input size is a multiple of 2.
 * @author Rodrigo Alves
 */

public class RunMergeSort {

	public static void main(String[] args) {
		sortResults();
	}
	
	private static void sortResults() {
		final int[] array = {1, 5, 3, 18, 55, -5, -10, 3, 1, 2, 9, 1, 100, 53, 2, 3};
		final int[] expected = { -10, -5, 1, 1, 1, 2, 2, 3, 3, 3, 5, 9, 18, 53, 55, 100};
		MergeSort.sort(array);
		
		System.out.print("Received: ");
		printList(array);
		
		System.out.print("\nExpected: ");
		printList(expected);
		
	}
		
	/** Receives a list (@param list) and prints it in the console: */
	private static void printList(final int[] list) {
		System.out.print("[ ");
		printList(list, list.length - 1);
		System.out.println("]");
	}

	/** Helper function to print the list: */
	private static void printList(final int[] list, final int length) {
		if (length > 0)
			printList(list, length-1); //Consumes stack memory: O(length)
		System.out.print(list[length] + " ");
	}

}
