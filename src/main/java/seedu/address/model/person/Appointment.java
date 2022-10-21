package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import seedu.address.model.tag.Tag;

/**
 * Represents the patient's appointments' details.
 */
public class Appointment {
    public static final String REASON_MESSAGE_CONSTRAINTS = "Reason should not be empty";
    public static final String DATE_MESSAGE_CONSTRAINTS = "Date should contain YYYY-MM-DD and HH:MM values";
    public static final String TIME_PERIOD_MESSAGE_CONSTRAINTS = "Time Period should contain valid Y M or D values";
    public static final String TAG_QUANTITY_CONSTRAINTS = "Operation results in more than 1 tag in Appointment.";
    public static final DateTimeFormatter DATE_FORMATTER =
            new DateTimeFormatterBuilder().appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    .appendOptional(DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")).toFormatter();

    public static final DateTimeFormatter STORAGE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Pattern TIME_PERIOD_FORMAT = Pattern.compile("^(?<year>\\s*([0-9]|10)Y)?"
            + "(?<month>\\s*([0-9]|1[0-2])M)?(?<day>\\s*([0-9]|[1-2][0-9]|3[0-1])D)?\\s*$");
    private final String reason;
    private final LocalDateTime dateTime;
    private final List<Integer> timePeriod;
    private final Set<Tag> tags = new HashSet<>();
    private final SimpleBooleanProperty isMarked;
    private final SimpleObjectProperty<Person> patient = new SimpleObjectProperty<>();

    private final DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Creates an appointment object with the given reason, dateTime, timePeriod string, and status.
     *
     * @param reason The given reason for appointment.
     * @param dateTime The given time to book the appointment.
     * @param timePeriod The given time period for the next appointment.
     * @param isMarked Status of the appointment.
     */
    public Appointment(String reason, String dateTime, String timePeriod, boolean isMarked) {
        checkValidity(reason, dateTime, timePeriod);
        this.reason = reason;
        String str = String.join(" ", dateTime.split("\\s+", 2));
        this.dateTime = LocalDateTime.parse(str, DATE_FORMATTER);
        this.timePeriod = parseTimePeriod(timePeriod);
        this.isMarked = new SimpleBooleanProperty(isMarked);
    }


    /**
     * Creates an appointment object with the given reason, dateTime, timePeriod string, and status.
     *
     * @param reason The given reason for appointment.
     * @param dateTime The given time to book the appointment.
     * @param timePeriod The given time period for the next appointment.
     * @param tags The given tag for the appointment.
     * @param isMarked Status of the appointment.
     */
    public Appointment(String reason, String dateTime, String timePeriod, Set<Tag> tags, boolean isMarked) {
        checkValidity(reason, dateTime, timePeriod);
        this.reason = reason;
        String str = String.join(" ", dateTime.split("\\s+", 2));
        this.dateTime = LocalDateTime.parse(str, DATE_FORMATTER);
        this.timePeriod = parseTimePeriod(timePeriod);
        this.isMarked = new SimpleBooleanProperty(isMarked);
        this.tags.addAll(tags);
    }

    /**
     * Creates an appointment object with the given reason, dateTime, timePeriod and status.
     *
     * @param reason The given reason for appointment.
     * @param dateTime The given time to book the appointment.
     * @param timePeriod The given time period for the next appointment.
     * @param isMarked Status of the appointment.
     */
    public Appointment(String reason, LocalDateTime dateTime, List<Integer> timePeriod, boolean isMarked) {
        this.reason = reason;
        this.dateTime = dateTime;
        this.timePeriod = timePeriod;
        this.isMarked = new SimpleBooleanProperty(isMarked);
    }

    /**
     * Creates an appointment object with the given reason, dateTime, timePeriod and status.
     *
     * @param reason The given reason for appointment.
     * @param dateTime The given time to book the appointment.
     * @param timePeriod The given time period for the next appointment.
     * @param tags The given tag for the appointment
     * @param isMarked Status of the appointment.
     */
    public Appointment(String reason, LocalDateTime dateTime, List<Integer> timePeriod,
                       Set<Tag> tags, boolean isMarked) {
        this.reason = reason;
        this.dateTime = dateTime;
        this.timePeriod = timePeriod;
        this.isMarked = new SimpleBooleanProperty(isMarked);
        this.tags.addAll(tags);
    }

    /**
     * Creates an appointment object with a new dateTime using the given timePeriod of the recurring appointment.
     *
     * @param recurringAppointment The appointment given that will occur again.
     */
    public Appointment(Appointment recurringAppointment) {
        this.reason = recurringAppointment.getReason();
        this.dateTime = incrementDateTime(recurringAppointment.getDateTime(), recurringAppointment.getTimePeriod());
        this.timePeriod = recurringAppointment.getTimePeriod();
        this.tags.addAll(recurringAppointment.tags);
        this.isMarked = new SimpleBooleanProperty(false);
        this.patient.set(recurringAppointment.getPatient());
    }

    private LocalDateTime incrementDateTime(LocalDateTime dateTime, List<Integer> timePeriod) {
        LocalDateTime nextDateTime;
        nextDateTime = dateTime.plusYears(timePeriod.get(0));
        nextDateTime = nextDateTime.plusMonths(timePeriod.get(1));
        nextDateTime = nextDateTime.plusDays(timePeriod.get(2));
        return nextDateTime;
    }


    private void checkValidity(String reason, String dateTime, String timePeriod) {
        requireNonNull(reason);
        requireNonNull(dateTime);
        checkArgument(isValidReason(reason), REASON_MESSAGE_CONSTRAINTS);
        checkArgument(isValidDateTime(dateTime), DATE_MESSAGE_CONSTRAINTS);
        checkArgument(isValidTimePeriod(timePeriod), TIME_PERIOD_MESSAGE_CONSTRAINTS);
    }

    /**
     * Parses the given timePeriod string into a list of integers depicting the values of year, month and day.
     *
     * @param timePeriod The given timePeriod string.
     * @return A list of integers consisting the number of years, months and days to add.
     */
    public static List<Integer> parseTimePeriod(String timePeriod) {
        List<Integer> list = Arrays.asList(0, 0, 0);
        final Matcher matcher = TIME_PERIOD_FORMAT.matcher(timePeriod);
        if (timePeriod.isEmpty() || !matcher.matches()) {
            return list;
        }

        Function<String, String> f = x -> x == null ? "" : x.trim();
        List<String> durations = Stream.of(matcher.group("year"), matcher.group("month"),
                matcher.group("day")).map(f).collect(Collectors.toList());

        Consumer<String> c = x -> {
            if (!x.isEmpty()) {
                list.set(durations.indexOf(x), Integer.parseInt(x.substring(0, x.length() - 1)));
            }
        };
        durations.forEach(c);
        return list;
    }

    /**
     * Checks whether the given appointment has the same time.
     *
     * @param other The given appointment.
     * @return The result of the equals test.
     */
    public boolean isSameTime(Appointment other) {
        return other.dateTime.equals(dateTime);
    }

    /**
     * Checks whether the given string is a valid reason.
     *
     * @param test The string to test.
     * @return The result of the equals test.
     */
    public static boolean isValidReason(String test) {
        return !test.equals("");
    }

    /**
     * Checks whether the given string is a valid DateTime.
     *
     * @param test The string to test.
     * @return The result of the LocalDateTime parse test.
     */
    public static boolean isValidDateTime(String test) {
        try {
            String str = String.join(" ", test.split("\\s+", 2));
            LocalDateTime.parse(str, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether the given string is a valid timePeriod.
     *
     * @param test The string to test.
     * @return The result of the matcher test.
     */
    public static boolean isValidTimePeriod(String test) {
        final Matcher matcher = TIME_PERIOD_FORMAT.matcher(test.trim());
        return test.isEmpty() || matcher.matches();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Integer> getTimePeriod() {
        return timePeriod;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public String getFormattedDateTime() {
        return dateTime.format(stringFormatter);
    }

    public String getFormattedPeriod() {
        String str = "%sY%sM%sD";
        str = String.format(str, timePeriod.get(0), timePeriod.get(1), timePeriod.get(2));
        return str;
    }

    public String getReason() {
        return reason;
    }

    public boolean isMarked() {
        return isMarked.get();
    }

    public void mark() {
        this.isMarked.set(true);
    }

    public void unmark() {
        this.isMarked.set(false);
    }

    public void setPatient(Person patient) {
        this.patient.set(patient);
    }

    public Person getPatient() {
        return patient.get();
    }

    public String getPatientName() {
        return this.patient.get().getName().fullName;
    }

    public String getStatus() {
        return "[" + getStateIcon() + "]";
    }

    @Override
    public String toString() {
        return getStatus() + " " + getFormattedDateTime() + " for " + reason + getRecurringStatus();
    }

    private String getStateIcon() {
        String markedIcon = "✅";
        String unmarkedIcon = "❌";
        return isMarked.get() ? markedIcon : unmarkedIcon;
    }
    public String getRecurringStatus() {
        String nonRecurring = "\nNon-recurring";
        String recurring = "\nRecurring every ";
        List<String> datesList = List.of(" year" , " month", " day");
        if (timePeriod.stream().allMatch(x -> x.equals(0))) {
            return nonRecurring;
        }
        for (int i = 0; i < timePeriod.size(); i++) {
            Integer digit = timePeriod.get(i);
            if (!digit.equals(0)) {
                recurring += digit.equals(1) ? digit + datesList.get(i) + " " : digit + datesList.get(i) + "s ";
            }
        }
        return recurring;
    }

    /**
     * Returns -1 if this appointment appears before the other appointment, and
     * returns 0 if this appointment has the same order as the other appointment, and
     * returns 1 if this appointment appears after the other appointment.
     *
     * @param appointment The other appointment to compare with.
     */
    public int compareTo(Appointment appointment) {
        return this.dateTime.compareTo(appointment.dateTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;

        return otherAppointment.patient.get().getName().equals(patient.get().getName())
                && otherAppointment.reason.equals(reason)
                && otherAppointment.dateTime.equals(dateTime)
                && otherAppointment.timePeriod.equals(timePeriod)
                && (otherAppointment.isMarked.get() == isMarked.get());
    }

    /**
     * Returns true if both appointments have the same reason, dateTime, timePeriod and status.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment appointment) {
        return appointment.reason.equals(reason)
                && appointment.dateTime.equals(dateTime)
                && appointment.timePeriod.equals(timePeriod)
                && (appointment.isMarked.get() == isMarked.get());
    }

    public Observable[] getProperties() {
        return new Observable[] {isMarked, patient};
    }


}
