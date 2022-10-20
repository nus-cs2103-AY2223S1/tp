package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * An association class between a {@code Person} and a {@code Session}.
 * Represents a time slot in a day.
 */
public class TimeSlot implements Comparable<TimeSlot> {
    private final Session session;
    private final Person person;

    /**
     * Constructs an {@code TimeSlot}.
     *
     * @param session A person's session.
     * @param person A person object to be associated with.
     */
    public TimeSlot(Session session, Person person) {
        requireNonNull(session);
        requireNonNull(person);
        this.session = session;
        this.person = person;
    }

    public Session getSession() {
        return session;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(session.toString()).append("\n").append(person.toString());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { //short circuit if same object
            return true;
        } else if (!(other instanceof TimeSlot)) { //instanceof handles nulls
            return false;
        }
        TimeSlot temp = (TimeSlot) other;
        return this.session.equals(temp.session) && this.person.equals(temp.person);
    }

    @Override
    public int compareTo(TimeSlot other) {
        return this.session.compareTo(other.session);
    }
}
