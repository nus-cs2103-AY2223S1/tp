package seedu.modquik.testutil;

import static seedu.modquik.logic.commands.CommandTestUtil.VALID_DEADLINE_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_DEADLINE_REMINDER2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_DESCRIPTION_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_DESCRIPTION_REMINDER2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_REMINDER2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_PRIORITY_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_PRIORITY_REMINDER2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.modquik.model.ModQuik;
import seedu.modquik.model.reminder.Reminder;

/**
 * A utility class containing a list of {@code Reminder} objects to be used in tests.
 */
public class TypicalReminders {

    public static final Reminder REMINDER1 = new ReminderBuilder().withName("Mark question 4 of test 1")
            .withDeadline("2000-01-22 14:00").withPriority("LOW").withDescription("200 more to go").build();
    public static final Reminder REMINDER2 = new ReminderBuilder().withName("Set paper for test 2")
            .withDeadline("2000-02-22 15:00").withPriority("LOW").withDescription("3 more questions to set").build();
    public static final Reminder REMINDER3 = new ReminderBuilder().withName("Zoom makeup tut")
            .withDeadline("2000-03-22 16:00").withPriority("LOW").withDescription("Tutorial 4 makeup").build();
    public static final Reminder REMINDER4 = new ReminderBuilder().withName("Upload HW1")
            .withDeadline("2000-04-22 17:00").withPriority("MEDIUM").withDescription("HW1 of CS2030").build();
    public static final Reminder REMINDER5 = new ReminderBuilder().withName("Upload zoom recording")
            .withDeadline("2000-05-22 18:00").withPriority("HIGH").withDescription("For lecture 4").build();

    // Manually added
    public static final Reminder MEETING_9AM = new ReminderBuilder().withName("Meeting at 9am")
            .withDeadline("2000-03-22 08:45").withPriority("MEDIUM").withDescription("with Prof Anne").build();
    public static final Reminder MEETING_10AM = new ReminderBuilder().withName("Meeting at 10am")
            .withDeadline("2000-03-22 09:45").withPriority("HIGH").withDescription("with Dean").build();

    // Manually added - Tutorial's details found in {@code CommandTestUtil}
    public static final Reminder SET_HW1 = new ReminderBuilder().withName(VALID_NAME_REMINDER1)
            .withDeadline(VALID_DEADLINE_REMINDER1).withPriority(VALID_PRIORITY_REMINDER1)
            .withDescription(VALID_DESCRIPTION_REMINDER1).build();
    public static final Reminder MARK_FINALS = new ReminderBuilder().withName(VALID_NAME_REMINDER2)
            .withDeadline(VALID_DEADLINE_REMINDER2).withPriority(VALID_PRIORITY_REMINDER2)
            .withDescription(VALID_DESCRIPTION_REMINDER2).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalReminders() {} // prevents instantiation

    /**
     * Returns an {@code ModQuik} with all the typical reminders.
     */
    public static ModQuik getTypicalModQuik() {
        ModQuik ab = new ModQuik();
        for (Reminder reminder : getTypicalReminders()) {
            ab.addReminder(reminder);
        }
        return ab;
    }

    public static List<Reminder> getTypicalReminders() {
        return new ArrayList<>(Arrays.asList(REMINDER1, REMINDER2, REMINDER3, REMINDER4, REMINDER5));
    }
}
