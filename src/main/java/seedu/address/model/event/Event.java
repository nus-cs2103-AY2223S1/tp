package seedu.address.model.event;

/**
 * Event represents a marketing events in the application.
 */
public class Event {
    //Temporary representation with String
    //TODO: create Data Structure for each field; Single DateTime object for Date & Time?

    private final String eventTitle;
    private final String date;
    private final String time;
    private final String purpose;

    //add a list of Customers here
    private final UidList uids;

    /**
     * Constructor to create new Event object
     * @param eventTitle title of marketing event
     * @param date date when marketing event occurs
     * @param time time when marketing event occurs
     * @param purpose summary of what the marketing is about
     */
    public Event(String eventTitle, String date, String time, String purpose) {
        this.eventTitle = eventTitle;
        this.date = date;
        this.time = time;
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
        this.date = eventToCopy.date;
        this.time = eventToCopy.time;
        this.purpose = eventToCopy.purpose;
        this.uids = uids;
    }
    /**
     * Overloaded constructor to create new Event object
     */
    public Event(String eventTitle, String date, String time, String purpose, UidList uids) {
        this.eventTitle = eventTitle;
        this.date = date;
        this.time = time;
        this.purpose = purpose;
        this.uids = uids;
    }

    public String getEventTitle() {
        return this.eventTitle;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getPurpose() {
        return this.purpose;
    }
    public UidList getUids() {
        return this.uids;
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
                && otherEvent.getDate().equals(this.getDate())
                && otherEvent.getTime().equals(this.getTime())
                && otherEvent.getPurpose().equals(this.getPurpose())
                && otherEvent.getUids().equals(this.getUids()); //add customers in equals method
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Event Title: ")
                .append(this.getEventTitle())
                .append("; Date: ")
                .append(this.getDate())
                .append("; Time: ")
                .append(this.getTime())
                .append("; Purpose: ")
                .append(this.getPurpose());

        return builder.toString();
    }

}
