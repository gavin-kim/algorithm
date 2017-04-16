package testing;


import dynamic.CoinChangeMin;
import quiz.FindMinMeetingRooms;
import recursion.CountPaths;
import recursion.Memoization;

import static recursion.CountPaths.grid;
import static recursion.CountPaths.grid2;

public class Test {

    private static final int ARRAY_SIZE = 100;
    private static final int RANGE = 1000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static final String RESOURCE_ROOT = "resources/data/";

    public static void main(String[] args) throws Exception {

        //CoinChangeMin.solve(13, new int[]{7,2,3,6}).forEach(System.out::println);
        FindMinMeetingRooms.Meeting[] meetings = {
            new FindMinMeetingRooms.Meeting(1, 6),
            new FindMinMeetingRooms.Meeting(2, 12),
            new FindMinMeetingRooms.Meeting(2, 7),
            new FindMinMeetingRooms.Meeting(12, 8),
            new FindMinMeetingRooms.Meeting(5, 14),
            new FindMinMeetingRooms.Meeting(5, 14),
            new FindMinMeetingRooms.Meeting(9, 14)
        };
        System.out.println(FindMinMeetingRooms.solve(meetings));
    }

}



