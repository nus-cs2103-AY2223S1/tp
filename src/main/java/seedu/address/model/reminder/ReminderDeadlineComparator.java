package seedu.address.model.reminder;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * A comparison function, which imposes a total ordering on Reminders by deadline. The ordering is defined by
 * the compareTo method of LocalDateTime, which is the type of deadline field.
 */
public class ReminderDeadlineComparator implements Comparator<Reminder> {
    @Override
    public int compare(Reminder r1, Reminder r2) {
        LocalDateTime otherDeadline = r2.getDatetime();
        LocalDateTime thisDeadline = r1.getDatetime();
        return thisDeadline.compareTo(otherDeadline);
    }
}
