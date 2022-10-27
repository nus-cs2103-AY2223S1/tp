package seedu.address.model.reminder;


import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * A comparison function, which imposes a total ordering on Reminders by ReminderDeadline. The ordering is defined by
 * the compareTo method of LocalDateTime, which is the type of deadline field.
 */
public class ReminderPriorityDeadlineComparator implements Comparator<Reminder> {
    private Comparator<Reminder> one;
    private Comparator<Reminder> two;

    public ReminderPriorityDeadlineComparator(Comparator<Reminder> one, Comparator<Reminder> two) {
        this.one = one;
        this.two = two;
        
    }
    @Override
    public int compare(Reminder r1, Reminder r2) {
        int comparisonByPriority = one.compare(r1, r2);
        
        if (comparisonByPriority == 0) {
            return two.compare(r1, r2);
        } else {
            return comparisonByPriority;
        }
    }
}
