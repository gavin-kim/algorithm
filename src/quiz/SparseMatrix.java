package quiz;

import java.util.HashMap;

/**
 * Purpose: To save space and time for matrix multiplication
 *
 *      matrix[n][m]       x[m]       y[n]
 *
 *   0 .90   0   0   0     .05       .036
 *   0   0 .36 .36 .18     .04       .297
 *   0   0   0 .90   0     .36   =   .333
 * .90   0   0   0   0     .37       .045
 * .47   0 .47   0   0     .19       .192
 *
 *
 * SparseMatrix[]
 *
 * 0 [{1, .09}]                      <- each row uses HashMap
 * 1 [{2, .36}, {3, .36}, {4, .18}]     (save space and O(n + non zero values)
 * 2 [{3, .90}]
 * 3 [{0, .90}]
 * 4 [{0, .45}, {2, .45}]
 *
 */
public class SparseMatrix {

    SparseVector[] sparseMatrix;

    public SparseMatrix(double[][] matrix) {
        sparseMatrix = new SparseVector[matrix[0].length];

        for (int row = 0; row < matrix.length; row++)
            for (int col = 0, len = matrix[row].length; col < len; col++)
                sparseMatrix[row].put(col, matrix[row][col]);
    }

    public double[] product(double[] that) {
        double[] result = new double[sparseMatrix.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = sparseMatrix[i].dot(that);
        }
        return result;
    }

    private class SparseVector {
        private HashMap<Integer, Double> vector = new HashMap<>();

        public int size() {
            return vector.size();
        }

        public void put(int k, double v) {
            vector.put(k, v);
        }

        public double get(int k) {
            return vector.getOrDefault(k, 0.0);
        }

        public double dot(double[] that) {
            double sum = 0.0;
            for (int k : vector.keySet())
                sum += that[k] * get(k);
            return sum;
        }
    }
}
