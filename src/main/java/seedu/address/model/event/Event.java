package seedu.address.model.event;

/**
 * Event represents a marketing events in the application.
 */
public class Event {
    //Temporary representation with String
    //TODO: create Data Structure for each field; Single DateTime object for Date & Time?
    private final String event_title;
    private final String date;
    private final String time;
    private final String purpose;

    public Event(String event_title, String date, String time, String purpose) {
        this.event_title = event_title;
        this.date = date;
        this.time = time;
        this.purpose = purpose;
    }

    public String getEventTitle() {
        return this.event_title;
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
                && otherEvent.getPurpose().equals(this.getPurpose());
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
