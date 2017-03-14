package quiz.timeinterval;

import java.util.Arrays;

public class TimeInterval {

    class Interval {
        int start;
        int end;
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
     * S------E          start times: 1, 3, 4, 6, 12
     *   SE                end times: 4, 7, 8, 12, 14
     *    S--E
     *      S-----E
     *            S-E
     *
     *
     */
    public int minMeetingRooms(Interval[] intervals) {

        int length = intervals.length;
        int[] starts = new int[length];
        int[] ends = new int[length];

        for (int i = 0; i < length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int rooms = 0;
        int startIndex = 0;
        int endIndex = 0;

        while (startIndex < length) {
            if (starts[startIndex] < ends[endIndex])
                rooms++;
            else
                endIndex++;

            startIndex++;   // always increase start index
        }
        return rooms;
    }
}
