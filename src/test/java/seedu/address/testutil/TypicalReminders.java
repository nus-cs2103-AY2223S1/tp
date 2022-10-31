package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import seedu.address.model.reminder.DateTime;
import seedu.address.model.reminder.Reminder;

/**
 * A utility class containing a list of {@code Reminder} objects to be used in tests.
 */
public class TypicalReminders {
    public static final String REMINDER_MEETING_DATETIME_STRING = "2022-12-12 at 11:11";
    public static final DateTime REMINDER_MEETING_DATETIME = new DateTime(REMINDER_MEETING_DATETIME_STRING);
    public static final String REMINDER_MEETING_DESCRIPTION = "Meeting with CEO!";

    public static final String REMINDER_PROMOTION_DATETIME_STRING = "2022-11-01 at 22:00";
    public static final DateTime REMINDER_PROMOTION_DATETIME = new DateTime(REMINDER_PROMOTION_DATETIME_STRING);
    public static final String REMINDER_PROMOTION_DESCRIPTION = "Promote product to customer!";

    public static final Reminder VALID_REMINDER_MEETING = new Reminder(REMINDER_MEETING_DESCRIPTION,
            REMINDER_MEETING_DATETIME, ALICE.getName(), ALICE.getPhone());
    public static final Reminder VALID_REMINDER_PROMOTION = new Reminder(REMINDER_PROMOTION_DESCRIPTION,
            REMINDER_PROMOTION_DATETIME, BENSON.getName(), BENSON.getPhone());
}
