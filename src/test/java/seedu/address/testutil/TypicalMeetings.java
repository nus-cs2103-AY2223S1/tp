package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MeetingList;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {
    public static final Meeting MEETING_TYPICAL_1 = new MeetingBuilder().withPersons(ALICE, CARL)
            .withDescription("CS1101S")
            .withDateAndTime("12-01-2022").withLocation("DECK").build();
    public static final Meeting MEETING_TYPICAL_2 = new MeetingBuilder().withPersons(ALICE, ELLE, FIONA)
            .withDescription("CS2040S")
            .withDateAndTime("05-12-2022").withLocation("UTOWN").build();
    public static final Meeting MEETING_TYPICAL_3 = new MeetingBuilder().withPersons(BENSON, DANIEL, GEORGE)
            .withDescription("CS2103")
            .withDateAndTime("06-02-2022").withLocation("COM1").build();

    private TypicalMeetings() {} // prevents instantiation
    /**
     * Returns an {@code MeetingList} with all the typical meetings.
     */
    public static MeetingList getTypicalMeetingList() {
        MeetingList ml = new MeetingList();
        for (Meeting meeting : getTypicalMeetings()) {
            ml.addMeeting(meeting);
        }
        return ml;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(MEETING_TYPICAL_1, MEETING_TYPICAL_2, MEETING_TYPICAL_3));
    }
}
