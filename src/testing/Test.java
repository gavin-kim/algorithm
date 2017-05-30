package testing;


import dynamic.CoinChangeMin;
import dynamic.CutRod;
import dynamic.MatrixChainMultiplication;
import quiz.FindMinMeetingRooms;
import recursion.CountPaths;
import recursion.Memoization;
import recursion.Staircase;

import static recursion.CountPaths.grid;
import static recursion.CountPaths.grid2;

public class Test {

    private static final int ARRAY_SIZE = 100;
    private static final int RANGE = 1000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static final String RESOURCE_ROOT = "resources/data/";

    public static void main(String[] args) throws Exception {
        MatrixChainMultiplication.solve(MatrixChainMultiplication.DIMENSIONS);
    }

}



