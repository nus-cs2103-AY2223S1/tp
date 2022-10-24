package seedu.address.model.event;

import seedu.address.model.person.Person;

/**
 * Event represents a marketing events in the application.
 */
public class Event {
    private final String eventTitle;
    private final StartDate startDate;
    private final StartTime startTime;
    private final String purpose;

    //add a list of Customers here
    private final UidList uids;

    /**
     * Constructor to create new Event object
     * @param eventTitle title of marketing event
     * @param startDate date when marketing event begins
     * @param startTime time when marketing event begins
     * @param purpose summary of what the marketing is about
     */
    public Event(String eventTitle, StartDate startDate, StartTime startTime, String purpose) {
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.startTime = startTime;
        this.purpose = purpose;
        this.uids = new UidList();
    }

    /**
     * Overloaded constructor to create new Event object
     * @param eventToCopy event to copy
     * @param uids new uid list
     */
    public Event(Event eventToCopy, UidList uids) {
        this.eventTitle = eventToCopy.eventTitle;
        this.startDate = eventToCopy.startDate;
        this.startTime = eventToCopy.startTime;
        this.purpose = eventToCopy.purpose;
        this.uids = uids;
    }
    /**
     * Overloaded constructor to create new Event object
     */
    public Event(String eventTitle, StartDate startDate, StartTime startTime, String purpose, UidList uids) {
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.startTime = startTime;
        this.purpose = purpose;
        this.uids = uids;
    }

    public String getEventTitle() {
        return this.eventTitle;
    }

    public StartDate getStartDate() {
        return this.startDate;
    }

    public StartTime getStartTime() {
        return this.startTime;
    }

    public String getPurpose() {
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
                && otherEvent.getUids().equals(this.getUids()); //add customers in equals method
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
                .append(this.getPurpose());

        return builder.toString();
    }
}
