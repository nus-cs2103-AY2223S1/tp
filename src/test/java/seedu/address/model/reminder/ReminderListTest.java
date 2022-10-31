package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalReminders.REMINDER_MEETING_DATETIME;
import static seedu.address.testutil.TypicalReminders.REMINDER_MEETING_DESCRIPTION;
import static seedu.address.testutil.TypicalReminders.VALID_REMINDER_MEETING;
import static seedu.address.testutil.TypicalReminders.VALID_REMINDER_PROMOTION;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ReminderListTest {

    @Test
    public void setReminder_null_nullPointerException() {
        ReminderList reminderList = new ReminderList();
        assertThrows(NullPointerException.class, () -> reminderList.setReminders(null));
    }

    @Test
    public void setReminder_validInput_success() {
        ReminderList reminderList = new ReminderList();
        ArrayList<Reminder> reminders = new ArrayList<>();
        reminders.add(VALID_REMINDER_MEETING);
        reminders.add(VALID_REMINDER_PROMOTION);

        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));

        reminderList.setReminders(reminders);
        assertTrue(reminderList.contains(VALID_REMINDER_MEETING));
        assertTrue(reminderList.contains(VALID_REMINDER_PROMOTION));
    }

    @Test
    public void add_null_nullPointerException() {
        ReminderList reminderList = new ReminderList();
        assertThrows(NullPointerException.class, () -> reminderList.add(null));
    }

    @Test
    public void add_validInput_success() {
        ReminderList reminderList = new ReminderList();
        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));

        reminderList.add(VALID_REMINDER_MEETING);
        assertTrue(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));

        reminderList.add(VALID_REMINDER_PROMOTION);
        assertTrue(reminderList.contains(VALID_REMINDER_MEETING));
        assertTrue(reminderList.contains(VALID_REMINDER_PROMOTION));
    }

    @Test
    public void delete_null_nullPointerException() {
        ReminderList reminderList = new ReminderList();
        assertThrows(NullPointerException.class, () -> reminderList.delete(null));
    }

    @Test
    public void delete_validInput_success() {
        ReminderList reminderList = new ReminderList();
        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));

        reminderList.add(VALID_REMINDER_MEETING);
        assertTrue(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));

        reminderList.add(VALID_REMINDER_PROMOTION);
        assertTrue(reminderList.contains(VALID_REMINDER_MEETING));
        assertTrue(reminderList.contains(VALID_REMINDER_PROMOTION));

        reminderList.delete(VALID_REMINDER_MEETING);
        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertTrue(reminderList.contains(VALID_REMINDER_PROMOTION));

        reminderList.delete(VALID_REMINDER_PROMOTION);
        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));
    }

    @Test
    public void contains_null_nullPointerException() {
        ReminderList reminderList = new ReminderList();
        assertThrows(NullPointerException.class, () -> reminderList.contains(null));
    }

    @Test
    public void contains_validInput_success() {
        ReminderList reminderList = new ReminderList();
        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));

        reminderList.add(VALID_REMINDER_MEETING);
        assertTrue(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));

        reminderList.add(VALID_REMINDER_PROMOTION);
        assertTrue(reminderList.contains(VALID_REMINDER_MEETING));
        assertTrue(reminderList.contains(VALID_REMINDER_PROMOTION));

        reminderList.delete(VALID_REMINDER_MEETING);
        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertTrue(reminderList.contains(VALID_REMINDER_PROMOTION));

        reminderList.delete(VALID_REMINDER_PROMOTION);
        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));
    }

    @Test
    public void size() {
        ReminderList reminderList = new ReminderList();
        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));
        assertEquals(0, reminderList.size());

        reminderList.add(VALID_REMINDER_MEETING);
        assertTrue(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));
        assertEquals(1, reminderList.size());

        reminderList.add(VALID_REMINDER_PROMOTION);
        assertTrue(reminderList.contains(VALID_REMINDER_MEETING));
        assertTrue(reminderList.contains(VALID_REMINDER_PROMOTION));
        assertEquals(2, reminderList.size());

        reminderList.delete(VALID_REMINDER_MEETING);
        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertTrue(reminderList.contains(VALID_REMINDER_PROMOTION));
        assertEquals(1, reminderList.size());

        reminderList.delete(VALID_REMINDER_PROMOTION);
        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));
        assertEquals(0, reminderList.size());
    }

    @Test
    public void clear() {
        ReminderList reminderList = new ReminderList();
        assertFalse(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));
        assertEquals(0, reminderList.size());

        reminderList.add(VALID_REMINDER_MEETING);
        assertTrue(reminderList.contains(VALID_REMINDER_MEETING));
        assertFalse(reminderList.contains(VALID_REMINDER_PROMOTION));
        assertEquals(1, reminderList.size());

        reminderList.add(VALID_REMINDER_PROMOTION);
        assertTrue(reminderList.contains(VALID_REMINDER_MEETING));
        assertTrue(reminderList.contains(VALID_REMINDER_PROMOTION));
        assertEquals(2, reminderList.size());

        reminderList.clear();
        assertEquals(0, reminderList.size());
    }

    @Test
    public void equals() {
        ReminderList reminderList1 = new ReminderList();
        ReminderList reminderList2 = new ReminderList();
        ReminderList reminderList3 = new ReminderList();
        ReminderList reminderList4 = new ReminderList();

        reminderList1.add(VALID_REMINDER_MEETING);
        reminderList2.add(VALID_REMINDER_MEETING);
        reminderList3.add(VALID_REMINDER_PROMOTION);

        // same reminderList -> true
        assertTrue(reminderList1.equals(reminderList1));

        // same reminders -> true
        assertTrue(reminderList1.equals(reminderList2));

        // different reminders -> false
        assertFalse(reminderList1.equals(reminderList3));

        // empty reminderList and nonEmpty reminderList -> false
        assertFalse(reminderList1.equals(reminderList4));
    }

    @Test
    public void getRemindersWithNameAndPhone() {
        ReminderList reminderList = new ReminderList();
        reminderList.add(VALID_REMINDER_PROMOTION);
        reminderList.add(VALID_REMINDER_MEETING);

        assertTrue(reminderList.getRemindersWithNameAndPhone(ALICE.getName(), CARL.getPhone()).isEmpty());
        assertTrue(reminderList.getRemindersWithNameAndPhone(CARL.getName(), ALICE.getPhone()).isEmpty());
        assertTrue(reminderList.getRemindersWithNameAndPhone(CARL.getName(), CARL.getPhone()).isEmpty());

        assertEquals(VALID_REMINDER_MEETING,
                reminderList.getRemindersWithNameAndPhone(ALICE.getName(), ALICE.getPhone()).get(0));
    }

    @Test
    public void updateRemindersWithNewNameAndPhone() {
        ReminderList reminderList = new ReminderList();
        Reminder reminder = new Reminder(REMINDER_MEETING_DESCRIPTION, REMINDER_MEETING_DATETIME,
                ALICE.getName(), ALICE.getPhone());
        reminderList.add(reminder);

        assertFalse(reminderList.getRemindersWithNameAndPhone(ALICE.getName(), ALICE.getPhone()).isEmpty());
        assertTrue(reminderList.getRemindersWithNameAndPhone(BENSON.getName(), BENSON.getPhone()).isEmpty());

        reminderList.updateRemindersWithNewNameAndPhone(ALICE.getName(), ALICE.getPhone(),
                BENSON.getName(), BENSON.getPhone());
        assertTrue(reminderList.getRemindersWithNameAndPhone(ALICE.getName(), ALICE.getPhone()).isEmpty());
        assertFalse(reminderList.getRemindersWithNameAndPhone(BENSON.getName(), BENSON.getPhone()).isEmpty());
    }

    @Test
    public void deleteRemindersWithNameAndPhone() {
        ReminderList reminderList = new ReminderList();
        Reminder reminder = new Reminder(REMINDER_MEETING_DESCRIPTION, REMINDER_MEETING_DATETIME,
                ALICE.getName(), ALICE.getPhone());
        reminderList.add(reminder);
        assertFalse(reminderList.getRemindersWithNameAndPhone(ALICE.getName(), ALICE.getPhone()).isEmpty());


        reminderList.deleteRemindersWithNameAndPhone(ALICE.getName(), ALICE.getPhone());
        assertTrue(reminderList.getRemindersWithNameAndPhone(ALICE.getName(), ALICE.getPhone()).isEmpty());
    }

    @Test
    public void getAllReminders() {
        ReminderList reminderList = new ReminderList();
        reminderList.add(VALID_REMINDER_MEETING);
        reminderList.add(VALID_REMINDER_PROMOTION);

        assertEquals(VALID_REMINDER_MEETING.compareTo(VALID_REMINDER_PROMOTION) <= -1
                     ? VALID_REMINDER_MEETING
                     : VALID_REMINDER_PROMOTION,
                reminderList.getAllReminders().get(0));
        assertEquals(VALID_REMINDER_MEETING.compareTo(VALID_REMINDER_PROMOTION) >= 1
                     ? VALID_REMINDER_MEETING
                     : VALID_REMINDER_PROMOTION,
                reminderList.getAllReminders().get(1));
    }
}
