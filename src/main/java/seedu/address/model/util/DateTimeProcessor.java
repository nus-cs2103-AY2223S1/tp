package seedu.address.model.util;

import static seedu.address.logic.parser.CreateMeetingCommandParser.DATE_TIME_VALIDATOR;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Checks whether the format of a due is in valid date-time format
 * in yyyy-MM-dd HHmm
 */
public class DateTimeProcessor {

    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter timeFormatter;

    /**
     * Constructor for a new DateValidator object
     *
     * @param dateFormatter determines the accepted date&time format of the input string
     */
    public DateTimeProcessor(DateTimeFormatter dateFormatter, DateTimeFormatter timeFormatter) {
        this.dateFormatter = dateFormatter;
        this.timeFormatter = timeFormatter;
    }

    //Solution below adapted from https://www.baeldung.com/java-datetimeformatter
    /**
     * Checks if the input string matches the accepted date format of the formatter
     *
     * @return Whether date format is valid
     */
    private boolean isDateValid(String dateStr) {
        try {
            DateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            inputDateFormat.setLenient(false);
            Date inputTime = inputDateFormat.parse(dateStr);
            this.dateFormatter.parse(dateStr);
        } catch (DateTimeParseException | java.text.ParseException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(DATE_TIME_VALIDATOR.isDateValid("30-02-2022"));
    }

    /**
     * Checks if the input string matches the accepted time format of the formatter
     *
     * @return Whether time format is valid
     */
    private boolean isTimeValid(String timeStr) {
        try {
            this.timeFormatter.parse(timeStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * converts input date&time from yyyy-MM-dd HHmm to MONTH dd yyyy hh:mm aa format
     *
     * @param dateAndTime date and time of meeting
     * @return date and time in MONTH dd yyyy hh:mm aa format
     */
    public String processDateTime(String dateAndTime) throws ParseException, java.text.ParseException {

        String[] tempStringArray = dateAndTime.strip().split("\\s+", 2);

        String meetingDate = tempStringArray[0];
        String meetingTime = tempStringArray.length < 2 ? "" : tempStringArray[1];

        if (Objects.equals(meetingDate, "")) {
            throw new ParseException("Meeting date cannot be empty");
        }

        if (!isDateValid(meetingDate)) {
            throw new ParseException(String.format("Meeting date: %1$s is not in dd-MM-yyyy format", meetingDate));
        }

        //Solution below adapted from https://www.baeldung.com/java-datetimeformatter
        DateTimeFormatter newFormatter = DateTimeFormatter
            .ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.UK).withResolverStyle(ResolverStyle.SMART);

        LocalDate inputDueDate = LocalDate.parse(meetingDate, this.dateFormatter);
        String dayAndDate = inputDueDate.format(newFormatter);

        //time pattern of input date in 24 hour format -- HH for 24h, hh for 12h
        DateFormat inputTimeFormat = new SimpleDateFormat("HHmm");
        inputTimeFormat.setLenient(false);

        //Date/time pattern of desired output date
        DateFormat outputTimeFormat = new SimpleDateFormat("hh:mm aa"); // aa for AM/ PM

        if (Objects.equals(meetingTime, "")) {
            throw new ParseException("Meeting time cannot be empty");
        }

        if (!isTimeValid(meetingTime)) {
            throw new ParseException(String.format("Meeting time: %1$s is not in HHmm format", meetingTime));
        }

        Date inputTime = inputTimeFormat.parse(meetingTime);
        String outputTime = outputTimeFormat.format(inputTime);
        return dayAndDate + " " + outputTime;
    }

    // for debugging
    /*public static void main(String[] args) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.US)
            .withResolverStyle(ResolverStyle.SMART);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm", Locale.US)
            .withResolverStyle(ResolverStyle.SMART);
        DateTimeProcessor validator = new DateTimeProcessor(dateFormatter, timeFormatter);
        try {
            validator.processDateTime("asdasd");
        } catch (ParseException e) {
            System.out.println(e);
        }
    }*/

}
