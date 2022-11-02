package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.reminder.exceptions.DuplicateReminderException;
import seedu.address.model.reminder.exceptions.ReminderNotFoundException;
import seedu.address.model.student.exceptions.PersonNotFoundException;

/**
 * A list of reminders that enforces uniqueness between its elements and does not allow nulls.
 * A reminder is considered unique by comparing using {@code Reminder#isSameReminder(Reminder)}. As such, adding and
 * updating of persons uses Reminder#isSameReminder(Reminder) for equality so as to ensure that the person being added
 * or updated is unique in terms of identity in the UniquePersonList. However, the removal of a person uses
 * Reminder#equals(Object) so as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Reminder#isSameReminder(Reminder)
 */
public class UniqueReminderList implements Iterable<Reminder> {

    private final ObservableList<Reminder> internalList = FXCollections.observableArrayList();
    private final ObservableList<Reminder> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent reminder as the given argument.
     */
    public boolean contains(Reminder toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameReminder);
    }

    /**
     * Adds a reminder to the list. The reminder list will then be sorted by the same custom ordering as defined in
     * {@code sortRemindersByPriority}.
     */
    public void add(Reminder toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateReminderException();
        }

        internalList.add(toAdd);
        internalList.sort(new ReminderComplexComparator(new ReminderPriorityComparator(),
                new ReminderDeadlineComparator()));
    }

    /**
     * Replaces the reminder {@code target} in the list with {@code editedReminder}.
     * {@code target} must exist in the list.
     * The reminder identity of {@code editedReminder} must not be the same as another existing reminder in ModQuik.
     */
    public void setReminder(Reminder target, Reminder editedReminder) {
        requireAllNonNull(target, editedReminder);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ReminderNotFoundException();
        }

        if (!target.isSameReminder(editedReminder) && contains(editedReminder)) {
            throw new DuplicateReminderException();
        }

        internalList.set(index, editedReminder);
    }

    /**
     * Removes the equivalent reminder from the list.
     * The reminder must exist in the list.
     */
    public void remove(Reminder toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ReminderNotFoundException();
        }
    }

    /**
     * Sorts reminder by priority in the list. Reminders with the same priority will be sorted by deadline
     * chronologically.
     * Reminders with same priority and deadline will then be sorted lexicographically.
     */
    public void sortRemindersByPriority() {
        internalList.sort(new ReminderComplexComparator(new ReminderPriorityComparator(),
                new ReminderDeadlineComparator()));
    }

    /**
     * Sorts reminder by deadline in the list. Reminders with the same deadline will be sorted by priority, from "HIGH"
     * to "MEDIUM" to "LOW".
     * Reminders with same deadline and priority will then be sorted lexicographically.
     */
    public void sortRemindersByDeadline() {
        internalList.sort(new ReminderComplexComparator(new ReminderDeadlineComparator(),
                new ReminderPriorityComparator()));
    }

    /**
     * Replaces the contents of this list with {@code reminder}.
     * {@code reminders} must not contain duplicate reminder.
     */
    public void setReminders(List<Reminder> reminders) {
        requireAllNonNull(reminders);
        if (!remindersAreUnique(reminders)) {
            throw new DuplicateReminderException();
        }

        internalList.setAll(reminders);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Reminder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Reminder> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueReminderList // instanceof handles nulls
                && internalList.equals(((UniqueReminderList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code reminders} contains only unique reminders.
     */
    private boolean remindersAreUnique(List<Reminder> reminders) {
        for (int i = 0; i < reminders.size() - 1; i++) {
            for (int j = i + 1; j < reminders.size(); j++) {
                if (reminders.get(i).isSameReminder(reminders.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Marks the equivalent reminder in the list as done.
     * The reminder must exist in the list.
     */
    public void mark(Reminder target) {
        requireNonNull(target);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ReminderNotFoundException();
        }
        target.setStatus(true);
        internalList.set(index, target);
    }

    /**
     * Unarks the equivalent reminder in the list as undone.
     * The reminder must exist in the list.
     */
    public void unmark(Reminder target) {
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ReminderNotFoundException();
        }
        target.setStatus(false);
        internalList.set(index, target);
    }
}
