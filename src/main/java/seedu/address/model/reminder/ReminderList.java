package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of all reminders.
 */
public class ReminderList implements ReadOnlyReminderList {
    private final ObservableList<Reminder> internalList = FXCollections.observableArrayList();
    private final ObservableList<Reminder> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public ReminderList() {}

    /**
     * Creates a ReminderList using the Reminders in the {@code toBeCopied}
     */
    public ReminderList(ReadOnlyReminderList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the reminder list with {@code reminder}.
     */
    public void setReminders(List<Reminder> reminders) {
        requireAllNonNull(reminders);
        internalList.setAll(reminders);
    }

    /**
     * Resets the existing data of this {@code ReminderList} with {@code newData}.
     */
    public void resetData(ReadOnlyReminderList newData) {
        requireNonNull(newData);

        setReminders(newData.getAllReminders());
    }

    /**
     * Adds a reminder to the list.
     */
    public void add(Reminder toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Deletes the specified reminder from the list.
     */
    public void delete(Reminder toDelete) {
        requireNonNull(toDelete);
        internalList.remove(toDelete);
    }

    /**
     * Returns true if the list contains an equivalent reminder as the given argument.
     */
    public boolean contains(Reminder toCheck) {
        requireNonNull(toCheck);
        return internalList.contains(toCheck);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderList // instanceof handles nulls
                && internalList.equals(((ReminderList) other).internalList));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Reminder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public ObservableList<Reminder> getAllReminders() {
        return asUnmodifiableObservableList();
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
