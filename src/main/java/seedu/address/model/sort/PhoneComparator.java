package seedu.address.model.sort;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * PhoneComparator class to sort the persons list based on phone number.
 */
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
