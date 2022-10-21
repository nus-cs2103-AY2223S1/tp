package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * A list of all reminders.
 */
public class ReminderList implements ReadOnlyReminderList {
    private final ObservableList<Reminder> internalList = FXCollections.observableArrayList();
    private final SortedList<Reminder> sortedList = internalList.sorted(Comparator.naturalOrder());
    private final ObservableList<Reminder> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(sortedList);

    /**
     * Creates an empty ReminderList.
     */
    public ReminderList() {
    }

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
     * Returns the size of the reminder list.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Clears the reminder list.
     */
    public void clear() {
        internalList.clear();
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

    public List<Reminder> getRemindersWithNameAndPhone(Name name, Phone phone) {
        return internalUnmodifiableList.stream()
                .filter(reminder -> reminder.getNameString().equals(name.fullName)
                        && reminder.getPhoneString().equals(phone.value)).collect(Collectors.toList());
    }

    /**
     * Updates the existing reminders with {@code oldName} and {@code oldPhone}
     * with {@code newName} and {@code newPhone}.
     */
    public void updateRemindersWithNewNameAndPhone(Name oldName, Phone oldPhone,
                                                   Name newName, Phone newPhone) {
        for (Reminder reminder : internalList) {
            if (reminder.matchesNameAndPhone(oldName, oldPhone)) {
                reminder.setNameAndPhone(newName, newPhone);
            }
        }
        internalList.setAll(sortedList);
    }

    /**
     * Deletes reminders with {@code name} and {@code phone}
     */
    public void deleteRemindersWithNameAndPhone(Name name, Phone phone) {
        internalList.removeIf(reminder -> reminder.getNameString().equals(name.fullName)
                        && reminder.getPhoneString().equals(phone.value));
    }

    @Override
    public ObservableList<Reminder> getAllReminders() {
        return internalUnmodifiableList;
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
