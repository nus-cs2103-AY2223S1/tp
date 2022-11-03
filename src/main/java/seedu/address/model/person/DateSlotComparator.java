package seedu.address.model.person;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Represents a comparator to compare date and time slot.
 */
public class DateSlotComparator implements Comparator<DateSlot> {
    /**
     * Compare the home visits' date and time slot.
     *
     * @param dateTimeSlot1 the first object to be compared.
     * @param dateTimeSlot2 the second object to be compared.
     */

    public int compare(DateSlot dateTimeSlot1, DateSlot dateTimeSlot2) {
        LocalDateTime d1 = dateTimeSlot1.dateSlotTime;
        LocalDateTime d2 = dateTimeSlot2.dateSlotTime;
        return d1.compareTo(d2);
    }

}
