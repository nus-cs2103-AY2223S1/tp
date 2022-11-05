package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a field to sort Events by.
 * Guarantees: immutable; is valid as declared in {@link #isValidSortField(String)}
 */
public class EventSortField {

    public static final String MESSAGE_CONSTRAINTS = "Event sort field must either be e, E, d or D.";

    // Accept exactly one upper or lower case e or d
    public static final String VALIDATION_REGEX = "[eEdD]{1}";


    // Comparator logic
    private static final Comparator<Event> SORT_BY_NO_FIELD = (e1, e2) -> 0;
    private static final Comparator<Event> SORT_BY_EVENT_TITLE = (e1, e2)
            -> e1.getEventTitle().compareTo(e2.getEventTitle());

    private static final Comparator<Event> SORT_BY_DATE = new Comparator<Event>() {

        @Override
        public int compare(Event e1, Event e2) {

            // Compare by event date first
            int compareByDateResult = e1.getStartDate().compareTo(e2.getStartDate());

            // If event date of both events are the same
            if (compareByDateResult == 0) {
                // Compare by event time instead
                return e1.getStartTime().compareTo(e2.getStartTime());
            }

            // Else event dates are different
            return compareByDateResult;
        }
    };


    private final EventSortFieldType field;
    private final Comparator<Event> comp;


    private EventSortField(EventSortFieldType type, Comparator<Event> c) {
        requireAllNonNull(type, c);
        field = type;
        comp = c;
    }

    public EventSortFieldType getField() {
        return field;
    }

    public Comparator<Event> getComparator() {
        return comp;
    }


    /**
     * Constructs an {@code EventSortField}.
     *
     * @param sortFieldLetter A valid sort field letter.
     * @return {@code EventSortField} representing the sort field letter.
     */
    public static EventSortField createSortField(String sortFieldLetter) {
        requireNonNull(sortFieldLetter);
        checkArgument(isValidSortField(sortFieldLetter), MESSAGE_CONSTRAINTS);
        return mapLetterToSortField(sortFieldLetter);
    }


    /**
     * Constructs an {@code EventSortField} that doesn't sort by any field.
     *
     * @return {@code EventSortField} that doesn't sort by any field.
     */
    public static EventSortField sortByNoField() {
        return new EventSortField(EventSortFieldType.NO_FIELD, SORT_BY_NO_FIELD);
    }


    /**
     * Returns true if the given {@code String} is a valid sort field.
     *
     * @param test {@code String} to test.
     * @return boolean result of test.
     */
    public static boolean isValidSortField(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }


    /**
     * Maps the given sort field letter to its corresponding {@code EventSortFieldType}.
     *
     * @param sortFieldLetter letter to map to {@code EventSortFieldType}.
     * @return {@code EventSortField} representing the sort field letter.
     */
    private static EventSortField mapLetterToSortField(String sortFieldLetter) {

        switch(sortFieldLetter) {

        case "e":
        case "E":
            return new EventSortField(EventSortFieldType.EVENT_TITLE, SORT_BY_EVENT_TITLE);

        case "d":
        case "D":
            return new EventSortField(EventSortFieldType.DATE, SORT_BY_DATE);

        // Don't sort by any field by default
        default:
            return sortByNoField();
        }
    }

    @Override
    public String toString() {
        return String.format("Event Sort Field: %s\nComparator: %s", field.toString(), comp.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventSortField)) {
            return false;
        }

        // state check
        EventSortField s = (EventSortField) other;

        return this.getField().equals(s.getField())
            && this.getComparator().equals(s.getComparator());
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, comp);
    }
}
