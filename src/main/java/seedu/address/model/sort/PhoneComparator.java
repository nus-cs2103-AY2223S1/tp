package seedu.address.model.sort;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class PhoneComparator implements Comparator<Person> {
    private final String comparator = "phone";

    @Override
    public int compare(Person x, Person y) {
        return x.getPhone().value.compareTo(y.getPhone().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
