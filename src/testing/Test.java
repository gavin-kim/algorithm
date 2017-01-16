package testing;

import sorting.basicsorts.BasicSorts;

public class Test {
    public static void main(String[] args) {
        Testable insertion = BasicSorts::insertionSort;
        Testable bubble = BasicSorts::bubbleSort;
        Testable selection = BasicSorts::selectionSort;

        System.out.println(TestUtils.simpleTest(insertion, 100000, 1000000, false));
        System.out.println(TestUtils.simpleTest(bubble, 100000, 1000000, false));
        System.out.println(TestUtils.simpleTest(selection, 100000, 1000000, false));
    }
}











