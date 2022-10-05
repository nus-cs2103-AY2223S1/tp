package seedu.address.model.person;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Person's class date in the address book.
 * Guarantees: immutable.
 */
public class Class {

    public final LocalDate date;
    public final LocalTime startTime;
    public final LocalTime endTime;


    /**
     * Constructs a {@code Class}.
     *
     * @param date
     * @param startTime
     * @param endTime
     */
    public Class(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
