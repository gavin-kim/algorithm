package quiz;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileIndex {

    /** create [key: word - value: Set<File>] index table. the word is in the file */
    public static HashMap<String, Set<File>> createIndexTable(String[] filenames)
        throws IOException {

        HashMap<String, Set<File>> indexTable = new HashMap<>();

        for (String filename : filenames) {
            File file = new File(filename);
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                String word = input.next();
                if (!indexTable.containsKey(word))
                    indexTable.put(word, new HashSet<>());
                // add a file associated with the word
                indexTable.get(word).add(file);
            }
        }
        return indexTable;
    }

    /** get files that has a specific word */
    public static File[] searchWord(Map<String, Set<File>> indexTable, String query) {
        return indexTable.get(query).toArray(new File[0]);
    }
}
