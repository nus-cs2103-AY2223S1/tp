package seedu.address.model.module;

import java.time.LocalTime;
import java.util.Comparator;

/**
 * To compare and sort lessons by date and time.
 */
public class LessonComparator implements Comparator<Lesson> {
    @Override
    public int compare(Lesson lesson1, Lesson lesson2) {
        int day1 = lesson1.getDay();
        int day2 = lesson2.getDay();
        LocalTime time1 = lesson1.getStartTime();
        LocalTime time2 = lesson2.getStartTime();
        if (day1 < day2) {
            return -1;
        } else if (day1 > day2) {
            return 1;
        } else {
            if (time1.isBefore(time2)) {
                return -1;
            } else if (time1.isAfter(time2)) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
