package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.exceptions.ConflictingMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;

/**
 * A list of meetings that enforces no timing conflicts between its elements and does not allow nulls.
 * A meeting is considered to have no timing conflict with another by comparing using {@code Meeting#willConflict}. As
 * such, adding and updating of meetings uses Meeting#willConflict(Meeting) for equality to ensure that the meeting
 * being added or updated does not conflict with other Meetings in the UniqueMeetingList. However, the removal of a
 * meeting uses Meeting#equals(Object) to ensure that the meeting with exactly the same fields will be removed.
 * <br>
 * Supports a minimal set of list operations.
 *
 * @see Meeting#willConflict(Meeting)
 */
public class NoConflictMeetingList implements Iterable<Meeting> {
    private final ObservableList<Meeting> internalList = FXCollections.observableArrayList();
    private final ObservableList<Meeting> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains a conflicting meeting as the given argument.
     * Any attempts to add a meeting that already exists in the list will be considered to be conflicting, even if they
     * are referring to the exact same meeting.
     */
    public boolean contains(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::willConflict);
    }

    /**
     * Returns true if the list contains a meeting that is not the given {@code except} and
     * conflicts with {@code toCheck}.
     */
    public boolean containsExcept(Meeting toCheck, Meeting except) {
        requireAllNonNull(toCheck, except);
        return internalList.stream().anyMatch(meeting ->
                toCheck.willConflict(meeting) && except != meeting);
    }

    /**
     * Returns true if the list contains an identical meeting as the given argument.
     */
    public boolean containsSpecific(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a meeting to the list.
     * The meeting must not conflict with any meetings in the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new ConflictingMeetingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the list.
     * The {@code editedMeeting} must not conflict with another existing meeting in the list.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) throws ConflictingMeetingException {
        requireAllNonNull(target, editedMeeting);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetingNotFoundException();
        }

        if (containsExcept(editedMeeting, target)) {
            // Any timing conflicts with the original timing is okay
            throw new ConflictingMeetingException();
        }

        internalList.set(index, editedMeeting);
    }

    public void setMeeting(NoConflictMeetingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Removes the equivalent meeting from the list.
     * The meeting must exist in the list.
     */
    public void remove(Meeting toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) { // Checks equivalence through Meeting#equals
            throw new MeetingNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code meetings}.
     * {@code meetings} must not contain conflicting meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        requireAllNonNull(meetings);
        if (!meetingsDoNotConflict(meetings)) {
            throw new ConflictingMeetingException();
        }

        internalList.setAll(meetings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoConflictMeetingList // instanceof handles nulls
                && internalList.equals(((NoConflictMeetingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code meetings} do not contain conflicting meetings.
     */
    private boolean meetingsDoNotConflict(List<Meeting> meetings) {
        for (int i = 0; i < meetings.size() - 1; i++) {
            for (int j = i + 1; j < meetings.size(); j++) {
                if (meetings.get(i).willConflict(meetings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
