package recursion;

public class CountPaths {

    /**
     *  count moves from (0, 0) to the (h-1, w-1)
     */
    public static int countPaths(boolean[][] grid) {
        return countPaths(grid, 0, 0, new int[MAX_ROW][MAX_COL]);
    }

    private static int countPaths(boolean[][] grid, int row, int col,
                                 int[][] paths) {
        if (!isValidPath(grid, row, col)) return 0;
        else if (isAtEnd(grid, row, col)) return 1;
        else if (paths[row][col] == 0) // recursion only unvisited path
            paths[row][col] = countPaths(grid, row + 1, col, paths) +
                countPaths(grid, row, col + 1, paths);

        return paths[row][col];
    }

    private static boolean isValidPath(boolean[][] grid, int row, int col) {
        return MAX_ROW > row && MAX_COL > col && grid[row][col];
    }

    private static boolean isAtEnd(boolean[][] grid, int row, int col) {
        return MAX_ROW == row + 1 && MAX_COL == col + 1;
    }

    private static int MAX_ROW = 8;
    private static int MAX_COL = 8;

    public static boolean[][] grid = {
        {true, true, true, true, true, true, true, true},
        {true, true, false, true, true, true, false, true},
        {true, true, true, true, false, true, true, true},
        {false, true, false, true, true, false, true, true},
        {true, true, false, true, true, true, true, true},
        {true, true, true, false, false, true, false, true},
        {true, false, true, true, true, false, true, true},
        {true, true, true, true, true, true, true, true},
    };

    public static boolean[][] grid2 = {
        {true, true, true, true, true, true, true, true},
        {false, false, false, false, false, false, false, true},
        {false, false, false, false, false, false, false, true},
        {false, false, false, false, false, false, false, true},
        {false, false, false, false, false, false, false, true},
        {false, false, false, false, false, false, false, true},
        {false, false, false, false, false, false, false, true},
        {false, false, false, false, false, false, false, true},
    };
}
