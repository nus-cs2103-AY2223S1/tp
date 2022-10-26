package seedu.address.logic.parser.sort;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Custom comparator that sorts Person based on their Names.
 */
public class SortByRiskTag implements Comparator<Person> {

    private final String order;

    /**
     * Constructor that takes in the order to sort the contact book by.
     * @param order the order to sort by.
     */
    public SortByRiskTag(String order) {
        this.order = order;
    }

    @Override
    public int compare(Person p1, Person p2) {

        if (this.order.equals("desc")) {
            return p2.getRiskTag().getRisk() - p1.getRiskTag().getRisk();
        }

        return p1.getRiskTag().getRisk() - p2.getRiskTag().getRisk();
    }

    @Override
    public boolean equals(Object other) {
        // if object is the same, short circuit this code
        if (other == this) {
            return true;
        }

        if (other instanceof SortByRiskTag) {
            SortByRiskTag s = (SortByRiskTag) other;
            return this.order.equals(s.order);
        }

        // handles null and if object is not an instance of SortByName
        return false;
    }
}
