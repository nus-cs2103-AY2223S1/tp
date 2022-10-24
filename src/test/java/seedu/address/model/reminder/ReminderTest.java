package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalReminders.REMINDER_MEETING_DATETIME;
import static seedu.address.testutil.TypicalReminders.REMINDER_MEETING_DATETIME_STRING;
import static seedu.address.testutil.TypicalReminders.REMINDER_MEETING_DESCRIPTION;
import static seedu.address.testutil.TypicalReminders.REMINDER_PROMOTION_DATETIME;
import static seedu.address.testutil.TypicalReminders.REMINDER_PROMOTION_DESCRIPTION;
import static seedu.address.testutil.TypicalReminders.VALID_REMINDER_MEETING;
import static seedu.address.testutil.TypicalReminders.VALID_REMINDER_PROMOTION;

import org.junit.jupiter.api.Test;

class ReminderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Reminder(null,
                REMINDER_MEETING_DATETIME, ALICE.getName(), ALICE.getPhone()));
        assertThrows(NullPointerException.class, () -> new Reminder(REMINDER_MEETING_DESCRIPTION,
                null, ALICE.getName(), ALICE.getPhone()));
        assertThrows(NullPointerException.class, () -> new Reminder(REMINDER_MEETING_DESCRIPTION,
                REMINDER_MEETING_DATETIME, null, ALICE.getPhone()));
        assertThrows(NullPointerException.class, () -> new Reminder(REMINDER_MEETING_DESCRIPTION,
                REMINDER_MEETING_DATETIME, ALICE.getName(), null));
    }

    @Test
    public void constructor_validInput_success() {
        assertEquals(VALID_REMINDER_MEETING, new Reminder(REMINDER_MEETING_DESCRIPTION,
                REMINDER_MEETING_DATETIME, ALICE.getName(), ALICE.getPhone()));
    }

    @Test
    public void matchesNameAndPhone() {
        // null name -> false
        assertFalse(VALID_REMINDER_MEETING.matchesNameAndPhone(null, ALICE.getPhone()));

        // null phone -> false
        assertFalse(VALID_REMINDER_MEETING.matchesNameAndPhone(ALICE.getName(), null));

        // same name and phone -> true
        assertTrue(VALID_REMINDER_MEETING.matchesNameAndPhone(ALICE.getName(), ALICE.getPhone()));

        // different name but same phone -> false
        assertFalse(VALID_REMINDER_MEETING.matchesNameAndPhone(BENSON.getName(), ALICE.getPhone()));

        // different phone but same name -> false
        assertFalse(VALID_REMINDER_PROMOTION.matchesNameAndPhone(BENSON.getName(), ALICE.getPhone()));
    }

    @Test
    public void isValidReminder() {
        assertThrows(NullPointerException.class, () -> Reminder.isValidReminder(null,
                REMINDER_MEETING_DATETIME_STRING, VALID_NAME_AMY, VALID_PHONE_AMY));
        assertThrows(NullPointerException.class, () -> Reminder.isValidReminder(REMINDER_MEETING_DESCRIPTION,
                null, VALID_NAME_AMY, VALID_PHONE_AMY));
        assertThrows(NullPointerException.class, () -> Reminder.isValidReminder(REMINDER_MEETING_DESCRIPTION,
                REMINDER_MEETING_DATETIME_STRING, null, VALID_PHONE_AMY));
        assertThrows(NullPointerException.class, () -> Reminder.isValidReminder(REMINDER_MEETING_DESCRIPTION,
                REMINDER_MEETING_DATETIME_STRING, VALID_NAME_AMY, null));

        assertFalse(Reminder.isValidReminder("", REMINDER_MEETING_DATETIME_STRING,
                VALID_NAME_AMY, VALID_PHONE_AMY));
        assertFalse(Reminder.isValidReminder(REMINDER_MEETING_DESCRIPTION, "notADateTime",
                VALID_NAME_AMY, VALID_PHONE_AMY));
        assertFalse(Reminder.isValidReminder(REMINDER_MEETING_DESCRIPTION, REMINDER_MEETING_DATETIME_STRING,
                "", VALID_PHONE_AMY));
        assertFalse(Reminder.isValidReminder(REMINDER_MEETING_DESCRIPTION, REMINDER_MEETING_DATETIME_STRING,
                VALID_NAME_AMY, "notAPhoneNumber"));

        assertTrue(Reminder.isValidReminder(REMINDER_MEETING_DESCRIPTION, REMINDER_MEETING_DATETIME_STRING,
                VALID_NAME_AMY, VALID_PHONE_AMY));
    }

    @Test
    public void compareTo() {
        Reminder reminder1 = new Reminder(REMINDER_MEETING_DESCRIPTION,
                REMINDER_MEETING_DATETIME, ALICE.getName(), ALICE.getPhone());
        Reminder reminder2 = new Reminder(REMINDER_MEETING_DESCRIPTION,
                REMINDER_MEETING_DATETIME, ALICE.getName(), ALICE.getPhone());
        Reminder reminder3 = new Reminder(REMINDER_MEETING_DESCRIPTION,
                REMINDER_PROMOTION_DATETIME, ALICE.getName(), ALICE.getPhone());
        Reminder reminder4 = new Reminder(REMINDER_PROMOTION_DESCRIPTION,
                REMINDER_MEETING_DATETIME, ALICE.getName(), ALICE.getPhone());
        Reminder reminder5 = new Reminder(REMINDER_PROMOTION_DESCRIPTION,
                REMINDER_PROMOTION_DATETIME, ALICE.getName(), ALICE.getPhone());

        assertEquals(0, reminder1.compareTo(reminder2));
        assertEquals(0, reminder1.compareTo(reminder1));
        assertTrue(1 >= reminder1.compareTo(reminder3));
        assertTrue(-1 <= reminder3.compareTo(reminder1));
        assertTrue(1 >= reminder1.compareTo(reminder4));
    }

    @Test
    public void equals() {
        Reminder reminder1 = new Reminder(REMINDER_MEETING_DESCRIPTION,
                REMINDER_MEETING_DATETIME, ALICE.getName(), ALICE.getPhone());
        Reminder reminder2 = new Reminder(REMINDER_MEETING_DESCRIPTION,
                REMINDER_MEETING_DATETIME, ALICE.getName(), ALICE.getPhone());
        Reminder reminder3 = new Reminder(REMINDER_PROMOTION_DESCRIPTION,
                REMINDER_PROMOTION_DATETIME, ALICE.getName(), ALICE.getPhone());

        assertTrue(reminder1.equals(reminder1));
        assertTrue(reminder1.equals(reminder2));
        assertFalse(reminder1.equals(reminder3));
    }
}
