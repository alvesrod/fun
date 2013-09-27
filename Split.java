
/**
 * Implement a split function that splits an array into two halves. You must not use variables.
 * Extra: Also implement a printList function that doesn't use any variables.
 * @author Rodrigo Alves
 */

public class Split {

	public static void main(String[] args) {
		final int[] list = {1, 2, 3, 4, 5, 6, 7};
		
		/* 
		 * The problem is to implement the split function.
		 * So, it shouldn't matter if I declare variables outside it.
		 * In fact, I need to put the result somewhere!
		 */
		int[] half1 = new int[ (int) (list.length / 2.0 + 0.5) ];
		int[] half2 = new int[list.length / 2];

		/* Split the halves: */
		split(list, half1, half2);
		
		/* Print the results: */
		printList(list);  //Output: [ 1 2 3 4 5 6 7 ]
		printList(half1); //Output: [ 1 3 5 7 ]
		printList(half2); //Output: [ 2 4 6 ]
	}

	/**
	 * Splits the list into 2 halves. Time complexity: O(n).
	 * @param list: the original list.
	 * @param half1, half2: the 2 halves with half of the list in each.
	 */
	private static void split(final int[] list, final int[] half1, final int[] half2) {
		split(list, half1, half2, list.length - 1);
	}
	
	/** Helper function to split halves */
	private static void split(final int[] list, final int[] half1, final int[] half2, final int i) {
		if (i < 0) return;
		half1[i/2] = list[i];
		split(list, half2, half1, i-1);  //Tail recursion. It shouldn't consume the stack.
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
