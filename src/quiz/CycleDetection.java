package quiz;

public class CycleDetection {

    class Node {
        int data;
        Node next;
    }

    // Approach 1: Floyd's Tortoise and Hare
    // slow moves 1 step, fast moves 2 steps
    public static boolean isCycle(Node head) {
        if (head == null)
            return false;

        Node slow = head;
        Node fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null)
                return false;

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }


    // Approach 2: Brent's algorithm return cycle position, -1 is no cycle
    public static int isCycle2(Node head) {
        /*
          0 1 2 3 4 5 6 7 8 9  compare between S and F
          S F                  power: 1
            S F F              power: 2
                S F F F F      power: 4
                        S F F ...
         */
        int power = 1, // fast moves 2, 4, 8 ... steps
            range = 1; // range between slow and fast
        Node slow = head;
        Node fast = head.next;

        while (slow != fast) {
            if (fast == null) return -1; // no cycle

            if (power == range) {
                slow = fast;
                power <<= 1;
                range = 0;
            }
            fast = fast.next; // 1, 1~2, 1~4, 1~8
            range++;
        }

        // cycle
        int cyclePosition = 0;
        slow = fast = head;

        for (int i = 0; i < range; i++) // range between slow and fast
            fast = fast.next;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
            cyclePosition++;
        }

        return cyclePosition;
    }
}
