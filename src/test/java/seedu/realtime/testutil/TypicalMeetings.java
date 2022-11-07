package seedu.realtime.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.realtime.model.RealTime;
import seedu.realtime.model.meeting.Meeting;

import static seedu.realtime.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {

    public static final Meeting ALICE = new MeetingBuilder()
            .withClient("Alice Pauline")
            .withListing("1")
            .withDateTime("2022-10-24 12:00")
            .withTags("Viewing")
            .build();

    public static final Meeting BENSON = new MeetingBuilder()
            .withClient("Benson Meier")
            .withListing("2")
            .withDateTime("2022-10-25 12:00")
            .withTags("Contract signing")
            .build();

    public static final Meeting CARL = new MeetingBuilder()
            .withClient("Carl Kurz")
            .withListing("3")
            .withDateTime("2022-10-21 08:00")
            .withTags("Viewing", "With Children")
            .build();

    public static final Meeting DANIEL = new MeetingBuilder()
            .withClient("Daniel Meier")
            .withListing("4")
            .withDateTime("2023-01-01 13:00")
            .build();

    public static final Meeting ELLE = new MeetingBuilder()
            .withClient("Elle Meyer")
            .withListing("5")
            .withDateTime("2020-09-30 12:00")
            .withTags("Contract signing", "Tough client")
            .build();

    public static final Meeting FIONA = new MeetingBuilder()
            .withClient("Fiona Kunz")
            .withListing("6")
            .withDateTime("2023-02-28 24:00")
            .build();

    public static final Meeting GEORGE = new MeetingBuilder()
            .withClient("George Best")
            .withListing("7")
            .withDateTime("2025-09-15 23:59")
            .build();

    // Manually added
    public static final Meeting HOON = new MeetingBuilder().withClient("Hoon Meier").build();
    public static final Meeting IDA = new MeetingBuilder().withClient("Ida Mueller").build();

    // Manually added - Meeting details found in {@code CommandTestUtil}
    public static final Meeting AMY = new MeetingBuilder()
            .withClient(VALID_NAME_AMY)
            .withListing(VALID_ID_AMY)
            .withDateTime(VALID_DATETIME_1)
            .withTags(VALID_TAG_VIEWING)
            .build();

    public static final Meeting BOB = new MeetingBuilder()
            .withClient(VALID_NAME_BOB)
            .withListing(VALID_ID_BOB)
            .withDateTime(VALID_DATETIME_2)
            .withTags(VALID_TAG_CONTRACT, VALID_TAG_CHILDREN)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalMeetings() {} // prevents instantiation

    /**
     * Returns a {@code RealTime} with all the typical Meetings.
     */
    public static RealTime getTypicalRealTime() {
        RealTime rt = new RealTime();
        for (Meeting meeting : getTypicalMeetings()) {
            rt.addMeeting(meeting);
        }
        return rt;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
