package testing;

import dynamic.SumOfSubstrings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

public class Test {

    private static final int ARRAY_SIZE = 100;
    private static final int RANGE = 1000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static final String RESOURCE_ROOT = "resources/data/";

    public static void main(String[] args) throws Exception {
        Node node1 = new Node();
        node1.value = "1";
        node1.leftChild = new Node();
        node1.leftChild.value = "2";
        node1.rightChild = new Node();
        node1.rightChild.value = "3";
        node1.leftChild.leftChild = new Node();
        node1.leftChild.leftChild.value = "4";
        node1.leftChild.leftChild.leftChild = new Node();
        node1.leftChild.leftChild.leftChild.value = "99";
        node1.rightChild.leftChild = new Node();
        node1.rightChild.leftChild.value = "5";
        node1.rightChild.rightChild = new Node();
        node1.rightChild.rightChild.value = "6";

        Node node2 = new Node();
        node2.value = "1";
        node2.leftChild = new Node();
        node2.leftChild.value = "3";
        node2.leftChild.leftChild = new Node();
        node2.leftChild.leftChild.value = "6";
        node2.leftChild.rightChild = new Node();
        node2.leftChild.rightChild.value = "5";
        node2.rightChild = new Node();
        node2.rightChild.value = "2";
        node2.rightChild.rightChild = new Node();
        node2.rightChild.rightChild.value = "4";
        node2.rightChild.rightChild.rightChild = new Node();
        node2.rightChild.rightChild.rightChild.value = "99";

        System.out.println(areReflected(node1, node2));
    }




    /**
     * areReflected returns true if tree1 and tree2 are reflected
     */
    static boolean areReflected(Node tree1, Node tree2) {
        return
            tree1 == null && tree2 == null || tree1 != null && tree2 != null &&
                tree1.value.equals(tree2.value) &&
                areReflected(tree1.leftChild, tree2.rightChild) &&
                areReflected(tree1.rightChild, tree2.leftChild);
    }

}

class Node {
    Node leftChild;
    Node rightChild;
    String value;
}


