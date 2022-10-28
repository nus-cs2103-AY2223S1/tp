package seedu.address.logic.parser.sort;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Custom comparator that sorts Person based on their Risk Levels.
 */
public class SortByClientTag implements Comparator<Person> {

    private final String order;

    /**
     * Constructor that takes in the order to sort the contact book by.
     * @param order the order to sort by.
     */
    public SortByClientTag(String order) {
        this.order = order;
    }

    @Override
    public int compare(Person p1, Person p2) {

        if (this.order.equals("desc")) {
            return p2.getClientTag().getType() - p1.getClientTag().getType();
        }

        return p1.getClientTag().getType() - p2.getClientTag().getType();
    }

    @Override
    public boolean equals(Object other) {
        // if object is the same, short circuit this code
        if (other == this) {
            return true;
        }

        if (other instanceof SortByClientTag) {
            SortByClientTag s = (SortByClientTag) other;
            return this.order.equals(s.order);
        }

        // handles null and if object is not an instance of SortByName
        return false;
    }
}
