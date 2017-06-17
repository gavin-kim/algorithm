package dynamic;
/**
 * Alice is a kindergarten teacher. She wants to give some candies to the children in her class.
 * All the children sit in a line (their positions are fixed), and each of them has a rating score
 * according to his or her performance in the class.  Alice wants to give at least 1 candy to each child.
 * If two children sit next to each other, then the one with the higher rating must get more candies.
 * Alice wants to save money, so she needs to minimize the total number of candies given to the children.
 * */
public class Candies {
    public static long solve(int[] ratings) {
        int[] memo = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++)
            memo[i] = 1;

        for (int i = 1; i < ratings.length; i++) {     // traverse left to right
            if (ratings[i - 1] < ratings[i])
                memo[i] = memo[i - 1] + 1;
        }

        for (int i = ratings.length - 1; i > 0; i--) { // traverse right to left
            if (ratings[i - 1] > ratings[i] && memo[i - 1] <= memo[i])
                memo[i - 1] = memo[i] + 1;
        }

        long sum = 0;

        for (int i = 0; i < ratings.length; i++)
            sum += memo[i];

        return sum;
    }
}
