package seedu.address.model.event;

/**
 * Represents the possible fields of an {@code Event} to sort by.
 */
public enum EventSortFieldType {

    EVENT_TITLE("event title"),
    DATE("date"),
    NO_FIELD("no field");


    private final String value;


    private EventSortFieldType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
