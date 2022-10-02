package seedu.address.model.meeting;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

import seedu.address.model.person.Person;
import seedu.address.model.util.DateTimeProcessor;

/**
 * Class for a new Meeting
 */
public class Meeting {

    private final Person person;
    private String description;
    private String location;
    private String meetingDateAndTime;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.US)
        .withResolverStyle(ResolverStyle.SMART);
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm", Locale.US)
        .withResolverStyle(ResolverStyle.SMART);
    private final DateTimeProcessor validator = new DateTimeProcessor(dateFormatter, timeFormatter);

    /**
     * Constructor for a new Meeting
     *
     * @param person the person whom the user is meeting with
     */
    public Meeting(Person person) {
        this.person = person;
    }

    /**
     * sets the location of the meeting
     *
     * @param location of the meeting
     */
    public void setMeetingLocation(String location) {
        this.location = location;
    }

    /**
     * sets the description of the meeting
     *
     * @param description of the meeting
     */
    public void setMeetingDescription(String description) {
        this.description = description;
    }

    // might want to check for parseException earlier tho
    /**
     * sets the date and time of the meeting
     *
     * @param dateAndTime of the meeting
     * @throws ParseException when the dateAndTime is in the wrong format
     */
    public void setMeetingDateAndTime(String dateAndTime) throws ParseException {
        this.meetingDateAndTime = validator.processDateTime(dateAndTime);
    }

}
