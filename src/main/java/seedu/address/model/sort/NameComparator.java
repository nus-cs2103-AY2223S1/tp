package seedu.address.model.sort;

import java.util.Comparator;
import java.util.Locale;

import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * NameComparator class to sort the persons list based on names.
 */
public class NameComparator implements Comparator<Person> {
    private final String comparator = "name";
    private Email getN;

    @Override
    public int compare(Person x, Person y) {
        getN = y.getEmail();
        return x.getName().fullName.toLowerCase(Locale.ROOT).compareTo(y.getName().fullName.toLowerCase(Locale.ROOT));
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
