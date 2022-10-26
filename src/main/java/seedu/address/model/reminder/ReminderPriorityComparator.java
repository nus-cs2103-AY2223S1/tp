package seedu.address.model.reminder;

import java.util.Comparator;

public class ReminderPriorityComparator implements Comparator<Reminder> {
    @Override
    public int compare(Reminder r1, Reminder r2) {
        int otherPriorityValue = r2.getPriorityValue();
        int thisPriorityValue = r1.getPriorityValue();
        if (otherPriorityValue == thisPriorityValue) {
            return r1.getName().fullName.compareTo(r2.getName().fullName);
        } else {
            return thisPriorityValue - otherPriorityValue;
        }
    }
}
