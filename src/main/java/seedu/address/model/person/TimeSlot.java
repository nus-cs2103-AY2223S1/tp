package seedu.address.model.person;

/**
 * An association class between a {@code Person} and a {@code Session}.
 */
public class TimeSlot implements Comparable<TimeSlot> {
    private final Session session;
    private final Person person;

    public TimeSlot(Session session, Person person) {
        this.session = session;
        this.person = person;
    }

    @Override
    public String toString() {
        return "";
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
