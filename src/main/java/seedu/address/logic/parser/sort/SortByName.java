package seedu.address.logic.parser.sort;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Custom comparator that sorts Person based on their Names.
 */
public class SortByName implements Comparator<Person> {

    private String order;

    /**
     * Constructor that takes in the order to sort the contact book by.
     * @param order the order to sort by.
     */
    public SortByName(String order) {
        this.order = order;
    }
    @Override
    public int compare(Person p1, Person p2) {
        // sorts the contact by descending order if there is a desc keyword
        if (this.order.equals("desc")) {
            return p2.getName().fullName.compareToIgnoreCase(p1.getName().fullName);
        }

        // default sorting is ascending
        return p1.getName().fullName.compareToIgnoreCase(p2.getName().fullName);
    }

    @Override
    public boolean equals(Object other) {
        // if object is the same, short circuit this code
        if (other == this) {
            return true;
        }

        if (other instanceof SortByName) {
            SortByName s = (SortByName) other;
            return this.order.equals(s.order);
        }

        // handles null and if object is not an instance of SortByName
        return false;
    }
}
