package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.date.Date;
import seedu.address.model.person.Person;

/**
 * Event represents a marketing events in the application.
 */
public class Event {
    private final EventTitle eventTitle;
    private final Date startDate;
    private final StartTime startTime;
    private final Purpose purpose;
    private final UidList uids;

    /**
     * Constructor to create new Event object
     * @param eventTitle title of marketing event
     * @param startDate date when marketing event begins
     * @param startTime time when marketing event begins
     * @param purpose summary of what the marketing is about
     */
    public Event(EventTitle eventTitle, Date startDate, StartTime startTime, Purpose purpose) {
        requireAllNonNull(eventTitle, startDate, startTime, purpose);
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.startTime = startTime;
        this.purpose = purpose;
        this.uids = new UidList();
    }
    /**
     * Overloaded constructor to create new Event object
     * Every field must be present and not null.
     */
    public Event(EventTitle eventTitle, Date startDate, StartTime startTime, Purpose purpose, UidList uids) {
        requireAllNonNull(eventTitle, startDate, startTime, purpose, uids);
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.startTime = startTime;
        this.purpose = purpose;
        this.uids = uids;
    }
    /**
     * Overloaded constructor to create new Event object from old Event object with updated uid list.
     * @param eventToCopy event to copy to the new Event object
     * @param uids new uid list
     */
    public Event(Event eventToCopy, UidList uids) {
        this.eventTitle = eventToCopy.eventTitle;
        this.startDate = eventToCopy.startDate;
        this.startTime = eventToCopy.startTime;
        this.purpose = eventToCopy.purpose;
        this.uids = uids;
    }
    public EventTitle getEventTitle() {
        return this.eventTitle;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public StartTime getStartTime() {
        return this.startTime;
    }

    public Purpose getPurpose() {
        return this.purpose;
    }
    public UidList getUids() {
        return this.uids;
    }
    /**
     * Returns true if the person is tagged to the event.
     */
    public boolean containsPerson(Person person) {
        return uids.contains(person.getUid());
    }
    /**
     * Returns true if both events have the same eventTitle.
     * Serves as a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getEventTitle().equals(getEventTitle());
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
        return otherEvent.getEventTitle().equals(this.getEventTitle())
                && otherEvent.getStartDate().equals(this.getStartDate())
                && otherEvent.getStartTime().equals(this.getStartTime())
                && otherEvent.getPurpose().equals(this.getPurpose())
                && otherEvent.getUids().equals(this.getUids());
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(eventTitle, startDate, startTime, purpose, uids);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Event Title: ")
                .append(this.getEventTitle())
                .append("; Start Date: ")
                .append(this.getStartDate())
                .append("; Start Time: ")
                .append(this.getStartTime())
                .append("; Purpose: ")
                .append(this.getPurpose())
                .append("; Attendees: ")
                .append(this.uids.getPersonNames());

        return builder.toString();
    }
}
