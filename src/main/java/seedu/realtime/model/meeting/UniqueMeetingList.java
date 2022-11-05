package seedu.realtime.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.realtime.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.meeting.exceptions.DuplicateMeetingException;
import seedu.realtime.model.meeting.exceptions.MeetingNotFoundException;
import seedu.realtime.model.person.Address;
import seedu.realtime.model.person.Client;
import seedu.realtime.model.person.Name;
import seedu.realtime.model.person.exceptions.DuplicatePersonException;

/**
 * A list of meetings that enforces uniqueness between its elements and does not allow nulls.
 * A meeting is considered unique by comparing using {@code Meeting#isSameMeeting(Meeting)}. As such, adding and
 * updating of Meeting uses Meeting#isSameMeeting(Meeting) for equality to ensure that the Meeting being added or
 * updated is unique in terms of identity in the UniqueMeetingList. However, the removal of an Meeting uses
 * Meeting#equals(Object) to ensure that the Meeting with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Meeting#isSameMeeting(Meeting)
 */
public class UniqueMeetingList implements Iterable<Meeting> {

    private final ObservableList<Meeting> internalList = FXCollections.observableArrayList();
    private final ObservableList<Meeting> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent meeting as the given argument.
     */
    public boolean contains(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMeeting);
    }

    /**
     * Adds a meeting to the list.
     * The meeting must not already exist in the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMeetingException();
        }
        internalList.add(toAdd);
        Collections.sort(internalList);
    }

    /**
     * Gets the meeting from the given name {@code name} and listing address {@code address}.
     * @param name name of the person in meeting
     * @param address listing address of meeting
     * @return meeting with given name and listing address
     */
    public Meeting getMeeting(Name name, Address address) {
        requireNonNull(name);
        for (Meeting meeting : internalList) {
            if (meeting.getClient().equals(name) && meeting.getListing().equals(address)) {
                return meeting;
            }
        }
        throw new MeetingNotFoundException();
    }

    /**
     * Replaces the meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the list.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing meeting in the list.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetingNotFoundException();
        }

        if (!target.isSameMeeting(editedMeeting) && contains(editedMeeting)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedMeeting);
    }

    /**
     * Removes the equivalent meeting from the list.
     * The person must exist in the list.
     */
    public void remove(Meeting toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetingNotFoundException();
        }
    }

    public void setMeetings(UniqueMeetingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code meetings}.
     * {@code meetings} must not contain duplicate persons.
     */
    public void setMeetings(List<Meeting> meetings) {
        requireAllNonNull(meetings);
        if (!meetingsAreUnique(meetings)) {
            throw new DuplicateMeetingException();
        }

        internalList.setAll(meetings);
    }

    /**
     * Remove all meetings with the client.
     */
    public void deleteMeetingsWithClient(Client toBeRemoved) {
        List<Meeting> newInternalList = internalList.stream().filter(toBeRemoved::doNotHaveMeeting)
            .collect(Collectors.toList());
        setMeetings(newInternalList);
    }

    /**
     * Remove all meetings about the listing.
     */
    public void deleteMeetingsAboutListing(Listing toBeRemoved) {
        List<Meeting> newInternalList = internalList.stream().filter(toBeRemoved::doNotHaveMeeting)
            .collect(Collectors.toList());
        setMeetings(newInternalList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueMeetingList // instanceof handles nulls
                && internalList.equals(((UniqueMeetingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean meetingsAreUnique(List<Meeting> listings) {
        for (int i = 0; i < listings.size() - 1; i++) {
            for (int j = i + 1; j < listings.size(); j++) {
                if (listings.get(i).isSameMeeting(listings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
