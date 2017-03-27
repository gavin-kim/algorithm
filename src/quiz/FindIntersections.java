package quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindIntersections {

    /*
        Get intersection numbers in the 2 lists

        For example, listA = [3, 4, 5, 2, 6], listB = [2, 8, 4, 7, 3]

        listA n listB = [2, 3]
    */

    public static List<Integer> solve(List<List<Integer>> lists) {
        Map<Integer, Integer> countOccurs = new HashMap<>();
        final int numOfLists = lists.size();

        for (int k : lists.get(0))
            countOccurs.put(k, 1);

        for (int i = 1;  i < numOfLists; i++) {

            for (int k : lists.get(i))
                if (countOccurs.containsKey(k) && countOccurs.get(k) == i)
                    countOccurs.put(k, i + 1);
        }

        List<Integer> result = new ArrayList<>();

        countOccurs.forEach((k, v) -> {
            System.out.println(k + ", " + v);
            if (v == numOfLists) result.add(k);
        });

        return result;
    }
}
