package longtimenosee.model.event;

import static longtimenosee.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;



/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class Event {
    private final Description description;
    private final PersonName personName; //There can only be one!
    private final Date date;
    private final Duration duration; //Consists of 2 durations: Start Time, End Time
    /**
     * Every field must be present and not null.
     */
    public Event(Description description, PersonName personName, Date date, Duration duration) {
        requireAllNonNull(description, personName, date, duration);
        this.description = description;
        this.personName = personName;
        this.date = date;
        this.duration = duration;
    }
    //TODO: Can you pin an event?

    public Description getDescription() {
        return description;
    }

    /**
     * Utility function to check if 2 events clash
     * @return whether the two event Clashes.
     */
    public static Boolean eventClash(Event first, Event second) {
        if (first.getDate().equals(second.getDate())) { //same day
            if (first.getDuration().isOverlap(second.getDuration())) { //duration window overlaps
                return true;
            }
        }
        return false;
    }

    public Date getDate() {
        return this.date;
    }

    public Duration getDuration() {
        return this.duration;
    }
    //TODO: Check if a person currently exists in the UniquePersonList for verification
    public PersonName getPersonName() {
        return this.personName;
    }

    /**
     * Weaker notation of equality between two events
     * We only compare the description of the events and if the same people are involved.
     * @param otherEvent
     * @return
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getDescription().equals(getDescription())
                && otherEvent.getPersonName().equals(getPersonName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getPersonName().equals(this.getPersonName())
                && otherEvent.getDescription().equals(this.getDescription())
                && otherEvent.getDate().equals(this.getDate())
                && otherEvent.getPersonName().equals(((Event) other).getPersonName())
                && otherEvent.getDuration().equals(this.getDuration());
    }





}
