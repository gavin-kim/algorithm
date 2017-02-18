package testing;

import structure.tree.avl.AVLTree;
import structure.tree.bst.BST;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.sql.*;
import java.util.*;

import static sorting.heap.HeapSort.swap;


public class Test {

    private static final int ARRAY_SIZE = 1000000;
    private static final int RANGE = 100000000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    public static void main(String[] args) throws Exception {
        Node tree1 = new Node("1");
        tree1.leftChild = new Node("2");
        tree1.rightChild = new Node("3");
        tree1.leftChild.leftChild = new Node("4");
        tree1.rightChild.leftChild = new Node("5");
        tree1.rightChild.rightChild = new Node("6");
        tree1.rightChild.rightChild.rightChild = new Node("10");

        Node tree2 = new Node("1");
        tree2.leftChild = new Node("3");
        tree2.rightChild = new Node("2");
        tree2.leftChild.leftChild = new Node("6");
        tree2.leftChild.leftChild.leftChild = new Node("10");
        tree2.leftChild.rightChild = new Node("5");
        tree2.rightChild.rightChild = new Node("4");


        System.out.println(areReflected(tree1, tree2));

    }

    static class Node {
        Node leftChild;
        Node rightChild;
        String value;

        public Node(String value) {
            this.value = value;
        }

    }

    static boolean areReflected(Node tree1, Node tree2) {
        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();
        q1.add(tree1);
        q2.add(tree2);

        while (!q1.isEmpty() && !q2.isEmpty()) {

            tree1 = q1.poll();
            tree2 = q2.poll();

            if (tree1 == null && tree2 == null)
                continue;

            if (tree1 != null && tree2 != null && tree1.value.equals(tree2.value)) {

                q1.add(tree1.leftChild);
                q2.add(tree2.rightChild);

                q1.add(tree1.rightChild);
                q2.add(tree2.leftChild);


            } else {
                return false;
            }
        }
        return true;
    }

    public static void hackerrankQ() throws Exception {
        Scanner input = new Scanner(new File("resources/data/input04.txt"));

        int lengthA = input.nextInt();
        Map<Integer, Integer> A = new HashMap<>();
        Map<Integer, Integer> B = new HashMap<>();

        Set<Integer> missingNumbers = new TreeSet<>();

        for (int i = 0; i < lengthA; i++) {
            int num = input.nextInt();

            if (A.containsKey(num))
                A.put(num, A.get(num) + 1);
            else
                A.put(num, 1);
        }

        int lengthB = input.nextInt();

        for (int i = 0; i < lengthB; i++) {
            int num = input.nextInt();

            if (B.containsKey(num))
                B.put(num, B.get(num) + 1);
            else
                B.put(num, 1);
        }

        for (int key : B.keySet())
            if (!B.get(key).equals(A.get(key)))
                missingNumbers.add(key);

        for (int v : missingNumbers)
            System.out.print(v + " ");
    }

    public static int test2(Connection c, String city) throws SQLException {

        int numActive;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = c.prepareStatement(
                "SELECT COUNT(*)" +
                    "FROM EMPLOYEE" +
                    "INNER JOIN CITY ON EMPLOYEE.CITY_ID = CITY.CITY_ID" +
                    "WHERE EMPLOYEE.EMP_ACTIVE = 'Y' AND CITY.CITY_NAME = ?");

            ps.setString(1, city);
            rs = ps.executeQuery();
            numActive = rs.getInt(1);

        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
        }

        return numActive;
    }

    public static void testAVL() {
        AVLTree<String, String> avlTree = new AVLTree<>();
        avlTree.put("S", "S");
        avlTree.put("E", "E");
        avlTree.put("X", "X");
        avlTree.put("A", "A");
        avlTree.put("R", "R");
        avlTree.put("C", "C");
        avlTree.put("H", "H");
        avlTree.put("M", "M");
        avlTree.put("L", "L");
        avlTree.put("P", "P");

        System.out.println(avlTree.delete("C"));
        System.out.println(avlTree.delete("H"));
        System.out.println(avlTree.delete("P"));
        System.out.println(avlTree.delete("S"));
        System.out.println(avlTree.delete("E"));
        System.out.println(avlTree.delete("A"));
        System.out.println(avlTree.delete("X"));
        System.out.println(avlTree.delete("M"));
        System.out.println(avlTree.delete("L"));
        System.out.println(avlTree.delete("P"));
        System.out.println(avlTree.delete("M"));
        avlTree.put("sdf", "sdf");
        System.out.println(avlTree.min());
        System.out.println(avlTree.max());
        System.out.println(avlTree.size());
        System.out.println(avlTree.getHeight());

        for (String k : avlTree.keys())
            System.out.println(k);
    }

    public static void testBST() {
        BST<String, String> bst = new BST<>();
        bst.put("S", "S");
        bst.put("E", "E");
        bst.put("X", "X");
        bst.put("A", "A");
        bst.put("R", "R");
        bst.put("C", "C");
        bst.put("H", "H");
        bst.put("M", "M");
        bst.put("L", "L");
        bst.put("P", "P");


        for (String k : bst.keys("F", "T"))
            System.out.println(k);
    }
    public static void quickSort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left< index - 1) { // Sort left half
            quickSort(arr, left, index - 1);
        }
        if (index< right) { // Sort right half
            quickSort(arr, index, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2]; // Pick pivot point

        while (left<= right) {
            //Find element on left that should be on right
            while (arr[left] < pivot) left++;

            //Find element on right that should be on left
            while (arr[right] > pivot) right--;

            if (left<= right) {
                swap(arr, left, right); // swaps elements
                left++;
                right--;
            }
        }
        return left;
    }


}











