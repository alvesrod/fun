import java.util.ArrayList;
import java.util.Stack;

/** 
 *  Challenge: Write code to calculate the number of ways of representing n cents.
 * 			   You should also print all the possible ways.
 *  @author: Rodrigo Alves
 *  
 *  
 *  Sample output (n = 16):
    # of ways to represent 16: 6
	[ 10 5 1 ]
	[ 10 1 1 1 1 1 1 ]
	[ 5 5 5 1 ]
	[ 5 5 1 1 1 1 1 1 ]
	[ 5 1 1 1 1 1 1 1 1 1 1 1 ]
	[ 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 ]
 *  
 */


public class PrintCoinCombinations {
	
	private final static int PENNY = 1;
	private final static int NICKEL = 5;
	private final static int DIME = 10;
	private final static int QUARTER = 25;
	
	public static void main(String args[]) {
		getWays(30);
	}
	
	/**
	 * Print in the console all possible ways to represent n cents.
	 * @return the number of ways.
	 */
	private static int getWays(int n) {
		ArrayList<ArrayList<Integer>> ways = new ArrayList<ArrayList<Integer>>();
		int count = getWays(n, 25, ways, null);
		System.out.println("# of ways to represent " + n + ": " + count);
		printWays(ways);
		return count;
	}
	
	private static int getWays(int val, int max, 
			ArrayList<ArrayList<Integer>> ways, Stack<Integer> buffer) {
		
		boolean firstTime = (buffer == null);
		
		if (firstTime) {
			buffer = new Stack<Integer>();
			if (val <= 0) return 0;
		}
		else {
			buffer.push(max);
			
			if (val < 0) {  //value cannot be represented
				buffer.pop();
				return 0;
			}
			
			if (val == 0) { //value can be represented. Add to list.
				ways.add(new ArrayList<Integer>(buffer));
				buffer.pop();
				return 1;
			}
		}
		
		int sum = 0;

		switch (max) {
		
		/* You can add more coins here */
		
		case QUARTER:
			sum += getWays(val - QUARTER, QUARTER, ways, buffer);
		case DIME:
			sum += getWays(val - DIME, DIME, ways, buffer);
		case NICKEL:
			sum += getWays(val - NICKEL, NICKEL, ways, buffer);
		case PENNY:
			sum += getWays(val - PENNY, PENNY, ways, buffer);
			
		}

		if (!firstTime)
			buffer.pop();
		
		return sum;
	}
	
	private static void printWays(ArrayList<ArrayList<Integer>> ways) {
		for (ArrayList<Integer> way : ways) {
			System.out.print("[ ");
			for (Integer coin : way)
				System.out.print(coin + " ");
			System.out.println("]");
		}
	}
	
}