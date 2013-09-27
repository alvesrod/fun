
/**
 * Rotate a square matrix to the right.
 * @author Rodrigo Alves
 */

public class RotateMatrix {
	
	public static void main(String[] args) {
	
		int[][] matrix = {{101, 102, 103, 104, 105}, 
						  {106, 107, 108, 109, 110}, 
						  {111, 112, 113, 114, 115},
						  {116, 117, 118, 119, 120},
						  {121, 122, 123, 124, 125}};
		
		printMatrix(matrix);		
		rotateRight(matrix);
		printMatrix(matrix);
	}

	/**
	 * Rotates the @param matrix to the right.
	 * Time complexity: quadratic. Memory: constant.
	 */
	private static void rotateRight(int[][] matrix) {
		if (matrix.length == 1) return;
		final int size = matrix[0].length;
		final int rowSize = matrix.length;
		if (size != rowSize) return;
		
		for (int j = 0; j < size/2; j++)
			for (int i = j; i < size - 1 - j; i++) {
				int temp = matrix[j][i];
				matrix[j][i] = matrix[size - 1 - i][j];
				matrix[size - 1 - i][j] = matrix[size-1 - j][size - 1 - i];
				matrix[size - 1 - j][size - 1 - i] = matrix[i][size - 1 - j];
				matrix[i][size-1 - j] = temp;
			}
	}

	private static void printMatrix(int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[0].length ; column++)
				System.out.print(" " + matrix[row][column]);
			System.out.println("");
		}
		System.out.println("");
	}

}
