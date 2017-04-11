package quiz;

import java.util.*;

/**
 * To provide searching items by a key or value
 *
 * e.g. Movie1,Kim,Lee,Park...
 *      Movie2,Yong,Kim,Lee,Kang...
 */
public class LookupIndex {

    public static void solve() {
        Scanner input = new Scanner(System.in);
        String separator = ",";

        Map<String, Queue<String>> table = new HashMap<>();
        Map<String, Queue<String>> invertedTable = new HashMap<>();

        // store key-value and value-key
        while (input.hasNextLine()) {
            String[] tokens = input.nextLine().split(separator);
            String key = tokens[0];

            for (int i = 1; i < tokens.length; i++) {
                String value = tokens[i];

                if (table.containsKey(key))
                    table.put(key, new LinkedList<>());
                if (invertedTable.containsKey(value))
                    invertedTable.put(value, new LinkedList<>());

                table.get(key).offer(value);
                invertedTable.get(value).offer(key);
            }
        }
    }
}
