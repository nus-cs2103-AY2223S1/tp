package seedu.address.model.event;

/**
 * Event represents a marketing events in the application.
 */
public class Event {
    private final EventTitle eventTitle;
    private final StartDate startDate;
    private final StartTime startTime;
    private final Purpose purpose;


    /**
     * Constructor to create new Event object
     * @param eventTitle title of marketing event
     * @param startDate date when marketing event begins
     * @param startTime time when marketing event begins
     * @param purpose summary of what the marketing is about
     */
    public Event(EventTitle eventTitle, StartDate startDate, StartTime startTime, Purpose purpose) {
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.startTime = startTime;
        this.purpose = purpose;
    }

    public EventTitle getEventTitle() {
        return this.eventTitle;
    }

    public StartDate getStartDate() {
        return this.startDate;
    }

    public StartTime getStartTime() {
        return this.startTime;
    }

    public Purpose getPurpose() {
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
                && otherEvent.getStartTime().equals(this.getStartTime())
                && otherEvent.getPurpose().equals(this.getPurpose());
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
