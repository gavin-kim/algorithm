package quiz;

import java.util.HashMap;
import java.util.Map;

public class Tries {

    Node root = new Node();

    class Node {
        int count = 0;
        Map<Character, Node> children = new HashMap<>();
    }

    // O(word.length())
    void add(String word) {

        Node node = root;
        char[] letters = word.toCharArray();
        int i = 0;

        while (node.children.containsKey(letters[i])) {
            node = node.children.get(letters[i++]);
            node.count++;
        }

        for (int j = i; j < letters.length; j++) {
            Node child = new Node();
            child.count = 1;
            node.children.put(letters[j], child);
            node = child;
        }
    }

    int find(String word) {
        Node node = root;

        for (char letter : word.toCharArray()) {
            node = node.children.get(letter);

            if (node == null) break;
        }

        return node == null ? 0 : node.count;
    }
}
