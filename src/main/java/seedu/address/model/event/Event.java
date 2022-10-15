package seedu.address.model.event;

/**
 * Event represents a marketing events in the application.
 */
public class Event {
    //Temporary representation with String
    //TODO: create Data Structure for each field; Single DateTime object for Date & Time?

    private final String eventTitle;
    private final StartDate startDate;
    private final String time;
    private final String purpose;


    /**
     * Constructor to create new Event object
     * @param eventTitle title of marketing event
     * @param startDate date when marketing event begins
     * @param time time when marketing event occurs
     * @param purpose summary of what the marketing is about
     */
    public Event(String eventTitle, StartDate startDate, String time, String purpose) {
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.time = time;
        this.purpose = purpose;
    }

    public String getEventTitle() {
        return this.eventTitle;
    }

    public StartDate getStartDate() {
        return this.startDate;
    }

    public String getTime() {
        return this.time;
    }

    public String getPurpose() {
        return this.purpose;
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
                && otherEvent.getTime().equals(this.getTime())
                && otherEvent.getPurpose().equals(this.getPurpose());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Event Title: ")
                .append(this.getEventTitle())
                .append("; Start Date: ")
                .append(this.getStartDate())
                .append("; Time: ")
                .append(this.getTime())
                .append("; Purpose: ")
                .append(this.getPurpose());

        return builder.toString();
    }

}
