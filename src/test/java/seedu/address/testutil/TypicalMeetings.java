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
    public static final Meeting MEETING_TYPICAL_1 = createMeeting1();
    public static final Meeting MEETING_TYPICAL_2 = createMeeting2();
    public static final Meeting MEETING_TYPICAL_3 = createMeeting3();

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
        return new ArrayList<>(Arrays.asList(createMeeting1(), createMeeting2(), createMeeting3()));
    }

    private static Meeting createMeeting1() {
        return new MeetingBuilder().withPersons(ALICE, CARL).withDescription("CS1101S")
                .withDateAndTime("Wednesday, 12 January 2022 11:59 PM").withLocation("DECK").build();
    }

    private static Meeting createMeeting2() {
        return new MeetingBuilder().withPersons(ALICE, ELLE, FIONA).withDescription("CS2040S")
                .withDateAndTime("Monday, 5 December 2022 11:49 PM").withLocation("UTOWN").build();
    }

    private static Meeting createMeeting3() {
        return new MeetingBuilder().withPersons(BENSON, DANIEL, GEORGE).withDescription("CS2103")
                .withDateAndTime("Sunday, 6 February 2022 09:00 PM").withLocation("COM1").build();
    }
}
