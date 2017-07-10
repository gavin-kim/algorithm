package dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * String a, b
 *
 * Operation X
 *
 * 1. Capitalize zero or more of a's lowercase letters at some index
 * 2. Delete all of the remaining lowercase letters in String a
 *
 */
public class Abbreviation {

    /**
     * Check if it's possible to make a equal to b by performing the operation X on String a
     *
     * String a: daBcdxbdC  -> _B_C
     * String b: ABBAC      -> A_BA
     *                      -> AB_A
     */
    public static boolean solve(String a, String b) {

        List<Character> upperCases = new ArrayList<>();
        int posA = 0;
        int posB = 0;

        while (posA < a.length()) {
            char charA = a.charAt(posA);

            if (Character.isUpperCase(charA)) {
                posB = b.indexOf(charA, posB);

            }
        }
        return true;
    }


}
