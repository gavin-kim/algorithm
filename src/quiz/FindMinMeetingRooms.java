package quiz;

import java.util.Arrays;

public class FindMinMeetingRooms {

    public static class Meeting {
        int start; // starting time
        int end;   // ending time

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * An array of meeting time intervals consisting of start and end times
     * [[start, end], [start, end], ...], find the minimum number of conference
     * rooms required.
     *
     * sort start times and end times in order
     * if start < end, one more room is required and check the next start time.
     *
     *
     * 1 3 5 7 9 ...
     * s---|-------e            start times: 1, 3, 4, 6, 12
     *   s-e       |              end times: 4, 7, 8, 12, 14
     *    s|--e    |
     *     |s-|----|e
     *     |s-|----|-e
     *     |  |  s-|----
     *     |  |  s-|----
     *     |  V    third end time (+ 1 rooms (2-1)) 1 room is finished
     *     V  second end time (+1 rooms (2-1)) 1 room is finished
     *     first end time (+3 rooms)
     */
    public static int solve(Meeting[] meetings) {

        int length = meetings.length;
        int[] startTimes = new int[length];
        int[] endTimes = new int[length];

        for (int i = 0; i < length; i++) {
            startTimes[i] = meetings[i].start;
            endTimes[i] = meetings[i].end;
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int rooms = 0;
        int endIndex = 0;

        for (int startTime : startTimes) {
            if (startTime < endTimes[endIndex]) rooms++;
            else endIndex++;
        }
        return rooms;
    }
}
