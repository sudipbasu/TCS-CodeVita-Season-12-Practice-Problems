import java.util.*;

public class SudokuValidator {
    static final int SIZE = 9;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] grid = new int[SIZE][SIZE];
        boolean[][] preFilled = new boolean[SIZE][SIZE];
        List<int[]> hints = new ArrayList<>();

        // Read the grid
        for (int i = 0; i < SIZE; i++) {
            String[] line = scanner.nextLine().split(" ");
            for (int j = 0; j < SIZE; j++) {
                int num = Integer.parseInt(line[j]);
                grid[i][j] = num;

                // Determine if the cell is a hint or pre-filled
                if (num % 10 == 0) { // Hints are trailing 0s
                    hints.add(new int[]{i, j, num / 10}); // Store row, column, and the hint value
                } else if (num / 10 == 0) { // Pre-filled cells
                    preFilled[i][j] = true;
                }
            }
        }

        // Read the allowed numbers for hints
        String[] allowedNumbersInput = scanner.nextLine().split(" ");
        Set<Integer> allowedNumbers = new HashSet<>();
        for (String numStr : allowedNumbersInput) {
            allowedNumbers.add(Integer.parseInt(numStr));
        }

        // Read K
        int K = Integer.parseInt(scanner.nextLine());

        // Store the indices of the cells that need modifications
        List<int[]> modifications = new ArrayList<>();
        boolean valid = validateGrid(grid, preFilled, allowedNumbers, modifications);

        // Determine the output based on the validation result
        if (modifications.isEmpty()) {
            System.out.println("Won");
        } else if (modifications.size() > K) {
            System.out.println("Impossible");
        } else {
            for (int[] pos : modifications) {
                System.out.println(pos[0] + " " + pos[1]);
            }
        }
    }

    private static boolean validateGrid(int[][] grid, boolean[][] preFilled,
                                        Set<Integer> allowedNumbers, List<int[]> modifications) {
        // Check for duplicates in rows, columns, and boxes
        boolean[][] rowSet = new boolean[SIZE][SIZE + 1];
        boolean[][] colSet = new boolean[SIZE][SIZE + 1];
        boolean[][] boxSet = new boolean[SIZE][SIZE + 1];

        // Check each cell in the grid
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int cellValue = grid[i][j];
                int boxIndex = (i / 3) * 3 + (j / 3); // Calculate box index

                if (cellValue != 0) {
                    // Check if it's a hint (trailing zero)
                    if (cellValue % 10 == 0) {
                        int hintValue = cellValue / 10; // Get actual hint value
                        if (!allowedNumbers.contains(hintValue)) {
                            modifications.add(new int[]{i, j}); // Hinted but wrong value
                        }
                    } else { // It's a filled cell
                        // Check for duplicates in row, column, and box
                        if (preFilled[i][j]) { // Ignore pre-filled cells for modifications
                            if (rowSet[i][cellValue] || colSet[j][cellValue] || boxSet[boxIndex][cellValue]) {
                                modifications.add(new int[]{i, j}); // Duplicate found
                            } else {
                                rowSet[i][cellValue] = true;
                                colSet[j][cellValue] = true;
                                boxSet[boxIndex][cellValue] = true;
                            }
                        } else { // Not pre-filled
                            if (rowSet[i][cellValue] || colSet[j][cellValue] || boxSet[boxIndex][cellValue]) {
                                modifications.add(new int[]{i, j}); // Duplicate found
                            } else {
                                rowSet[i][cellValue] = true;
                                colSet[j][cellValue] = true;
                                boxSet[boxIndex][cellValue] = true;
                            }
                        }
                    }
                }
            }
        }
        return modifications.isEmpty();
    }
}
