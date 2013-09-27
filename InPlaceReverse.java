
/**
 * Implement reverse(list[]) that reverses the order of a list
 * without declaring any variables!
 * 
 * Also print the list without declaring any variables.
 * @author Rodrigo Alves
 *
 */
public class InPlaceReverse {

	public static void main(String[] args) {
		int[] list = {1, 2, 3, 4, 5, 6, 7};
		printList(list);
		reverse(list); //O(n).
		printList(list);
	}

	/** Reverses @param list without declaring any variables: */
	private static void reverse(int[] list) {
		if (list.length <= 1) return;
		reverse(list, 0);
	}
	
	/** Reverse helper function: */
	private static void reverse(int[] list, int i) {
		if (i >= list.length / 2) return;
		swap(list, i, list.length - i - 1);
		reverse(list, i+1); //Tail recursive. Stack memory should be constant.
	}

	/** Swap two elements of a list without declaring any variables: */
	private static void swap(int[] list, int index1, int index2) {
		list[index1] = list[index1] ^ list[index2]; //XOR
		list[index2] = list[index1] ^ list[index2];
		list[index1] = list[index1] ^ list[index2];
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
