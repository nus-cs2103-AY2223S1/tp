package seedu.address.logic.parser.sort;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Custom comparator that sorts Person based on their Income.
 */
public class SortByIncome implements Comparator<Person> {

    private final String order;

    /**
     * Constructor that takes in the order to sort the contact book by.
     * @param order the order to sort by.
     */
    public SortByIncome(String order) {
        this.order = order;
    }

    @Override
    public int compare(Person p1, Person p2) {

        //Since income is Long, have to manually check and return an int

        if (this.order.equals("desc")) {
            if (p2.getIncome().convertIncomeToLong() > p1.getIncome().convertIncomeToLong()) {
                return 1;
            } else if (p2.getIncome().convertIncomeToLong() < p1.getIncome().convertIncomeToLong()) {
                return -1;
            }
            return 0;
        }

        if (p1.getIncome().convertIncomeToLong() > p2.getIncome().convertIncomeToLong()) {
            return 1;
        } else if (p1.getIncome().convertIncomeToLong() < p2.getIncome().convertIncomeToLong()) {
            return -1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object other) {
        // if object is the same, short circuit this code
        if (other == this) {
            return true;
        }

        if (other instanceof SortByIncome) {
            SortByIncome s = (SortByIncome) other;
            return this.order.equals(s.order);
        }

        // handles null and if object is not an instance of SortByName
        return false;
    }
}
