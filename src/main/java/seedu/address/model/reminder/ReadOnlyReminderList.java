package seedu.address.model.reminder;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a reminder list
 */
public interface ReadOnlyReminderList {

    /**
     * Returns an unmodifiable view of the reminders list.
     */
    ObservableList<Reminder> getAllReminders();
}
