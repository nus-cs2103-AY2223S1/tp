package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class MeetingList implements ReadOnlyMeetingList {

    // May need to change this to iterable list
    private final UniqueMeetingList meetings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        meetings = new UniqueMeetingList();
    }

    public MeetingList() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public MeetingList(ReadOnlyMeetingList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void resetData(ReadOnlyMeetingList newData) {
        setMeetings(newData.getMeetingList());
    }

    //// meeting-level operations

    /**
     * Returns true if a Meeting with the same person to meet
     * and date and time as {@code meeting} exists in the address book.
     */
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addMeeting(Meeting newMeeting) {
        this.meetings.add(newMeeting);
    }

    /**
     * Replaces the given Meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the address book.
     * {@code editedMeeting} must not be the same
     * as another existing Meeting in the address book.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);

        meetings.setMeeting(target, editedMeeting);
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    // util methods
    //    @Override
    //    public String toString() {
    //        return persons.asUnmodifiableObservableList().size() + " persons";
    ////        TODO: refine later
    //    }


    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingList // instanceof handles nulls
                && meetings.equals(((MeetingList) other).meetings));
    }

    @Override
    public int hashCode() {
        return meetings.hashCode();
    }
}
