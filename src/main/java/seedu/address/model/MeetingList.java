package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;

/**
 * Wraps all data at the meeting-list level
 * Duplicates are not allowed (by .isSameMeeting comparison)
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
     * Creates an MeetingList using the Meetings in the {@code toBeCopied}
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
     * Adds a meeting to the meeting list.
     * The meeting must not already exist in the meeting list.
     */
    public void addMeeting(Meeting newMeeting) {
        this.meetings.add(newMeeting);
    }

    /**
     * Adds a meeting to the meeting list.
     * The meeting must not already exist in the meeting list.
     */
    public void addMeeting(Meeting newMeeting, int idx) {
        this.meetings.add(newMeeting, idx);
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
     * Removes {@code key} from this {@code MeetingList}.
     * {@code key} must exist in the meeting list.
     */
    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    /**
     * calls meetings.sortByDate()
     * @param isInAscending sorts in ascending if true, descending otherwise
     */
    public void sortByDate(boolean isInAscending) {
        meetings.sortByDate(isInAscending);
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
