package seedu.address.testutil;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Locale;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.util.DateTimeProcessor;

/**
 * A utility class to help with building Person objects.
 */
public class MeetingBuilder {

    private final ArrayList<Person> personsToMeetArray = new ArrayList<>();
    public static final Person DEFAULT_PERSON_IN_MEETING_BUILDER = new PersonBuilder()
        .withTags("Classmate", "Dalao").build();
    public static final String DEFAULT_DESCRIPTION = "Do CS2103 Project";
    public static final String DEFAULT_DATE_AND_TIME = "16-10-2022 1530";
    public static final String DEFAULT_LOCATION = "University Town";

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        this.personsToMeetArray.add(MeetingBuilder.DEFAULT_PERSON_IN_MEETING_BUILDER);
    }

    public Meeting build() throws ParseException {
        return new Meeting(this.personsToMeetArray, MeetingBuilder.DEFAULT_DESCRIPTION,
            MeetingBuilder.DEFAULT_DATE_AND_TIME, MeetingBuilder.DEFAULT_LOCATION);
    }
}
