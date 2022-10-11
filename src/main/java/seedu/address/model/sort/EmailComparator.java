package seedu.address.model.sort;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class EmailComparator implements Comparator<Person> {
    private final String comparator = "email";

    @Override
    public int compare(Person x, Person y) {
        return x.getEmail().value.compareTo(y.getEmail().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
