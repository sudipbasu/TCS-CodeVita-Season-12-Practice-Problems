import java.util.*;

class BoardGames {

    static class Cell {
        int x, y, moves;

        Cell(int x, int y, int moves) {
            this.x = x;
            this.y = y;
            this.moves = moves;
        }
    }

    public static int minMoves(int[][] grid, int[] source, int[] destination, int[] moveRule) {
        int M = grid.length;
        int N = grid[0].length;

        // Direction vectors for the four movements (forward, right, left, backward)
        int[][] directions = {
            {moveRule[0], moveRule[1]},       // Forward
            {moveRule[1], -moveRule[0]},      // Right (90 degrees clockwise)
            {-moveRule[1], moveRule[0]},      // Left (90 degrees counterclockwise)
            {-moveRule[0], -moveRule[1]}      // Backward (180 degrees rotation)
        };

        // Visited array to prevent revisiting nodes
        boolean[][] visited = new boolean[M][N];
        Queue<Cell> queue = new LinkedList<>();

        // Add the source to the queue
        queue.add(new Cell(source[0], source[1], 0));
        visited[source[0]][source[1]] = true;

        // Perform BFS
        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            // If we reach the destination, return the number of moves
            if (current.x == destination[0] && current.y == destination[1]) {
                return current.moves;
            }

            // Explore all four possible movements
            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                // Check if the new position is within the grid and not visited, and it's traversable (value 0)
                if (isValid(newX, newY, M, N, grid, visited)) {
                    visited[newX][newY] = true;
                    queue.add(new Cell(newX, newY, current.moves + 1));
                }
            }
        }

        // If no path is found, return -1
        return -1;
    }

    // Helper function to check if a cell is valid
    private static boolean isValid(int x, int y, int M, int N, int[][] grid, boolean[][] visited) {
        return x >= 0 && x < M && y >= 0 && y < N && grid[x][y] == 0 && !visited[x][y];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read grid dimensions
        int M = sc.nextInt();
        int N = sc.nextInt();
        int[][] grid = new int[M][N];

        // Read the grid values
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // Read source and destination coordinates
        int[] source = {sc.nextInt(), sc.nextInt()};
        int[] destination = {sc.nextInt(), sc.nextInt()};

        // Read the move rule
        int[] moveRule = {sc.nextInt(), sc.nextInt()};

        // Get the minimum moves required
        int result = minMoves(grid, source, destination, moveRule);

        // Print the result
        System.out.println(result);
    }
}
