package quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayInterSection {

    /*
        Get intersection numbers in the 2 lists

        For example, listA = [3, 4, 5, 2, 6], listB = [2, 8, 4, 7, 3]

        listA n listB = [2, 3]
    */
    public static List<Integer> getIntersections(List<Integer> listA, List<Integer> listB) {
        Map<Integer, Integer> countOccurs = new HashMap<>();
        int numOfLists = 2;

        for (int k : listA) {
            if (countOccurs.containsKey(k))
                countOccurs.put(k, countOccurs.get(k) + 1);
            else
                countOccurs.put(k, 1);
        }

        for (int k : listB) {
            if (countOccurs.containsKey(k))
                countOccurs.put(k, countOccurs.get(k) + 1);
            else
                countOccurs.put(k, 1);
        }

        List<Integer> result = new ArrayList<>();
        countOccurs.forEach((k, v) -> {
            if (v == 2) result.add(k);
        });

        return result;
    }

    // get intersection numbers in lists
    public static List<Integer> getIntersections(List<List<Integer>> lists) {
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
