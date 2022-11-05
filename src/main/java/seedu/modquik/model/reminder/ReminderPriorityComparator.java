package seedu.modquik.model.reminder;

import java.util.Comparator;

/**
 * A comparison function, which imposes a total ordering on Reminders by ReminderPriority. The ordering is defined by
 * the value of Priority in ReminderPriority, where "HIGH" = 1, "MEDIUM" = 2 and "LOW" = 3.
 */
public class ReminderPriorityComparator implements Comparator<Reminder> {
    @Override
    public int compare(Reminder r1, Reminder r2) {
        int otherPriorityValue = r2.getPriorityValue();
        int thisPriorityValue = r1.getPriorityValue();
        return thisPriorityValue - otherPriorityValue;
    }
}
