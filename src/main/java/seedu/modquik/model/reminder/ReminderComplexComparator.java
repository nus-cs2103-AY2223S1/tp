package seedu.modquik.model.reminder;

import java.util.Comparator;

/**
 * A comparison function, which imposes a total ordering on Reminders by 2 custom Reminder comparators.
 * The ordering is defined by the result of the first comparator. If it is 0, it will be defined by the value of the
 * second comparator. If it is still 0, the String value of ReminderName would be used which will be by the default
 * lexicographical order.
 */
public class ReminderComplexComparator implements Comparator<Reminder> {
    private final Comparator<Reminder> reminderComparatorOne;
    private final Comparator<Reminder> reminderComparatorTwo;

    /**
     * Constructs a {@code ReminderComplexComparator}.
     *
     * @param reminderComparatorOne The first Reminder Comparator
     * @param reminderComparatorTwo The second Reminder Comparator
     */
    public ReminderComplexComparator(Comparator<Reminder> reminderComparatorOne,
                                     Comparator<Reminder> reminderComparatorTwo) {
        this.reminderComparatorOne = reminderComparatorOne;
        this.reminderComparatorTwo = reminderComparatorTwo;

    }

    @Override
    public int compare(Reminder r1, Reminder r2) {
        int compareValueOne = reminderComparatorOne.compare(r1, r2);

        if (compareValueOne == 0) {
            int compareValueTwo = reminderComparatorTwo.compare(r1, r2);

            return compareValueTwo == 0 ? r1.getName().fullName.compareTo(r2.getName().fullName)
                    : compareValueTwo;
        } else {
            return compareValueOne;
        }
    }
}
