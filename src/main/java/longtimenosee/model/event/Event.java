package longtimenosee.model.event;


import static longtimenosee.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.List;

import longtimenosee.model.person.Name;
/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class Event {

    public static final Comparator<Event> EVENT_COMPARATOR = new Comparator<Event>() {
        @Override
        public int compare(Event firstEvent, Event secondEvent) {
            int compare = firstEvent.getDate().getDate().compareTo(secondEvent.getDate().getDate());
            if (compare != 0) {
                return compare;
            } else {
                return firstEvent.getDuration().getStartTime().compareTo(secondEvent.getDuration().getStartTime());
            }
        }
    };
    private final Description description;
    private final Name personName; //There can only be one!
    private final Date date;
    private final Duration duration; //Consists of 2 durations: Start Time, End Time
    /**
     * Every field must be present and not null.
     */
    public Event(Description description, Name personName, Date date, Duration duration) {
        requireAllNonNull(description, personName, date, duration);
        this.description = description;
        this.personName = personName;
        this.date = date;
        this.duration = duration;
    }
    /**
     * Utility method to view list of events.
     * @param events
     * @return
     */
    public static String viewEvents(List<Event> events) {
        StringBuilder s = new StringBuilder();
        for (Event e: events) {
            s.append(e + "\n");
        }
        return s.toString();
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
    public Name getPersonName() {
        return this.personName;
    }

    /**
     * Weaker notation of equality between two events
     * TODO: Decide what "unique event" means?
     * We only compare the description of the events and if the same people are involved.
     * @param otherEvent
     * @return
     */
    public boolean isSameEvent(Event otherEvent) {
        return otherEvent != null && otherEvent.equals(this);
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
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n" + "Description: ")
                .append(getDescription() + "\n")
                .append("Attendees: ")
                .append(getPersonName() + "\n")
                .append("Date: ")
                .append(getDate() + "\n")
                .append("Duration: ")
                .append(getDuration());

        return builder.toString();
    }
}
